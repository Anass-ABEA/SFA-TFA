package net.thexcoders.authentications.requests;

public class PasswordRequest implements Request {

    private String login;
    private String password;

    public PasswordRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getData() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isValid() {
        return login.contains("@") && login.length() > 5 && password.length() >= 8;
    }
}
