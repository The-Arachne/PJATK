using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace TakaWydmuszka.DTO_s
{
    public class Task_Output
    {
        public int IdTask { get; set; }
        public string Name { get; set; }
        public string TaskType_Name { get; set; }
        public string Description { get; set; }
        public DateTime DeadLine { get; set; }
        
    }
}
