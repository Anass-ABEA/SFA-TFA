package net.thexcoders.authentications.requests;

import net.thexcoders.authentications.helper_class.StringHelper;

public class CodeRequest implements Request {
    private static final int CODE_SIZE = 6;

    private final String code;

    public CodeRequest(String code) {
        this.code = StringHelper.spaceRemover(code);
    }


    public String getData() {
        return code;
    }

    @Override
    public boolean isValid() {
       return code.length() == CODE_SIZE && isFormatCorrect();
    }

    private boolean isFormatCorrect(){
        return StringHelper.check(code,true,false,false);
    }

}
