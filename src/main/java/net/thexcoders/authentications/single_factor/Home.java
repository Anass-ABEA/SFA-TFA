package net.thexcoders.authentications.single_factor;

import net.thexcoders.authentications.helper_class.StringHelper;
import net.thexcoders.authentications.reponse.Response;
import net.thexcoders.authentications.requests.CodeRequest;
import net.thexcoders.authentications.requests.PasswordRequest;
import net.thexcoders.authentications.requests.TokenRequest;
import net.thexcoders.authentications.server.Server;

public class Home {

    public static void passwordAuth(String login , String password){
        SingleFactorAuthentication sfa = new SingleFactorAuthentication();
        PasswordRequest pwdReq = new PasswordRequest(login, StringHelper.hashPassword(password));
        Server server = new Server();
        Response response = sfa.connect(pwdReq,server);
        response.display();
    }

    public static void codeAuth(String code){
        SingleFactorAuthentication sfa = new SingleFactorAuthentication();
        CodeRequest codeReq = new CodeRequest(code);
        Server server = new Server();
        Response response = sfa.connect(codeReq,server);
        response.display();
    }

    public static void tokenAuth(String token, int type){
        SingleFactorAuthentication sfa = new SingleFactorAuthentication();
        TokenRequest tokenReq = new TokenRequest(token,type);
        Server server = new Server();
        Response response = sfa.connect(tokenReq,server);
        response.display();
    }

    public static void main(String[] args) {

        // something you know
        passwordAuth("john.doe@gmail.com", "password");
        passwordAuth("john.doe@gmail.com", "passwords"); // FAILS DUE TO INCORRECT PASSWORD

        // something you have • Token
        tokenAuth("E12A45678D012ZGF678F01ZDC45789C8",TokenRequest.TYPE_UPPER_NUMERIC);
        tokenAuth("e12z456r8b012eae678v01Zik457u9C8",TokenRequest.TYPE_UPPER); // FAILS DUE TO INVALID TOKEN
        tokenAuth("a2z456r8b012eae678v01Zik457u9C8",TokenRequest.TYPE_UPPER); // FAILS DUE TO INEXISTANT TOKEN
        tokenAuth("01234567890123456789012345678958",TokenRequest.TYPE_NUMERIC);

        // something you have • Code
        codeAuth("714289");
        codeAuth("486189"); // FAILS DUE TO INVALID CODE
        codeAuth("846583");
        codeAuth("126583");  // FAILS DUE TO INEXISTANT CODE

    }
}
