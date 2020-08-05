using APBD_Kolos.Models;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBD_Kolos.Services
{
    interface ISqlService
    {
        public IActionResult getProjects(int id);
        public IActionResult addProject(AddTask proj);
    }
}
