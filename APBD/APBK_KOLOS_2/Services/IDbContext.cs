using APBK_KOLOS_2.DTO;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBK_KOLOS_2.Services
{
    public interface IDbContext
    {
        public Championship_DTO getTeamsFrom(int id);
        public void setPlayerToTeam(int id,PlayerDTO player);
    }
}
