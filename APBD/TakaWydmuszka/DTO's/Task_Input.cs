using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace TakaWydmuszka.DTO_s
{
    public class Task_Input
    {
        public string Name { get; set; }
        public string Description { get; set; }
        public DateTime Deadline { get; set; }
        public int IdTeam { get; set; }
        public int IdAssignedTo { get; set; }
        public int IdCreator { get; set; }

        public TaskType_Input TaskType { get; set; }

    }
}
