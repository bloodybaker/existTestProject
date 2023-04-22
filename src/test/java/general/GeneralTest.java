package general;

import core.driver.SingleDriver;
import core.util.Constants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.ProductPage;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class GeneralTest extends Hook {

    @Test
    @DisplayName("Verify all required fields are presented")
    public void nameAndPriceVerification() {
        ProductPage productPage = new ProductPage();
        for (String link : Constants.URLS) {
            SingleDriver.open(link);

            assertTrue(productPage.isTitlePresented());
            assertNotNull(productPage.getTitle());
            assertTrue(productPage.isPricePresented());
            assertNotNull(productPage.getPrice());
        }
    }

    @Test
    @DisplayName("Compare sum in cart")
    public void sumPricesPerProduct() {
        ProductPage productPage = new ProductPage();
        for (String link : Constants.URLS) {
            SingleDriver.open(link);
            assertNotNull(productPage.getPrice());
            productPage.clickBuy();
            productPage.waitForUpdatingCart(String.valueOf(Arrays.asList(Constants.URLS).indexOf(link) + 1));
        }
        productPage.clickOnCart();
        CartPage cartPage = productPage.clickOnOpenCart();
        Integer sumOfAllProducts = cartPage.getSumOfAllProducts();
        Integer total = cartPage.getTotalPrice();
        assertEquals(sumOfAllProducts, total);
    }
}
