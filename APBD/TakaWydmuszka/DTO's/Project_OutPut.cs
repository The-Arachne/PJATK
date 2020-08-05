using System;
using System.Collections.Generic;

namespace TakaWydmuszka.DTO_s
{
    public class Project_OutPut
    {
        public Project_OutPut()
        {
            taskList = new List<Task_Output>();
        }
        public string Name { get; set; }
        public DateTime DeadLine { get; set; }

        public List<Task_Output> taskList { get; set; }
    }
}
