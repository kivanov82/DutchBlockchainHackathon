using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IntegrationTests.DTO
{
    public class UserDTO
    {
        [JsonProperty("details")]
        public Details details { get; set; }
        [JsonProperty("authorities")]
        public Authority[] authorities { get; set; }
        [JsonProperty("authenticated")]
        public bool authenticated { get; set; }
        [JsonProperty("principal")]
        public Principal principal { get; set; }
        [JsonProperty("credentials")]
        public object credentials { get; set; }
        [JsonProperty("name")]
        public string name { get; set; }
    }

    public class Details
    {
        [JsonProperty("remoteAddress")]
        public string remoteAddress { get; set; }
        [JsonProperty("sessionId")]
        public string sessionId { get; set; }
    }

    public class Principal
    {
        [JsonProperty("password")]
        public object password { get; set; }
        [JsonProperty("username")]
        public string username { get; set; }
        [JsonProperty("authorities")]
        public Authority[] authorities { get; set; }
        [JsonProperty("accountNonExpired")]
        public bool accountNonExpired { get; set; }
        [JsonProperty("accountNonLocked")]
        public bool accountNonLocked { get; set; }
        [JsonProperty("credentialsNonExpired")]
        public bool credentialsNonExpired { get; set; }
        [JsonProperty("enabled")]
        public bool enabled { get; set; }
    }

    public class Authority
    {
        [JsonProperty("authority")]
        public string authority { get; set; }
    }
}
