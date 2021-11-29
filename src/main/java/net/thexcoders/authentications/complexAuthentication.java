package net.thexcoders.authentications;

import net.thexcoders.authentications.reponse.ComplexResponse;
import net.thexcoders.authentications.reponse.Response;
import net.thexcoders.authentications.requests.Request;
import net.thexcoders.authentications.server.Server;

public interface complexAuthentication {
    Response connect(ComplexResponse response, Request userRequest, Server server);
    Response connect(Request userRequest, Server server, Request requestData);
}
