using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using APBK_KOLOS_2.CustomExceptions;
using APBK_KOLOS_2.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace APBK_KOLOS_2.Controllers
{
    [Route("api/championships")]
    [ApiController]
    public class ChampionshipController : ControllerBase
    {
        IDbContext _db;
        public ChampionshipController(IDbContext db)
        {
            _db = db;
        }
        [HttpGet("{id}/teams")]
        public IActionResult getTeamsInChampionship(int id)
        {
            try
            {
                var res = _db.getTeamsFrom(id);
                return Ok(res);
            }
            catch(Exc_NotFound exc) { return NotFound(exc.Message); }
        }
    }
}