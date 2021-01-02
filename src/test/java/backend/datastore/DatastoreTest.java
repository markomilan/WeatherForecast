package backend.datastore;

import java.io.FileWriter;
import java.io.IOException;
import backend.datastore.Datastore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatastoreTest {

    @Test
    public void sendEmailAdress() {
        String email = "abc";
        String city = "abc";

        Datastore datastore = new Datastore(email, city);
        assertEquals(email,"abc");
    }

    @Test
    public void CheckEmailValidityWrong() {
        String email = "abc";
        String city = "abc";

        Datastore datastore = new Datastore(email, city);
        assertEquals(false,datastore.CheckValidity(email, city));
    }

    @Test
    public void CheckEmailValidityCorrect() {
        String email = "a@b.com";
        String city = "abc";

        Datastore datastore = new Datastore(email, city);
        assertEquals(true,datastore.CheckValidity(email, city));
    }

    @Test
    public void CheckCityValidityWrong() {
        String email = "a@b.com";
        String city = "";

        Datastore datastore = new Datastore(email, city);
        assertEquals(false,datastore.CheckValidity(email, city));
    }

    @Test
    public void CheckCityValidityCorrect() {
        String email = "a@b.com";
        String city = "abc";

        Datastore datastore = new Datastore(email, city);
        assertEquals(true,datastore.CheckValidity(email, city));
    }

}
