using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PrzykladowyKolos.Services
{
    interface ISqlService
    {
        public IActionResult getSortedBy(string orderBy);
    }
}
