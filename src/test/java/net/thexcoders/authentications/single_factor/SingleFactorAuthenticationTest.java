package net.thexcoders.authentications.single_factor;

import net.thexcoders.authentications.helper_class.StringHelper;
import net.thexcoders.authentications.reponse.Response;
import net.thexcoders.authentications.requests.CodeRequest;
import net.thexcoders.authentications.requests.PasswordRequest;
import net.thexcoders.authentications.requests.TokenRequest;
import net.thexcoders.authentications.server.Server;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.thexcoders.authentications.single_factor.MyAssertions.*;

class SingleFactorAuthenticationTest {
    Server server;
    SingleFactorAuthentication sfa;

    @BeforeEach
    void setUp() {
        server = new Server();
        sfa = new SingleFactorAuthentication();
    }

    @Test
    void testEmailAuth() {
        Response response;

        response = emailAuthHelper("john.doe@gmail.com", "password");
        assertDone(response);

        response = emailAuthHelper("john.doe@gmail.com", "passwords");
        assertRejected(response);

        response = emailAuthHelper("jane.doe@gmail.com", "123456");
        assertDone(response);

        response = emailAuthHelper("james.doe@gmail.com", "pass123");
        assertDone(response);

        response = emailAuthHelper("jame.doe@gmail.com", "pass123");
        assertRejected(response);
    }

    Response emailAuthHelper(String email, String password) {
        PasswordRequest pwdReq = new PasswordRequest(email,
                StringHelper.hashPassword(password)
        );
        return sfa.connect(pwdReq, server);
    }

    @Test
    void testAuthToken() {
        Response response;

        response = authTokenHelper("01234567890123456789012345678958",TokenRequest.TYPE_NUMERIC);
        assertDone(response);

        response = authTokenHelper("01234567890123456789012345678958",TokenRequest.TYPE_UPPER);
        assertRejected(response);

        response = authTokenHelper("E12A45678D012ZGF678F01ZDC45789C8",TokenRequest.TYPE_UPPER_NUMERIC);
        assertDone(response);

        response = authTokenHelper("e12z456r8b012eae678v01Zik457u9C8",TokenRequest.TYPE_LOWER_NUMERIC);
        assertRejected(response);

        response = authTokenHelper("ekabazdobvntpzntyjzenbxyopaoknax",TokenRequest.TYPE_LOWER_NUMERIC);
        assertDone(response);

        response = authTokenHelper("ekabazdobvffpzntyjzenbxyopaoknax",TokenRequest.TYPE_LOWER_NUMERIC);
        assertRejected(response);

    }
    Response authTokenHelper(String code, int type) {
        TokenRequest tokenReq = new TokenRequest(code,type);
        return sfa.connect(tokenReq, server);
    }


    @Test
    void testSingleUseCode(){
        Response response;

        response = singleUseCodeHelper("123456");
        assertDone(response);

        response = singleUseCodeHelper("714289");
        assertDone(response);

        response = singleUseCodeHelper("486189");
        assertRejected(response);

        response = singleUseCodeHelper("846583");
        assertDone(response);
    }

    Response singleUseCodeHelper(String code){
        CodeRequest codeReq = new CodeRequest(code);
        return sfa.connect(codeReq,server);
    }


}