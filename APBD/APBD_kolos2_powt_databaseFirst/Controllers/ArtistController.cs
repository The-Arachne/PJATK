using APBD_kolos2_powt_databaseFirst.CustomExceptions;
using APBD_kolos2_powt_databaseFirst.Services;
using Microsoft.AspNetCore.Mvc;
using System;

namespace APBD_kolos2_powt_databaseFirst.Controllers
{
    [Route("api/artists")]
    [ApiController]
    public class ArtistController : ControllerBase
    {
        private readonly IDbContext _db;
        public ArtistController(IDbContext db)
        {
            _db = db;
        }

        [HttpGet("{id}")]
        public IActionResult getArtist(int id)
        {
            try
            {
                var res = _db.getArtist(id);
                return Ok(res);
            }catch(Exc_SthMissing e)
            {
                return NotFound(e.Message); 
            }
        }

        [HttpPut("{id}/events/{id2}")]
        public IActionResult changeDate(int id,int id2, DateTime performaceDate)
        {
            try
            {
                _db.chageDate(id, id2, performaceDate);
                return Ok();
            }catch(Exc_SthMissing e)
            {
                return NotFound(e.Message);
            }
            catch(Exc_BadRequest e)
            {
                return BadRequest(e.Message);
            }
        }
    }
}