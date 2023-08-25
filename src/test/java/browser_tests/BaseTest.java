package browser_tests;

import automation.pages.BasePage;
import automation.utils.DriverSetUP;
import automation.utils.ReportListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.IOException;

@Listeners(ReportListener.class)
public class BaseTest {

    @BeforeMethod
    public void setUp() {
        DriverSetUP.openApplication();
    }

    @AfterMethod()
    public void tearDown(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            getScreenShot(result);
        }
        DriverSetUP.quitBrowser();
    }


    public void getScreenShot(ITestResult result) throws IOException {
        TakesScreenshot camera = (TakesScreenshot) BasePage.getWebDriver();
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        com.google.common.io.Files.move(screenshot,
                new File("output/" + result.getName() + ".png"));
    }
}
