package backend.sending;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class Recommendations {

    String city;
    String rain;
    String dayTemp;
    //String nightTemp;

    public Recommendations(String city) {

        this.city = city;
        getContent(city);
    }

    public static boolean elementHasClass(WebElement element, String active) {

        return Arrays.asList(element.getAttribute("class").split(" ")).contains(active);
    }

    public void getContent(String city) {

        System.setProperty("webdriver.chrome.driver", "chromedriver87.exe");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        WebDriver webDriver = new ChromeDriver(options);
        webDriver.get("https://koponyeg.hu/elorejelzes/" + city);

        WebElement element = webDriver.findElement(By.className("daily-forecast-col"));

        if(elementHasClass(element,"daily-forecast-col")) {

            String content = element.getText();
            String[] list = content.split("\n");
            //nightTemp = list[5];

            if (list.length == 7) {

                rain = list[4].substring(0, list[4].length() - 3);
                dayTemp = list[5].substring(0, list[5].length() - 1);
            } else {

                dayTemp = list[4].substring(0, list[4].length() - 1);
                rain = "0";
            }
            //System.out.println(dayTemp + "° " + rain + " mm");
        }
        webDriver.quit();
    }

    public String getRain() {
        return rain;
    }

    public String getDayTemp() {
        return dayTemp;
    }
}
