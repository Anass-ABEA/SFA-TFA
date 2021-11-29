package net.thexcoders.authentications.requests;

public interface Request {
    boolean isValid();
    Object getData();
}
