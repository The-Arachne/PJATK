using System;
using System.Data.SqlClient;
using TakaWydmuszka.CustomExceptions;
using TakaWydmuszka.DTO_s;

namespace TakaWydmuszka.Services
{
    public class DbContext : IDbContext
    {
        private string connString = "Data Source=db-mssql;Initial Catalog=s18635;Integrated Security=True;MultipleActiveResultsets=true";

        public void addTask(Task_Input input)
        {
            using (var conn = new SqlConnection(connString))
            using (var comand = new SqlCommand())
            {
                comand.Connection = conn;
                conn.Open();
                var tran = conn.BeginTransaction();
                comand.Transaction = tran;

                try {
                    comand.CommandText = "select * from TaskType where Name=@NameType";
                    comand.Parameters.AddWithValue("NameType", input.TaskType.Name);

                    var dr = comand.ExecuteReader();
                    if (!dr.Read())
                    {
                        dr.Close();
                        comand.CommandText = "insert into TaskType values(@NameType)";
                        comand.ExecuteNonQuery();
                    }
                    dr.Close();
                    comand.CommandText = "select idTaskType from TaskType where Name=@NameType";
                    dr = comand.ExecuteReader();
                    if (dr.Read())
                    {
                        
                        input.TaskType.IdTaskType = (int)dr.GetValue(0);
                    }
                    dr.Close();

                    comand.CommandText = "select * from project where idProject=@idProject";
                    comand.Parameters.AddWithValue("idProject", input.IdTeam);
                    dr = comand.ExecuteReader();
                    if (!dr.Read()) throw new Exc_NotFound("Project with this ID is not found");
                    dr.Close();

                    comand.CommandText = "select * from TeamMember where IdTeamMember=@idAssignedTo or IdTeamMember=@idCreator";
                    comand.Parameters.AddWithValue("idAssignedTo", input.IdAssignedTo);
                    comand.Parameters.AddWithValue("idCreator", input.IdCreator);
                    dr = comand.ExecuteReader();
                    if (!dr.Read()) throw new Exc_NotFound("TeamMember with this ID is not found");
                    dr.Close();


                    comand.CommandText = "insert into Task (name, description, deadline, idproject, idtasktype, idassignedto, idcreator) "+
                                          "values(@name,@description,@deadline,@idProject,@idTaskType,@idAssignedTo,@idCreator)";
                    comand.Parameters.AddWithValue("name", input.Name);
                    comand.Parameters.AddWithValue("description", input.Description);
                    comand.Parameters.AddWithValue("deadline", input.Deadline);
                    comand.Parameters.AddWithValue("idTaskType", input.TaskType.IdTaskType);
                    comand.ExecuteNonQuery();
                    dr.Close();

                    tran.Commit();
                    tran.Dispose();
                }
                catch (Exc_BadRequest e)
                {
                    tran.Rollback();
                    tran.Dispose();
                    throw new Exc_BadRequest(e.Message);
                }catch (Exc_NotFound e)
                {
                    tran.Rollback();
                    tran.Dispose();
                    throw new Exc_NotFound(e.Message);
                }
                catch (Exception e)
                {
                    tran.Rollback();
                    tran.Dispose();
                    throw new Exc_BadRequest(e.Message);
                }
            }
        }

        public Project_OutPut getProject(int id)
        {
            using(var conn=new SqlConnection(connString))
            using(var comand=new SqlCommand())
            {
                Project_OutPut res = new Project_OutPut();

                comand.Connection = conn;
                conn.Open();

                comand.CommandText = "select * from Project where IdProject=@id";
                comand.Parameters.AddWithValue("id", id);
                var dr = comand.ExecuteReader();
                if (!dr.Read()) throw new Exc_NotFound("Project z tym ID nie istnieje");
                else
                {
                    res.Name = dr["Name"].ToString();
                    res.DeadLine = (DateTime)dr["Deadline"];
                }
                dr.Close();

                comand.CommandText = "select tk.IdTask, tk.Name TaskName, tk.Description, tk.DeadLine, tt.Name TypeName from Task tk inner join TaskType tt on tk.IdTaskType = tt.IdTaskType where tk.IdProject=@id order by tk.DeadLine desc";
                dr = comand.ExecuteReader();
                while (dr.Read())
                {
                    var task = new Task_Output() 
                    {
                        DeadLine=(DateTime)dr["DeadLine"],
                        Description=dr["Description"].ToString(),
                        IdTask=(int)dr["IdTask"],
                        Name=dr["TaskName"].ToString(),
                        TaskType_Name=dr["TypeName"].ToString()
                    };
                    res.taskList.Add(task);
                }
                dr.Close();
                return res;
            }
        }
    }
}
