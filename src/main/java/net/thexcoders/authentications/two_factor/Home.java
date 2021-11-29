package net.thexcoders.authentications.two_factor;

import net.thexcoders.authentications.helper_class.StringHelper;
import net.thexcoders.authentications.reponse.ComplexResponse;
import net.thexcoders.authentications.reponse.Response;
import net.thexcoders.authentications.requests.CodeRequest;
import net.thexcoders.authentications.requests.PasswordRequest;
import net.thexcoders.authentications.requests.TokenRequest;
import net.thexcoders.authentications.requests.second_fact.EmailRequest;
import net.thexcoders.authentications.server.Server;

public class Home {

    public static Response passwordAuth(String login, String password) {
        TwoFactorAuthentication tfa = new TwoFactorAuthentication();
        PasswordRequest pwdReq = new PasswordRequest(login, StringHelper.hashPassword(password));
        Server server = new Server();
        Response response = tfa.connect(null, pwdReq, server);
        response.display();
        return response;
    }

    public static void passwordAuthPartTwo(Response response, String code) {
        TwoFactorAuthentication tfa = new TwoFactorAuthentication();
        Server server = new Server();
        EmailRequest secondFactor = new EmailRequest(code);
        response = tfa.connect((ComplexResponse) response, secondFactor, server);
        response.display();
    }


    public static void main(String[] args) {
        Response resp;

        resp = passwordAuth("john.doe@gmail.com", "password");
        passwordAuthPartTwo(resp,"714289");

        passwordAuth("john.doe@gmail.com", "passwords"); // FAILS DUE TO INCORRECT PASSWORD

        resp = passwordAuth("john.doe@gmail.com", "password");
        passwordAuthPartTwo(resp,"714299");

    }
}
