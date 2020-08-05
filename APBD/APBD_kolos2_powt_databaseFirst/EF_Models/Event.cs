using System;
using System.Collections.Generic;

namespace APBD_kolos2_powt_databaseFirst.EF_Models
{
    public partial class Event
    {
        public Event()
        {
            ArtistEvent = new HashSet<ArtistEvent>();
            EventOrganiser = new HashSet<EventOrganiser>();
        }

        public int IdEvent { get; set; }
        public string Name { get; set; }
        public DateTime StartDate { get; set; }
        public DateTime EndDate { get; set; }

        public virtual ICollection<ArtistEvent> ArtistEvent { get; set; }
        public virtual ICollection<EventOrganiser> EventOrganiser { get; set; }
    }
}
