using System;
using System.Collections.Generic;

namespace APBD_kolos2_powt_databaseFirst.EF_Models
{
    public partial class Artist
    {
        public Artist()
        {
            ArtistEvent = new HashSet<ArtistEvent>();
        }

        public int IdArtist { get; set; }
        public string NickName { get; set; }

        public virtual ICollection<ArtistEvent> ArtistEvent { get; set; }
    }
}
