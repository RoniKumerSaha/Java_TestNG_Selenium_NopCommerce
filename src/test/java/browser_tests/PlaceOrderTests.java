package browser_tests;

import automation.pages.*;
import automation.utils.DataHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceOrderTests extends BaseTest {

    @Test(testName = "Verify that user can select a product and place order successfully")
    public void testOrderFeature() {
        CartPage cartPage = new CartPage();
        CheckoutPage checkoutPage = new CheckoutPage();
        ProductDetailsPage productDetailsPage = new ProductDetailsPage();

        new HomePage().selectProductCategory("Electronics", "Cell phones");
        new ProductPage().selectProduct("Nokia Lumia 1020");

        productDetailsPage.setQuantity(2);
        productDetailsPage.addToCart();
        productDetailsPage.goToCart();

        cartPage.acceptTermsAndCheckOut();
        cartPage.checkOutAsGuest();

        checkoutPage.fillBillingAddressAndContinue(new DataHelper().getBillingData());
        checkoutPage.selectShippingOptionAndContinue("Next Day Air ($0.00)");
        checkoutPage.selectPaymentOptionAndContinue("Credit Card");
        checkoutPage.enterCardInfo(new DataHelper().getCardInfo());
        checkoutPage.confirmOder();

        Assert.assertEquals(checkoutPage.confirmationText.getText(), "Your order has been successfully processed!", "Confirmation text did not match");
    }
}
