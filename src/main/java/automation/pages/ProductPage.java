package automation.pages;

import org.openqa.selenium.By;

public class ProductPage extends BasePage {
    public void selectProduct(String productName) {
        getWebDriver().findElement(By.xpath("//a[text()='" + productName + "']")).click();
    }
}
