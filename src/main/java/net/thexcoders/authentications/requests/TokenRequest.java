package net.thexcoders.authentications.requests;

import net.thexcoders.authentications.helper_class.StringHelper;

public class TokenRequest implements Request {
    private static final int TOKEN_SIZE = 32;

    public static final int TYPE_NUMERIC = 0 ;
    public static final int TYPE_UPPER = 1 ;
    public static final int TYPE_UPPER_NUMERIC = 2;
    public static final int TYPE_LOWER_NUMERIC = 3;
    public static final int TYPE_LOWER_UPPER_NUMERIC = 4;

    private final String token;
    private final int tokenType;

    public TokenRequest(String token, int tokenType) {
        this.token = token;
        this.tokenType = tokenType;
    }

    public String getData() {
        return token;
    }

    public int getTokenType() {
        return tokenType;
    }

    @Override
    public boolean isValid() {
        if (token.length() != TOKEN_SIZE) return false;

        switch (tokenType){
            case TYPE_NUMERIC:
                return StringHelper.check(token,true,false,false);
            case TYPE_UPPER:
                return StringHelper.check(token,false,true,false);
            case TYPE_UPPER_NUMERIC:
                return StringHelper.check(token,true,true,false);
            case TYPE_LOWER_NUMERIC:
                return StringHelper.check(token,true,false,true);
            case TYPE_LOWER_UPPER_NUMERIC:
                return StringHelper.check(token,true,true,true);
        }
        return false;
    }

}
