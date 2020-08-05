using CodeFirst.Models;
using Microsoft.AspNetCore.Mvc;

namespace CodeFirst.Services
{
    public interface IDbService
    {
        public IActionResult GetDoctor(int id);
        public IActionResult AddDoctor(Doctor doctor);
        public IActionResult ModifyDoctor(int id,DoctorUp doctor);
        public IActionResult DeleteDoctor(int id);
        public IActionResult RandomSeed();
    }
}
