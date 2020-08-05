using APBD_kolos2_powt_databaseFirst.Models;
using System;

namespace APBD_kolos2_powt_databaseFirst.Services
{
    public interface IDbContext
    {
        public ArtistInput getArtist(int id);
        public void chageDate(int artistID, int EventID, DateTime data);
    }
}
