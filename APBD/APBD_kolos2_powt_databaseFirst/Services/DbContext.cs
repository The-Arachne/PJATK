using APBD_kolos2_powt_databaseFirst.CustomExceptions;
using APBD_kolos2_powt_databaseFirst.EF_Models;
using APBD_kolos2_powt_databaseFirst.Models;
using System;
using System.Linq;

namespace APBD_kolos2_powt_databaseFirst.Services
{
    public class DbContext : IDbContext
    {
        private readonly MasterContext _contx;
        public DbContext(MasterContext masterContext)
        {
            _contx = masterContext;
        }

        public void chageDate(int artistID, int EventID, DateTime data)
        {
            var ArtistTMP = _contx.Artist.Where(e => e.IdArtist == artistID).FirstOrDefault();
            if (ArtistTMP == null) throw new Exc_SthMissing("Artist with this ID is not existing");

            var EventTMP = _contx.Event.Where(e => e.IdEvent == EventID).FirstOrDefault();
            if (EventTMP == null) throw new Exc_SthMissing("Event with this ID is not existing");
            Console.WriteLine(EventTMP.Name+" "+data);
            if (EventTMP.StartDate >= data && EventTMP.EndDate <= data) throw new Exc_BadRequest("Date is not fitting the schedule xD");

            var Res = _contx.ArtistEvent.Where(e => e.IdArtist == artistID && e.IdEvent == EventID).FirstOrDefault();
            Res.PerformanceDate = data;
            _contx.SaveChanges();
        }

        public ArtistInput getArtist(int id)
        {
            var ArtistTMP = _contx.Artist.Where(e => e.IdArtist == id).FirstOrDefault();
            if (ArtistTMP == null) throw new Exc_SthMissing("Artist with this ID is not existing");

            ArtistInput res = new ArtistInput()
            {
                IdArtist = ArtistTMP.IdArtist,
                NickName=ArtistTMP.NickName
            };

            var Event = from art in _contx.Artist
                        join art_evn in _contx.ArtistEvent on art.IdArtist equals art_evn.IdArtist
                        join evn in _contx.Event on art_evn.IdEvent equals evn.IdEvent
                        where art.IdArtist == ArtistTMP.IdArtist
                        orderby evn.StartDate
                        select new { evn.IdEvent, evn.Name, evn.StartDate, evn.EndDate};

            if(!Event.Any()) throw new Exc_SthMissing("Artist with this ID havent participated in any event yet");

            foreach (var x in Event)
            {
                EventInput eventInput = new EventInput() 
                {
                    IdEvent=x.IdEvent,
                    Name=x.Name,
                    StartDate=x.StartDate,
                    EndDate= x.EndDate
                };

                res.EventParticipate.Add(eventInput);
            }
            return res;
        }
    }
}
