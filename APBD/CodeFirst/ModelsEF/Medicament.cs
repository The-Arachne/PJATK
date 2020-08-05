using System.Collections.Generic;

namespace CodeFirst.Models
{
	public class Medicament
    {
		public Medicament()
		{
			PrescriptionXMedicament = new HashSet<Prescription_Medicament>();
		}
		public int IdMedicament { get; set; }
		public string Name { get; set; }
		public string Description { get; set; }
		public string Type { get; set; }

		public virtual ICollection<Prescription_Medicament> PrescriptionXMedicament { get; set; }
	}
}
