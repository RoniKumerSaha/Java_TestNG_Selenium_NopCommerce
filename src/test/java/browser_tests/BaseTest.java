package browser_tests;

import automation.helpers.DataHelper;
import automation.pages.BasePage;
import automation.utils.DriverSetUP;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;


public class BaseTest {

    @BeforeMethod
    public void startBrowser(){
        DriverSetUP.gotoHomePage();
    }

    @AfterMethod()
    public void stopBrowser(ITestResult result) throws IOException {
        if(ITestResult.FAILURE == result.getStatus()){
            getScreenShot(result);
        }
        DriverSetUP.quitBrowser();
    }


    @DataProvider
    public Object[][] getRegistrationData(){
        return new DataHelper().getUserRegistrationData("data.csv");
    }

    public void getScreenShot(ITestResult result) throws IOException {
        TakesScreenshot camera = (TakesScreenshot) BasePage.getWebDriver();
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        com.google.common.io.Files.move(screenshot,
                new File("output/"+ result.getName() +".png"));
    }
}
