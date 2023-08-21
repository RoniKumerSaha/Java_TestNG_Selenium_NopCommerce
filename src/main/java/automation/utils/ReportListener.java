package automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class ReportListener implements ITestListener {

    String reportPath = System.getProperty("user.dir") + "/reports/extends-report.html";
    ThreadLocal<String> testName = new ThreadLocal<>();
    ExtentSparkReporter htmlReporter;
    ExtentReports extent;

    @Override
    public void onStart(ITestContext context) {
        htmlReporter = new ExtentSparkReporter(new File(reportPath));
        htmlReporter.config().setDocumentTitle("UI Tests");
        htmlReporter.config().setReportName("UI Tests");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.setSystemInfo("OS Name", System.getProperty("os.name"));
        extent.setSystemInfo("Automation Name", "AUTOMATION");
        extent.setSystemInfo("Automation Type", System.getProperty("AUTOMATION_TYPE"));
        extent.attachReporter(htmlReporter);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        testName.set(String.format("%s_%s", result.getTestClass().getRealClass().getSimpleName(), result.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (DriverSetUP.getWebDriver() != null)
            extent.createTest(testName.get()).log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromBase64String(((TakesScreenshot) DriverSetUP.getWebDriver()).getScreenshotAs(OutputType.BASE64)).build());
        else
            extent.createTest(testName.get()).log(Status.PASS, result.getThrowable().getLocalizedMessage());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (DriverSetUP.getWebDriver() != null)
            extent.createTest(testName.get()).log(Status.FAIL, result.getThrowable().getLocalizedMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(((TakesScreenshot) DriverSetUP.getWebDriver()).getScreenshotAs(OutputType.BASE64)).build());
        else
            extent.createTest(testName.get()).log(Status.FAIL, result.getThrowable().getLocalizedMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (DriverSetUP.getWebDriver() != null)
            extent.createTest(testName.get()).log(Status.SKIP, result.getThrowable().getLocalizedMessage(), MediaEntityBuilder.createScreenCaptureFromBase64String(((TakesScreenshot) DriverSetUP.getWebDriver()).getScreenshotAs(OutputType.BASE64)).build());
        else
            extent.createTest(testName.get()).log(Status.SKIP, result.getThrowable().getLocalizedMessage());
    }
}
