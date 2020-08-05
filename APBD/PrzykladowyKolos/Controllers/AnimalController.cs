using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using PrzykladowyKolos.Services;

namespace PrzykladowyKolos.Controllers
{
    [ApiController]
    [Route("api/animals")]
    public class AnimalController:ControllerBase
    {
        SqlService tmp;
        public IConfiguration Configuration { get; set; }

        public AnimalController()
        {
            tmp = new SqlService();
        }

        [HttpGet]
        public IActionResult GetAnimals(string sortBy)
        {
            return tmp.getSortedBy(sortBy);
        }

        [HttpPost]
        public IActionResult AddAnimal()
        {
            return Ok();
        }

    }
}
