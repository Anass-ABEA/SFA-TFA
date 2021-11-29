package net.thexcoders.authentications.server;

import net.thexcoders.authentications.helper_class.StringHelper;
import net.thexcoders.authentications.requests.CodeRequest;
import net.thexcoders.authentications.requests.second_fact.EmailRequest;
import net.thexcoders.authentications.requests.PasswordRequest;
import net.thexcoders.authentications.requests.TokenRequest;

import java.util.HashMap;

public class Server {
    private final HashMap<String, String> passwordDB; // EMAIL -> PASSWORD
    private final HashMap<String, Boolean> tokenDB; // TOKEN -> isActive
    private final HashMap<String, Boolean> codeDB; // CODE -> isActive

    private final HashMap<String, String> emailVerificationCodes; // EMAIL -> code

    public Server() {
        passwordDB = new HashMap<>();
        tokenDB = new HashMap<>();
        codeDB = new HashMap<>();
        emailVerificationCodes = new HashMap<>();
        fillDatabase();
    }

    private void fillDatabase() {
        passwordDB.put("john.doe@gmail.com", StringHelper.hashPassword("password"));
        passwordDB.put("jane.doe@gmail.com", StringHelper.hashPassword("123456"));
        passwordDB.put("james.doe@gmail.com", StringHelper.hashPassword("pass123"));

        tokenDB.put("01234567890123456789012345678958", true);
        tokenDB.put("E12A45678D012ZGF678F01ZDC45789C8", true);
        tokenDB.put("e12z456r8b012eae678v01Zik457u9C8", false);
        tokenDB.put("ekabazdobvntpzntyjzenbxyopaoknax", true);

        codeDB.put("123456", true);
        codeDB.put("714289", true);
        codeDB.put("486189", false);
        codeDB.put("846583", true);

        emailVerificationCodes.put("john.doe@gmail.com", "714289");
        emailVerificationCodes.put("jane.doe@gmail.com", "846583");
        emailVerificationCodes.put("james.doe@gmail.com", "486189");
    }

    public boolean check(PasswordRequest userRequest) {
        String login = userRequest.getData();
        String password = userRequest.getPassword();
        String item = passwordDB.get(login);
        return item != null && item.equals(password);
    }

    public boolean check(TokenRequest userRequest) {  // when a token is used it expires
        String token = userRequest.getData();
        Boolean item = tokenDB.get(token);
        if (item != null && item) {
            tokenDB.put(token, false);
            return true;
        }
        return false;
    }

    public boolean check(CodeRequest userRequest) { // when a code is used it expires
        String code = userRequest.getData();
        Boolean item = codeDB.get(code);
        if (item != null && item) {
            codeDB.put(code, false);
            return true;
        }
        return false;
    }

    public boolean check(EmailRequest userRequest, String email) {
        String code = userRequest.getData();
        String emailCode = emailVerificationCodes.get(email);
        return emailCode != null && emailCode.equals(code);
    }


}
