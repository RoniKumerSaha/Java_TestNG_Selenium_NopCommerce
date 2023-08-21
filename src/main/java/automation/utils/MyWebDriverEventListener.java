package automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.util.List;

public class MyWebDriverEventListener implements WebDriverListener{
    public static Logger logger = LogManager.getLogger(MyWebDriverEventListener.class);
    @Override
    public void beforeGet(WebDriver driver, String url) {
        logger.info("Navigating to: " + url);
    }

    @Override
    public void afterGet(WebDriver driver, String url) {
        WebDriverListener.super.afterGet(driver, url);
    }

    @Override
    public void beforeGetTitle(WebDriver driver) {
        WebDriverListener.super.beforeGetTitle(driver);
    }

    @Override
    public void afterGetTitle(WebDriver driver, String result) {
        logger.info("Page title is: " + result);
    }

    @Override
    public void beforeFindElement(WebDriver driver, By locator) {
        logger.info("Finding element using: " + locator);
    }

    @Override
    public void afterFindElement(WebDriver driver, By locator, WebElement result) {
        WebDriverListener.super.afterFindElement(driver, locator, result);
    }

    @Override
    public void beforeFindElements(WebDriver driver, By locator) {
        WebDriverListener.super.beforeFindElements(driver, locator);
    }

    @Override
    public void afterFindElements(WebDriver driver, By locator, List<WebElement> result) {
        logger.info("Element found!");
    }

    @Override
    public void beforeClick(WebElement element) {
        logger.info("Clicking element: " + element);
    }

    @Override
    public void afterClick(WebElement element) {
        logger.info("Element clicked!");
    }
}
