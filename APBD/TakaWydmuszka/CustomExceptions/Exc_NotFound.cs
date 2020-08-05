using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace TakaWydmuszka.CustomExceptions
{
    public class Exc_NotFound:Exception
    {
        public Exc_NotFound()
        {

        }
        public Exc_NotFound(string message):base(message)
        {

        }
        public Exc_NotFound(string message,Exception inner):base(message, inner)
        {

        }
    }
}
