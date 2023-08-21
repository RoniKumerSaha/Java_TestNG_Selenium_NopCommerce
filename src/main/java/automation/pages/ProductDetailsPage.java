package automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends BasePage {

    @FindBy(xpath = "//input[@aria-label='Enter a quantity']")
    WebElement quantityInput;

    @FindBy(xpath = "//button[contains(@id,'add-to-cart-button')]")
    WebElement addToCartButton;

    @FindBy(linkText = "shopping cart")
    WebElement cartLink;

    public void setQuantity(int quantity) {
        quantityInput.clear();
        quantityInput.sendKeys(String.valueOf(quantity));
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public void goToCart() {
        waitFor(cartLink);
        cartLink.click();
    }
}
