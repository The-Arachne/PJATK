using System.Collections.Generic;

namespace CodeFirst.Models
{
    public class DoctorUp
    {
        public string? FirstName { get; set; }
        public string? LastName { get; set; }
        public string? Email { get; set; }
        public virtual ICollection<Prescription>? Prescription { get; set; }
    }
}
