using CodeFirst.Models;
using CodeFirst.Services;
using Microsoft.AspNetCore.Mvc;

namespace CodeFirst.Controllers
{

    [ApiController]
    [Route("api/Doctor")]
    public class DoctorController : ControllerBase
    {
        IDbService idb;
        public DoctorController(IDbService IdbService)
        {
            idb = IdbService;
        }

        [HttpGet("{id}")]
        public IActionResult GetDoctor(int id)
        {
            return idb.GetDoctor(id);
        }

        [HttpPost]
        public IActionResult AddDoctor(Doctor doctor)
        {
            return idb.AddDoctor(doctor);
        }
        [HttpPost("{id}")]
        public IActionResult ModifyDoctor(int id,DoctorUp doctor)
        {
            return idb.ModifyDoctor(id, doctor);
        }
        [HttpDelete("{id}")]
        public IActionResult DeleteDoctor(int id)
        {
            return idb.DeleteDoctor(id);
        }
        [HttpPost("seed")]
        public IActionResult RandomSeed()
        {
            return idb.RandomSeed();
        }
    }
}