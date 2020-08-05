using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using TakaWydmuszka.CustomExceptions;
using TakaWydmuszka.Services;

namespace TakaWydmuszka.Controllers
{
    [Route("api/projects")]
    [ApiController]
    public class ProjectController : ControllerBase
    {
        private IDbContext _db;
        public ProjectController(IDbContext db)
        {
            _db=db;
        }

        [HttpGet("{id}")]
        public IActionResult getProject(int id)
        {
            try
            {
               var res=_db.getProject(id);
               return Ok(res);
            }catch(Exc_NotFound exc) { return NotFound(exc.Message); }
        }
    }
}