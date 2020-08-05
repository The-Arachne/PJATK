using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using APBK_KOLOS_2.CustomExceptions;
using APBK_KOLOS_2.DTO;
using APBK_KOLOS_2.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace APBK_KOLOS_2.Controllers
{
    [Route("api/teams")]
    [ApiController]
    public class TeamController : ControllerBase
    {
        IDbContext _db;
        public TeamController(IDbContext db)
        {
            _db = db;
        }

        [HttpPost("{id}/players")]
        public IActionResult setPlayerToTeam(int id,PlayerDTO player)
        {
            try
            {
                _db.setPlayerToTeam(id, player);
                return Ok();
            }catch(Exc_BadRequest exc) { return BadRequest(exc.Message); }
            catch(Exc_NotFound exc) { return NotFound(exc.Message); }
           
        }

    }
}