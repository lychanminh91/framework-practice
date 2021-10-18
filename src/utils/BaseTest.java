package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private WebDriver driver;
    private enum BROWSER {
        FIREFOX, CHROME, SAFARI
    }

    protected WebDriver getBrowserDriver(String browserName, String url){
        BROWSER browser = BROWSER.valueOf(browserName.toUpperCase());
        if(browser == BROWSER.FIREFOX){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if(browser == BROWSER.CHROME){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else {
            throw new RuntimeException("Invalid browser");
        }

        driver.manage().timeouts().implicitlyWait(GlobalConstants.TIME_OUT, TimeUnit.SECONDS);
        driver.get(url);
        return driver;
    }

}
