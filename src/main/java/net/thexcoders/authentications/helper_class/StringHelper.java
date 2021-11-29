package net.thexcoders.authentications.helper_class;

import aQute.libg.cryptography.SHA1;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class StringHelper {
    public static boolean check(String value,boolean numeric, boolean upper, boolean lower) {
        for (char c : value.toCharArray()) {
            if (!numeric) if (c < '9' && c > '0') return false;
            if (!upper)   if (c < 'Z' && c > 'A') return false;
            if (!lower)   if (c < 'z' && c > 'a') return false;
        }
        return true;
    }

    public static String hashPassword(String password)  {
        try{
            return Base64.getEncoder().encodeToString(SHA1.digest(password.getBytes(StandardCharsets.UTF_8)).digest());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String spaceRemover(String code){
        return code.replaceAll(" ","");
    }
}
