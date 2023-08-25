package automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;


public class DriverSetUP {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(String browserName) {
        if (browserName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            //chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--silent");
            chromeOptions.addArguments("--remote-allow-origins=*");
            driver.set(new ChromeDriver(chromeOptions));
        } else if (browserName.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        } else {
            WebDriverManager.edgedriver().setup();
            driver.set(new EdgeDriver());
        }
        driver.set(setEventListener(driver.get()));
    }

    public static WebDriver setEventListener(WebDriver driver) {
        CustomWebDriverEventListener myWebdriverEventListener = new CustomWebDriverEventListener();
        return new EventFiringDecorator(myWebdriverEventListener).decorate(driver);
    }

    public static void openApplication() {
        setDriver(ConfigHelper.getValue("BROWSER"));
        getWebDriver().get(ConfigHelper.getValue("BASE_URL"));
    }

    public static WebDriver getWebDriver() {
        return driver.get();
    }

    public static void quitBrowser() {
        getWebDriver().quit();
    }
}
