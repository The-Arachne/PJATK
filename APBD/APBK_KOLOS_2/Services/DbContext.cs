using APBK_KOLOS_2.CustomExceptions;
using APBK_KOLOS_2.DTO;
using APBK_KOLOS_2.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBK_KOLOS_2.Services
{
    public class DbContext: IDbContext
    {
        s18635Context _context;
        public DbContext(s18635Context context)
        {
            _context = context;
        }

        public Championship_DTO getTeamsFrom(int id)
        {
            var champion = _context.Championship.Where(e => e.IdChampionship == id).FirstOrDefault();
            if (champion == null) throw new Exc_NotFound("Championship with this ID is not existing");

            Championship_DTO res = new Championship_DTO()
            {
                officialName = champion.OfficialName
            };

            var teams = from tim in _context.Team
                        join champ_tim in _context.ChampionshipTeam on tim.IdTeam equals champ_tim.IdTeam
                        where champ_tim.IdChampionship == id
                        orderby champ_tim.Score ascending
                        select new { tim.TeamName, tim.MaxAge, champ_tim.Score };
            if (teams == null) throw new Exc_NotFound("No teams to this champ");

            foreach(var x in teams)
            {
                teamDTO tmpTeam = new teamDTO()
                {
                    MaxAge=x.MaxAge,
                    Score=x.Score,
                    TeamName=x.TeamName
                };
                res.TeamList.Add(tmpTeam);
            }

            return res;

        }


        //BRAK CZASU NA SPRAWDZENIE CZY DZIALA
        public void setPlayerToTeam(int id, PlayerDTO players)
        {
            var team = _context.Team.Where(e => e.IdTeam == id).FirstOrDefault();
            if (team == null) throw new Exc_NotFound("Team with this ID is not existing");

            var player = _context.Player.Where(e => e.FirstName.Equals(players.firstName)&& e.LastName.Equals(players.lastName) && e.DateOfBirth==players.birthdate).FirstOrDefault();
            if (player == null) throw new Exc_NotFound("Player with this data is not existing");

            if (DateTime.Compare(player.DateOfBirth, DateTime.Now) > team.MaxAge) throw new Exc_BadRequest("Nieodpowiedni wiek");

            var teamPla = _context.PlayerTeam.Where(e=>e.IdPlayer==player.IdPlayer).FirstOrDefault();
            if(teamPla==null) throw new Exc_BadRequest("Gracz juz nalezy do druzyny");

            PlayerTeam xd = new PlayerTeam()
            {
                IdPlayer=player.IdPlayer,
          
                Comment=players.comment,
                IdPlayerTeam=_context.PlayerTeam.Select(e=>e.IdPlayerTeam).Last()+1,
                IdTeam=id,
                NumOnShirt=players.numOnShirt
            };

            _context.PlayerTeam.Add(xd);
            _context.SaveChanges();

        }
    }
}
