package automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends BasePage{

    @FindBy(className = "female")
    WebElement genderOptionFemale;

    @FindBy(className = "male")
    WebElement genderOptionMale;

    @FindBy(id = "FirstName")
    WebElement firstNameInput;

    @FindBy(id = "LastName")
    WebElement lastNameInput;

    @FindBy(xpath = "//select[@name='DateOfBirthDay']")
    WebElement dayOption;

    @FindBy(xpath = "//select[@name='DateOfBirthMonth']")
    WebElement monthOption;

    @FindBy(xpath = "//select[@name='DateOfBirthYear']")
    WebElement yearOption;

    @FindBy(id = "Email")
    WebElement emailInput;

    @FindBy(id = "Company")
    WebElement companyDetailsInput;

    @FindBy(id = "Newsletter")
    WebElement newsletterCheckbox;

    @FindBy(id = "Password")
    WebElement passwordInput;

    @FindBy(id = "ConfirmPassword")
    WebElement confirmPasswordInput;

    @FindBy(id = "register-button")
    WebElement submitButton;

    @FindBy(xpath = "//div[@class='result']")
    public WebElement confirmationMessage;

    public void selectGender(String gender){
        if(gender.equalsIgnoreCase("Male")){
            genderOptionMale.click();
        }else {
            genderOptionFemale.click();
        }
    }

    public void enterFullName(String firstname, String lastname){
        firstNameInput.sendKeys(firstname);
        lastNameInput.sendKeys(lastname);
    }

    public void selectDateOfBirth(String date){
        selectDropdown(dayOption).selectByValue(String.valueOf(Integer.parseInt(date.split("/")[0])));
        selectDropdown(monthOption).selectByValue(String.valueOf(Integer.parseInt(date.split("/")[1])));
        selectDropdown(yearOption).selectByValue(String.valueOf(Integer.parseInt(date.split("/")[2])));
    }

    public void enterEmail(String email){
        emailInput.sendKeys(email);
    }

    public void enterCompanyDetails(String companyDetails){
        companyDetailsInput.sendKeys(companyDetails);
    }

    public void setNewsLetterStatus(String status){
        if(status.equalsIgnoreCase("Checked") && (!newsletterCheckbox.isSelected())){
            newsletterCheckbox.click();
        } else if (status.equalsIgnoreCase("Unchecked") && newsletterCheckbox.isSelected()) {
            newsletterCheckbox.click();
        }
    }

    public void setPasswordAndConfirm(String password){
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(password);
    }

    public void clickRegisterButton(){
        submitButton.click();
    }


}
