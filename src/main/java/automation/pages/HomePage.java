package automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(linkText = "Register")
    WebElement registerButton;

    public void goToRegistrationPage() {
        registerButton.click();
    }

    public void selectProductCategory(String categoryName, String subCategoryName) {
        WebElement category = getWebDriver().findElement(By.xpath("//a[text()='" + categoryName + " ']"));
        WebElement subCategory = getWebDriver().findElement(By.xpath("//a[text()='" + subCategoryName + " ']"));
        hoverOn(getWebDriver(), category);
        subCategory.click();
    }


}
