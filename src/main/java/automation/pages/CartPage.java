package automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{
    @FindBy(id = "termsofservice")
    WebElement termsAndConditionCheckbox;

    @FindBy(id = "checkout")
    WebElement checkOutButton;

    @FindBy(xpath = "//button[text()='Checkout as Guest']")
    WebElement checkOutAsGuestButton;

    public void acceptTermsAndCheckOut(){
        if(!termsAndConditionCheckbox.isSelected())
            termsAndConditionCheckbox.click();
        checkOutButton.click();
    }

    public void checkOutAsGuest(){
        checkOutAsGuestButton.click();
    }
}
