package net.thexcoders.authentications.single_factor;

import net.thexcoders.authentications.Authentication;
import net.thexcoders.authentications.reponse.Response;
import net.thexcoders.authentications.requests.*;
import net.thexcoders.authentications.server.Server;

public class SingleFactorAuthentication implements Authentication {

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


}
