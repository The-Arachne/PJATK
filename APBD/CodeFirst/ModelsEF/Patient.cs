using System;
using System.Collections.Generic;

namespace CodeFirst.Models
{
    public class Patient
    {
        public Patient()
        {
            Prescription = new HashSet<Prescription>();
        }
        public int IdPatient { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public DateTime BithDate { get; set; }

        public virtual ICollection<Prescription> Prescription { get; set; }
    }
}
