using System;
using System.Collections.Generic;

namespace CodeFirst.Models
{
	public class Prescription
    {
		public Prescription()
		{
			PrescriptionXMedicament = new HashSet<Prescription_Medicament>();
		}
		public int IdPrescription { get; set; }
		public DateTime Date { get; set; }
		public DateTime DueDate { get; set; }
		public int IdPatient { get; set; }
		public int IdDoctor { get; set; }

		public virtual Patient Patient { get; set; }
		public virtual Doctor Doctor { get; set; }

		public virtual ICollection<Prescription_Medicament> PrescriptionXMedicament { get; set; }
	}
}
