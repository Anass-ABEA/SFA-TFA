package net.thexcoders.authentications.reponse;

public class Response {
    public static final int STATUS_DONE = 1;
    public static final int STATUS_WAITING = 0;
    public static final int STATUS_REJECTED = -1;

    public static final String MSG_SUCCESS = "USER AUTHENTICATED SUCCESSFULLY!";
    public static final String MSG_FAILED = "AUTHENTICATION FAILED!";
    public static final String MSG_WAITING = "WAITING FOR USER TO COMPLETE AUTHENTICATION";

    private final int status;
    private final String message;

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static Response failure() {
        return new Response(STATUS_REJECTED, MSG_FAILED);
    }

    public static Response success() {
        return new Response(STATUS_DONE, MSG_SUCCESS);
    }

    public static Response waiting() {
        return new Response(STATUS_WAITING, MSG_WAITING);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "\n\n\n**** Response retrieved ****" +
                "\nstatus\t\t" + status +
                "\nmessage\t\t" + message;
    }

    public void display(){
        System.out.println(this);
    }
}
