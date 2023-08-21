package browser_tests;

import automation.pages.HomePage;
import automation.pages.RegistrationPage;
import automation.utils.DataHelper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseTest {

    @DataProvider
    public Object[][] getRegistrationData() {
        return new DataHelper().getUserRegistrationData("user-data.csv");
    }

    @Test(testName = "Checking home page title")
    public void homePageTitleTest() {
        Assert.assertEquals(new HomePage().getPageTitle(), "nopCommerce demo store", "Title is different");
    }

    @Test(testName = "Verify new user registration using data:", dataProvider = "getRegistrationData")
    public void userRegistrationTest(String gender, String firstname, String lastname, String dob, String company, String status, String password, String message) {
        RegistrationPage registrationPage = new RegistrationPage();
        new HomePage().goToRegistrationPage();

        registrationPage.selectGender(gender);
        registrationPage.enterFullName(firstname, lastname);
        registrationPage.selectDateOfBirth(dob);
        registrationPage.enterEmail(new DataHelper().getDynamicEmail());
        registrationPage.enterCompanyDetails(company);
        registrationPage.setNewsLetterStatus(status);
        registrationPage.setPasswordAndConfirm(password);
        registrationPage.clickRegisterButton();

        Assert.assertEquals(registrationPage.confirmationMessage.getText(), message, "Confirmation message did not match");
    }
}
