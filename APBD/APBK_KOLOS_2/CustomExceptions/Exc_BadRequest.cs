using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace APBK_KOLOS_2.CustomExceptions
{
    public class Exc_BadRequest : Exception
    {
        public Exc_BadRequest()
        {

        }
        public Exc_BadRequest(string message) : base(message)
        {

        }
        public Exc_BadRequest(string message, Exception inner) : base(message, inner)
        {

        }
    }
}
