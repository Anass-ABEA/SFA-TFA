package net.thexcoders.authentications.single_factor;

import net.thexcoders.authentications.reponse.Response;
import org.junit.jupiter.api.Assertions;

public class MyAssertions extends Assertions {
    public static void assertDone(Response response) {
        assertEquals(Response.STATUS_DONE, response.getStatus());
    }

    public static void assertRejected(Response response) {
        assertEquals(Response.STATUS_REJECTED, response.getStatus());
    }

    public static void assertWaiting(Response response) {
        assertEquals(Response.STATUS_WAITING, response.getStatus());
    }
}
