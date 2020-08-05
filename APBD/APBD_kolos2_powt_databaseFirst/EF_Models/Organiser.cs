using System;
using System.Collections.Generic;

namespace APBD_kolos2_powt_databaseFirst.EF_Models
{
    public partial class Organiser
    {
        public Organiser()
        {
            EventOrganiser = new HashSet<EventOrganiser>();
        }

        public int IdOrganiser { get; set; }
        public string Name { get; set; }

        public virtual ICollection<EventOrganiser> EventOrganiser { get; set; }
    }
}
