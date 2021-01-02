package backend.connection;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.lang.*;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;


public class Connection {
    String site = "köpönyeg";
    String searchSite = "https://google.com";

    public void setChromeDriver() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver87.exe");
        ChromeDriver driver = new ChromeDriver();
        setPage(driver);
    }

    public void setFireFoxDriver() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
        FirefoxDriver driver = new FirefoxDriver();
        setPage(driver);
    }

    public void setPage(WebDriver driver) throws InterruptedException {
        driver.navigate().to(searchSite);
        driver.manage().window().maximize();
        WebElement searchBox = driver.findElement(By.name("q"));
        Thread.sleep(5000);
        searchBox.sendKeys(site + Keys.RETURN);
        Thread.sleep(5000);
        List<WebElement> findElements = driver.findElements(By.xpath("//*[@id='rso']//h3/a"));
        findElements.get(0).click();
        searchBox.submit();
        System.out.println(driver.getTitle());
        driver.quit();
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getSearchSite() {
        return searchSite;
    }

    public void setSearchSite(String searchSite) {
        this.searchSite = searchSite;
    }


}
