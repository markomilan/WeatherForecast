package backend.connection;

import backend.datastore.Datastore;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.lang.*;
import java.util.List;
import backend.connection.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ConnectionTest {
    Connection connection = new Connection();

    @Test
    public void checkSite() {
        connection.setSite("idokep");
        assertEquals(connection.getSite(),"idokep");
    }

    @Test
    public void checkBrowser() {
        connection.setSearchSite("https://google.com");
        assertEquals(connection.getSearchSite(),"https://google.com");
    }

}
