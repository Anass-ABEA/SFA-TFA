package net.thexcoders.authentications.requests.second_fact;

import net.thexcoders.authentications.helper_class.StringHelper;
import net.thexcoders.authentications.requests.Request;

import java.util.Date;

public class EmailRequest implements Request {
    private static final int CODE_SIZE = 6;
    private static final int EXPIRES_AFTER_MILLIS = 20000; // 20 seconds

    private final Date expirationDate;
    private final String emailCode;

    public EmailRequest(String code) {
        this.emailCode = StringHelper.spaceRemover(code);
        this.expirationDate = new Date(new Date().getTime() + EXPIRES_AFTER_MILLIS);
    }

    public String getData() {
        return emailCode;
    }

    @Override
    public boolean isValid() {
        return emailCode.length() == CODE_SIZE && isFormatCorrect() && isDateNotExpired();
    }

    private boolean isDateNotExpired() {
        return expirationDate.getTime() > new Date().getTime();
    }

    private boolean isFormatCorrect() {
        return StringHelper.check(emailCode, true, false, false);
    }

}
