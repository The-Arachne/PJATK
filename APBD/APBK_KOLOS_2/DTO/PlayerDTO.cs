using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBK_KOLOS_2.DTO
{
    public class PlayerDTO
    {
        public string firstName { get; set; }
        public string lastName { get; set; }
        public DateTime birthdate { get; set; }
        public int numOnShirt { get; set; }
        public string comment { get; set; }
    }
}
