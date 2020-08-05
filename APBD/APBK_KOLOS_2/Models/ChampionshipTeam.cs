using System;
using System.Collections.Generic;

namespace APBK_KOLOS_2.Models
{
    public partial class ChampionshipTeam
    {
        public int IdChampionshipTeam { get; set; }
        public int IdTeam { get; set; }
        public int IdChampionship { get; set; }
        public float? Score { get; set; }

        public virtual Championship IdChampionshipNavigation { get; set; }
        public virtual Team IdTeamNavigation { get; set; }
    }
}
