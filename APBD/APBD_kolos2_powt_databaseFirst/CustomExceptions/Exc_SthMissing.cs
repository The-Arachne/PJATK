using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBD_kolos2_powt_databaseFirst.CustomExceptions
{
    public class Exc_SthMissing: Exception
    {
        public Exc_SthMissing()
        {

        }
        public Exc_SthMissing(string mssg):base(mssg)
        {

        }
        public Exc_SthMissing(string mssg,Exception inner) : base(mssg,inner)
        {

        }
    }
}
