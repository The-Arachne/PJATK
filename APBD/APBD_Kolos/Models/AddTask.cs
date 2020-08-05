using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBD_Kolos.Models
{
    public class AddTask
    {
        public string  Name { get; set; }
        public string Description { get; set; }
        public string DeadLine { get; set; }
        public int IdTeam { get; set; }
        public int IdAssignedTo { get; set; }
        public int IdCreator { get; set; }
        public TaskIn TaskType { get; set; }
    }
}
