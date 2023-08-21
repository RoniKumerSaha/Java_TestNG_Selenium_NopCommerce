package browser_tests;

import automation.helpers.DataHelper;
import automation.pages.HomePage;
import automation.pages.RegistrationPage;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTests extends BaseTest{

    @Test(testName = "Checking home page title")
    public void testHomePageTitle(){
        Allure.step("Verifying page title");
        Assert.assertEquals(new HomePage().getPageTitle(), "nopCommerce demo store", "Title is different");
    }

    @Test(testName = "Verify new user registration using data:", dataProvider = "getRegistrationData")
    public void testUserRegistration(String gender, String firstname, String lastname, String dob, String company, String status, String password, String message){
        RegistrationPage registrationPage = new RegistrationPage();

        Allure.step("Navigating to registration page");
        new HomePage().goToRegistrationPage();

        Allure.step("Selecting Gender: " + gender);
        registrationPage.selectGender(gender);

        Allure.step("Enter data for Name: " + firstname + " " + lastname);
        registrationPage.enterFullName(firstname, lastname);

        Allure.step("Selecting Date of Birth: " + dob);
        registrationPage.selectDateOfBirth(dob);

        Allure.step("Entering Email");
        registrationPage.enterEmail(new DataHelper().getDynamicEmail());

        Allure.step("Entering Company name: " + company);
        registrationPage.enterCompanyDetails(company);

        Allure.step("Selecting Status: " + status);
        registrationPage.setNewsLetterStatus(status);

        Allure.step("Setting Password: " + password);
        registrationPage.setPasswordAndConfirm(password);

        Allure.step("Clicking Register");
        registrationPage.clickRegisterButton();

        Allure.step("Verifying that Registration is complete");
        Assert.assertEquals(registrationPage.confirmationMessage.getText(), message, "Confirmation message did not match");
    }
}
