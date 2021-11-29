package net.thexcoders.authentications.two_factor;

import net.thexcoders.authentications.Authentication;
import net.thexcoders.authentications.complexAuthentication;
import net.thexcoders.authentications.reponse.ComplexResponse;
import net.thexcoders.authentications.reponse.Response;
import net.thexcoders.authentications.requests.*;
import net.thexcoders.authentications.requests.second_fact.EmailRequest;
import net.thexcoders.authentications.server.Server;

public class TwoFactorAuthentication implements complexAuthentication, Authentication {

    @Override
    public Response connect(Request userRequest, Server server) {
        if (!userRequest.isValid()) return Response.failure();

        if (PasswordRequest.class.equals(userRequest.getClass())) {
            if (server.check((PasswordRequest) userRequest)) return Response.success();
        } else if (TokenRequest.class.equals(userRequest.getClass())) {
            if (server.check((TokenRequest) userRequest)) return Response.success();
        } else if (CodeRequest.class.equals(userRequest.getClass())) {
            if (server.check((CodeRequest) userRequest)) return Response.success();
        }
        return Response.failure();
    }


    @Override
    public Response connect(ComplexResponse response, Request userRequest, Server server) {

        if (response == null) {
            Response resp = connect(userRequest, server);
            if (resp.getStatus() == Response.STATUS_DONE) return ComplexResponse.waitingComplexResponse(userRequest);
        } else {
            Response resp = connect(userRequest, server, response.getRequestData());
            if (resp.getStatus() == Response.STATUS_DONE) return resp;
        }
        return Response.failure();
    }

    public Response connect(Request userRequest, Server server, Request preciousRequestData) {
        if (!userRequest.isValid()) return Response.failure();

        String data = (String) preciousRequestData.getData();

        if (EmailRequest.class.equals(userRequest.getClass())) {
            if (server.check((EmailRequest) userRequest, data)) return Response.success();
        }
        return Response.failure();
    }


}
