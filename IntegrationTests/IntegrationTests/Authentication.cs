using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace IntegrationTests
{
    public class Authentication: ICredentials
    {
        public const string username = "Fan";
        public const string password = "password";
        public const string authenticationType = "basic";

        public NetworkCredential GetCredential(Uri uri, string authType)
        {
            return new NetworkCredential(username, password);
        }
    }
}
