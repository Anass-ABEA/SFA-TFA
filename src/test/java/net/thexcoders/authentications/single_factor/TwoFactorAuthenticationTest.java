package net.thexcoders.authentications.single_factor;

import net.thexcoders.authentications.helper_class.StringHelper;
import net.thexcoders.authentications.reponse.ComplexResponse;
import net.thexcoders.authentications.reponse.Response;
import net.thexcoders.authentications.requests.PasswordRequest;
import net.thexcoders.authentications.requests.second_fact.EmailRequest;
import net.thexcoders.authentications.server.Server;
import net.thexcoders.authentications.two_factor.TwoFactorAuthentication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.thexcoders.authentications.single_factor.MyAssertions.*;

class TwoFactorAuthenticationTest {
    Server server;
    TwoFactorAuthentication tfa;

    @BeforeEach
    void setUp() {
        server = new Server();
        tfa = new TwoFactorAuthentication();
    }

    public Response passwordAuth(String login, String password) {
        TwoFactorAuthentication tfa = new TwoFactorAuthentication();
        PasswordRequest pwdReq = new PasswordRequest(login, StringHelper.hashPassword(password));
        Server server = new Server();
        Response response = tfa.connect(null, pwdReq, server);
        response.display();
        return response;
    }

    public Response passwordAuthPartTwo(Response response, String code) {
        TwoFactorAuthentication tfa = new TwoFactorAuthentication();
        Server server = new Server();
        EmailRequest secondFactor = new EmailRequest(code);
        response = tfa.connect((ComplexResponse) response, secondFactor, server);
        response.display();
        return response;
    }


    @Test
    void testDone() {
        Response response;

        response = passwordAuth("john.doe@gmail.com", "password");
        assertWaiting(response);
        response = passwordAuthPartTwo(response,"714289");
        assertDone(response);


    }

    @Test
    void testRejectedAuth(){
        Response response;
        response = passwordAuth("john.doe@gmail.com", "passwords");
        assertRejected(response);
    }

    @Test
    void testRejectedCode(){
        Response response;
        response = passwordAuth("john.doe@gmail.com", "password");
        response = passwordAuthPartTwo(response,"123456");
        assertRejected(response);
    }

    @Test
    void testWaiting(){
        Response response;

        response = passwordAuth("jane.doe@gmail.com", "123456");
        assertWaiting(response);
    }


}