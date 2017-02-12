using IntegrationTests.DTO;
using Newtonsoft.Json;
using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace IntegrationTests
{
    public static class RequestHandler
    {
        private const string connectionString = "http://localhost:9090/";
        private static Authentication authentication = new Authentication();

        public static UserDTO GetUser()
        {
            return FromJSON<UserDTO>(GetUserResponseString());
        }

        private static string GetUserResponseString()
        {
            WebRequest request = WebRequest.Create(connectionString + "user");
            request.Credentials = authentication.GetCredential(null, null);
            WebResponse response = request.GetResponse();
            // Display the status.  
            Console.WriteLine(((HttpWebResponse)response).StatusDescription);
            // Get the stream containing content returned by the server.  
            Stream dataStream = response.GetResponseStream();
            // Open the stream using a StreamReader for easy access.  
            StreamReader reader = new StreamReader(dataStream);
            // Read the content.  
            string responseFromServer = reader.ReadToEnd();
            // Display the content.  
            Console.WriteLine(responseFromServer);
            // Clean up the streams and the response.  
            reader.Close();
            response.Close();

            return responseFromServer;
        }

        public static string ToJSON(Object anObject)
        {
            return JsonConvert.SerializeObject(
                   anObject,
                   Formatting.Indented,
                   new JsonSerializerSettings { NullValueHandling = NullValueHandling.Ignore, StringEscapeHandling = StringEscapeHandling.EscapeHtml });
        }

        // deserialize JSON to a type <T>
        public static T FromJSON<T>(string json) where T : new()
        {
            if (json == "")
                return new T();
            else
                return JsonConvert.DeserializeObject<T>(json);
        }
    }
}
