package browser_tests;

import automation.helpers.DataHelper;
import automation.pages.*;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceOrderTests extends BaseTest{

    @Test(testName = "Verify that user can select a product and place order successfully")
    public void testOrderFeature(){
        CartPage cartPage = new CartPage();
        CheckoutPage checkoutPage = new CheckoutPage();
        ProductDetailsPage productDetailsPage = new ProductDetailsPage();

        Allure.step("Selecting the product category");
        new HomePage().selectProductCategory("Electronics", "Cell phones");

        Allure.step("Selecting a product");
        new ProductPage().selectProduct("Nokia Lumia 1020");

        Allure.step("Adding product quantity and add to cart");
        productDetailsPage.setQuantity(2);
        productDetailsPage.addToCart();
        productDetailsPage.goToCart();

        Allure.step("Accepting terms and checkout as guest");
        cartPage.acceptTermsAndCheckOut();
        cartPage.checkOutAsGuest();

        Allure.step("Filling up the billing address");
        checkoutPage.fillBillingAddressAndContinue(new DataHelper().getBillingData());

        Allure.step("Selecting shipping method");
        checkoutPage.selectShippingOptionAndContinue("Next Day Air ($0.00)");

        Allure.step("Selecting Payment option and entering card info");
        checkoutPage.selectPaymentOptionAndContinue("Credit Card");
        checkoutPage.enterCardInfo(new DataHelper().getCardInfo());

        Allure.step("Confirm and placing order");
        checkoutPage.confirmOder();

        Allure.step("Verifying that order is successful");
        Assert.assertEquals(checkoutPage.confirmationText.getText(), "Your order has been successfully processed!", "Confirmation text did not match");
    }
}
