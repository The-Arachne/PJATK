using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBD_kolos2_powt_databaseFirst.CustomExceptions
{
    public class Exc_BadRequest: Exception
    {
        public Exc_BadRequest()
        {

        }
        public Exc_BadRequest(string mssg) : base(mssg)
        {

        }
        public Exc_BadRequest(string mssg, Exception inner) : base(mssg, inner)
        {

        }
    }
}
