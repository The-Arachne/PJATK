using Microsoft.AspNetCore.Mvc;
using PrzykladowyKolos.Models;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;

namespace PrzykladowyKolos.Services
{
    public class SqlService: Controller,ISqlService
    {
        private const string ConString = "Data Source=db-mssql;Initial Catalog=s18635;Integrated Security=True;MultipleActiveResultSets=true";

        public IActionResult getSortedBy(string orderBy)
        {
            var list = new List<Animal>();
            using (SqlConnection con = new SqlConnection(ConString))
            using (SqlCommand com = new SqlCommand())
            {
                com.Connection = con;
                con.Open();
                var poCzymSort =(orderBy!=null && orderBy.Length>0)?orderBy:"AdmissionDate desc";
                var comm = "select Name,Type,AdmissionDate,LastName from animal inner join owner on animal.idOwner = owner.idOwner order by "+poCzymSort;
                com.CommandText = comm;
                com.Parameters.AddWithValue("@sort", poCzymSort);
                try
                {
                    SqlDataReader dr = com.ExecuteReader();
                    while (dr.Read())
                    {
                        var tmpAni = new Animal();
                        tmpAni.Animal_Name = dr["Name"].ToString();
                        tmpAni.Animal_Type = dr["Type"].ToString();
                        tmpAni.Animal_DateOfAdmission = dr["AdmissionDate"].ToString();
                        tmpAni.Animal_LastNameOfOwner = dr["LastName"].ToString();

                        list.Add(tmpAni);
                    }
                }catch(Exception e)
                {
                    Console.WriteLine(e);
                    return StatusCode(400,"Nie znaleziono kolumny:\n"+e);
                }
                
            }

                return Ok(list);
        }
    }
}
