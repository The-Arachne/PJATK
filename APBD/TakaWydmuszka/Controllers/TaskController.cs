using Microsoft.AspNetCore.Mvc;
using TakaWydmuszka.CustomExceptions;
using TakaWydmuszka.DTO_s;
using TakaWydmuszka.Services;

namespace TakaWydmuszka.Controllers
{
    [Route("api/tasks")]
    [ApiController]
    public class TaskController : ControllerBase
    {
        private IDbContext _db;

        public TaskController(IDbContext db)
        {
            _db = db;
        }

        [HttpPost]
        public IActionResult addTask(Task_Input task)
        {
            try {
                _db.addTask(task);
                return Ok();
            }catch(Exc_NotFound exc) { return NotFound(exc.Message); }
            catch (Exc_BadRequest exc) { return BadRequest(exc.Message); }
        }
    }
}