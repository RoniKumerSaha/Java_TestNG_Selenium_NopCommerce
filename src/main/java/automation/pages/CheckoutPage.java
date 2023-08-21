package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;

public class CheckoutPage extends BasePage{
    @FindBy(id = "BillingNewAddress_FirstName")
    WebElement firstNameInput;

    @FindBy(id = "BillingNewAddress_LastName")
    WebElement lastNameInput;

    @FindBy(id = "BillingNewAddress_Email")
    WebElement emailInput;

    @FindBy(id = "BillingNewAddress_Company")
    WebElement companyInput;

    @FindBy(id = "BillingNewAddress_CountryId")
    WebElement countryDropdown;

    @FindBy(id = "BillingNewAddress_StateProvinceId")
    WebElement stateDropdown;

    @FindBy(id = "BillingNewAddress_City")
    WebElement cityInput;

    @FindBy(id = "BillingNewAddress_Address1")
    WebElement addressInput;

    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    WebElement zipCodeInput;

    @FindBy(id = "BillingNewAddress_PhoneNumber")
    WebElement phoneInput;

    @FindBy(xpath = "(//button[text()='Continue' ])[1]")
    WebElement continueToShipping;

    @FindBy(xpath = "(//button[text()='Continue' ])[3]")
    WebElement continueToPayment;

    @FindBy(xpath = "(//button[text()='Continue' ])[4]")
    WebElement continueCardDetails;

    @FindBy(xpath = "(//button[text()='Continue' ])[5]")
    WebElement continueToConfirmOrder;

    @FindBy(id = "CreditCardType")
    WebElement cardTypeDropdown;

    @FindBy(id = "CardholderName")
    WebElement cardHolderInput;

    @FindBy(id = "CardNumber")
    WebElement cardNumberInput;

    @FindBy(id = "ExpireMonth")
    WebElement expireMonthDropdown;

    @FindBy(id = "ExpireYear")
    WebElement expireYearDropdown;

    @FindBy(id = "CardCode")
    WebElement cardCodeInput;

    @FindBy(xpath = "(//button[text()='Confirm'])")
    WebElement confirmButton;

    @FindBy(xpath = "//div[@Class='section order-completed']/div")
    public WebElement confirmationText;

    public void fillBillingAddressAndContinue(HashMap<String, String> billingData){
        firstNameInput.sendKeys(billingData.get("firstname"));
        lastNameInput.sendKeys(billingData.get("lastname"));
        emailInput.sendKeys(billingData.get("email"));
        companyInput.sendKeys(billingData.get("company"));
        selectDropdown(countryDropdown).selectByVisibleText(billingData.get("country"));
        cityInput.sendKeys(billingData.get("city"));
        addressInput.sendKeys(billingData.get("address"));
        zipCodeInput.sendKeys(billingData.get("zip"));
        phoneInput.sendKeys(billingData.get("phone"));
        continueToShipping.click();
        waitForElementToVanish(continueToShipping);
    }

    public void selectShippingOptionAndContinue(String shippingOption){
        getWebDriver().findElement(By.xpath("//label[text()='" + shippingOption +"']")).click();
        continueToPayment.click();
        waitForElementToVanish(continueToPayment);
    }

    public void selectPaymentOptionAndContinue(String paymentOption){
        getWebDriver().findElement(By.xpath("//label[text()='" + paymentOption +"']")).click();
        continueCardDetails.click();
        waitForElementToVanish(continueCardDetails);
    }

    public void enterCardInfo(HashMap<String, String> cardData){
        selectDropdown(cardTypeDropdown).selectByVisibleText(cardData.get("cardType"));
        cardHolderInput.sendKeys(cardData.get("cardholder"));
        selectDropdown(expireMonthDropdown).selectByValue(cardData.get("expireMonth"));
        selectDropdown(expireYearDropdown).selectByVisibleText(cardData.get("expireYear"));
        cardNumberInput.sendKeys(cardData.get("cardNumber"));
        cardCodeInput.sendKeys(cardData.get("cardCode"));
        continueToConfirmOrder.click();
        waitForElementToVanish(continueToConfirmOrder);
    }

    public void confirmOder(){
        confirmButton.click();
        waitForElementToVanish(confirmButton);
    }
}
