using IntegrationTests.DTO;
using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IntegrationTests
{
    class UserTester
    {
        Authentication auth = new Authentication();

        [Test]
        [Category("authorization")]
        public void TestAuthorization()
        {
            UserDTO user = RequestHandler.GetUser();
            Assert.AreNotEqual(null, user);
        }

        [Test]
        [Category("authorization")]
        public void TestPassword_ShouldBeNull()
        {
            UserDTO user = RequestHandler.GetUser();
            Assert.AreEqual(null, user.principal.password);
        }

        [Test]
        [Category("authorization")]
        public void TestUsername_ShouldEqualAuthorizations()
        {
            UserDTO user = RequestHandler.GetUser();
            Assert.AreEqual(auth.GetCredential(null, null).UserName.ToLower(), user.principal.username.ToLower());
        }

        [Test]
        [Category("authorization")]
        public void TestUser_ShouldBeAuthenticated()
        {
            UserDTO user = RequestHandler.GetUser();
            Assert.AreEqual(true, user.authenticated);
        }

        [Test]
        [Category("authorization")]
        public void TestUser_RemoteAddressShouldBeFilled()
        {
            UserDTO user = RequestHandler.GetUser();
            Assert.AreNotEqual(null, user.details.remoteAddress);
        }

        [Test]
        [Category("authorization")]
        public void TestUser_AuthorityShouldBeFan()
        {
            UserDTO user = RequestHandler.GetUser();
            Assert.AreEqual("ROLE_FAN", user.authorities[0].authority);
        }

        [Test]
        [Category("authorization")]
        public void TestUser_PrincipalAuthorityShouldBeFan()
        {
            UserDTO user = RequestHandler.GetUser();
            Assert.AreEqual("ROLE_FAN", user.principal.authorities[0].authority);
        }

        [Test]
        [Category("authorization")]
        public void TestUser_ShouldNotBeExpired()
        {
            UserDTO user = RequestHandler.GetUser();
            Assert.AreEqual(true, user.principal.accountNonExpired);
        }

        [Test]
        [Category("authorization")]
        public void TestUser_ShouldNotBeLocked()
        {
            UserDTO user = RequestHandler.GetUser();
            Assert.AreEqual(true, user.principal.accountNonLocked);
        }

        [Test]
        [Category("authorization")]
        public void TestUser_CredentialsShouldNotBeExpired()
        {
            UserDTO user = RequestHandler.GetUser();
            Assert.AreEqual(true, user.principal.credentialsNonExpired);
        }

        [Test]
        [Category("authorization")]
        public void TestUser_ShouldBeEnabled()
        {
            UserDTO user = RequestHandler.GetUser();
            Assert.AreEqual(true, user.principal.enabled);
        }

        [Test]
        [Category("authorization")]
        public void TestUser_CredentialsShouldBeNull()
        {
            UserDTO user = RequestHandler.GetUser();
            Assert.AreEqual(null, user.credentials);
        }
    }
}
