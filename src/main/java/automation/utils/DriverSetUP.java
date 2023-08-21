package automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class DriverSetUP {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static void setDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless");
        options.addArguments("--silent");
        options.addArguments("--remote-allow-origins=*");
        driver.set(new ChromeDriver(options));
        driver.set(setEventListener(driver.get()));
    }

    public static WebDriver setEventListener(WebDriver driver){
        MyWebDriverEventListener myWebdriverEventListener = new MyWebDriverEventListener();
        return new EventFiringDecorator(myWebdriverEventListener).decorate(driver);
    }

    public static void gotoHomePage(){
        setDriver();
        getWebDriver().get("https://demo.nopcommerce.com");
    }

    public static WebDriver getWebDriver(){
        return driver.get();
    }

    public static void quitBrowser(){
        getWebDriver().quit();
    }
}
