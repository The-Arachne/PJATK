using System;
using System.Collections.Generic;

namespace APBD_kolos2_powt_databaseFirst.EF_Models
{
    public partial class EventOrganiser
    {
        public int IdEvent { get; set; }
        public int IdOrganiser { get; set; }

        public virtual Event IdEventNavigation { get; set; }
        public virtual Organiser IdOrganiserNavigation { get; set; }
    }
}
