using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBK_KOLOS_2.DTO
{
    public class teamDTO
    {
        public teamDTO()
        {
            Score = 0;
        }
        public string TeamName { get; set; }
        public int MaxAge { get; set; }
        public float? Score { get; set; }
    }
}
