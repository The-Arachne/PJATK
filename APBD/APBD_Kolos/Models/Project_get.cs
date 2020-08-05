using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBD_Kolos.Models
{
    public class Project_get
    {
        public string Pro_name { get; set; }
        public string Pro_DeadLine { get; set; }
        public List<Tasks> Task_List { get; set; }
        

    }
}
