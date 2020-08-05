using System.Collections.Generic;

namespace APBD_kolos2_powt_databaseFirst.Models
{
    public class ArtistInput
    {
        public ArtistInput()
        {
            EventParticipate = new List<EventInput>();
        }
        public int IdArtist { get; set; }
        public string NickName { get; set; }
        public List<EventInput> EventParticipate { get; set; }
    }
}
