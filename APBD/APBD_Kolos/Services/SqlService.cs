using APBD_Kolos.Models;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;

namespace APBD_Kolos.Services
{
    public class SqlService : Controller, ISqlService
    {
        private const string ConString = "Data Source=db-mssql;Initial Catalog=s18635;Integrated Security=True;MultipleActiveResultSets=true";

        public IActionResult addProject(AddTask proj)
        {
            using (SqlConnection con = new SqlConnection(ConString))
            using (SqlCommand com = new SqlCommand())
            {
                com.Connection = con;
                con.Open();
                SqlTransaction transaction = con.BeginTransaction();
                try
                {
                    com.CommandText = "select * from tasktype where name=@name";
                    com.Transaction = transaction;
           
                    com.Parameters.AddWithValue("@name", proj.TaskType.Name);
                    SqlDataReader dr = null;
                    try
                    {
                        dr = com.ExecuteReader();
                    }
                    catch (Exception e)
                    {
                        return NotFound("cos z obiektem tasktype jest nie tak");
                    }

                    if (dr != null && !dr.HasRows)
                    {
                        dr.Close();
                        com.CommandText = "insert into tasktype values(@tskname)";
     
                        com.Parameters.AddWithValue("@tskname", proj.TaskType.Name);
                        com.ExecuteNonQuery();
                        dr.Close();
                    }
                    Console.WriteLine("JESTEM TU");
                    com.CommandText = "insert into task values((select Max(idtask)+1 from task),@name,@desc,@dedl,@idproj,(select idproject from tasktype where name =@name),@idass,@idcre,)";
                    com.Parameters.AddWithValue("@name", proj.Name);
                    com.Parameters.AddWithValue("@desc", proj.Description);
                    com.Parameters.AddWithValue("@dedl", proj.DeadLine);
                    com.Parameters.AddWithValue("@idproj", proj.IdTeam);
                    com.Parameters.AddWithValue("@idass", proj.IdAssignedTo);
                    com.Parameters.AddWithValue("@idcre", proj.IdCreator);
                    com.ExecuteNonQuery();
                    Console.WriteLine("A TERAZ TU");



                    transaction.Commit();
                }
                catch (Exception e)
                {
                    transaction.Rollback();
                    return NotFound("cos jest nie tak\n" + e);
                }
                transaction.Dispose();
            }
            return Ok();
        }

        public IActionResult getProjects(int id)
        {
            var project = new Project_get();
            project.Task_List = new List<Tasks>();
            using (SqlConnection con = new SqlConnection(ConString))
            using (SqlCommand com = new SqlCommand())
            {
                com.Connection = con;
                con.Open();
                
                
                com.CommandText = "select pr.name as nae,pr.deadline as ded, ts.name as tsnae,ts.description as tsdescr,ts.deadline as tsded,tsk.name as tsknae from project pr inner join task ts on pr.idproject = ts.idproject inner join tasktype tsk on tsk.idtasktype = ts.idtasktype where pr.idproject = @id order by tsded desc";
                com.Parameters.AddWithValue("@id", id);
                try
                {
                    Console.WriteLine("benc");
                    SqlDataReader dr = com.ExecuteReader();
                    Console.WriteLine("Dziala");
                    while (dr.Read())
                    {
                        project.Pro_name = dr["nae"].ToString();
                        project.Pro_DeadLine = dr["ded"].ToString();
                        
                        var taskTMP = new Tasks();
                        taskTMP.name= dr["tsnae"].ToString();
                        taskTMP.desccription= dr["tsdescr"].ToString();
                        taskTMP.deadline= dr["tsded"].ToString();
                        taskTMP.tasktypeName= dr["tsknae"].ToString();

                        project.Task_List.Add(taskTMP);
                        
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine(e);
                    return StatusCode(400, "Nie znaleziono kolumny:\n" + e);
                }

            }

            return Ok(project);
        }
    }
}
