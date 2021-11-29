package net.thexcoders.authentications;

import net.thexcoders.authentications.reponse.Response;
import net.thexcoders.authentications.requests.Request;
import net.thexcoders.authentications.server.Server;

public interface Authentication {
    Response connect(Request userRequest, Server server);
}
