package net.thexcoders.authentications.reponse;

import net.thexcoders.authentications.requests.Request;

public class ComplexResponse extends Response {

    private final Request requestData;

    public ComplexResponse(Request request, int status, String message) {
        super(status, message);
        this.requestData = request;
    }

    public static ComplexResponse waitingComplexResponse(Request request) {
        Response resp = Response.waiting();
        return new ComplexResponse(request, resp.getStatus(), resp.getMessage());
    }

    public Request getRequestData() {
        return requestData;
    }
}
