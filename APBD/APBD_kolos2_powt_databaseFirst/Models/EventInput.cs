using System;

namespace APBD_kolos2_powt_databaseFirst.Models
{
    public class EventInput
    {
        public int IdEvent { get; set; }
        public string Name { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime EndDate { get; set; }
    }
}