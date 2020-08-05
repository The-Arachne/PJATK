using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBK_KOLOS_2.DTO
{
    public class Championship_DTO
    {
        public Championship_DTO()
        {
            TeamList = new List<teamDTO>();
        }
        public string officialName { get; set; }
        public List<teamDTO> TeamList { get; set; }

    }
}
