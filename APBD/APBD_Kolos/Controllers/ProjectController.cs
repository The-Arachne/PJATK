using APBD_Kolos.Models;
using APBD_Kolos.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBD_Kolos.Controllers
{
    [ApiController]
    [Route("api/projects")]
    public class ProjectController : ControllerBase
    {
        SqlService tmp;
        public IConfiguration Configuration { get; set; }

        public ProjectController()
        {
            tmp = new SqlService();
        }

        [HttpGet("{id}")]
        public IActionResult GetProject(int id)
        {
            return tmp.getProjects(id);
        }

        [HttpPost]
        public IActionResult AddProject(AddTask proj)
        {
            
            return tmp.addProject(proj);
        }

    }
}
