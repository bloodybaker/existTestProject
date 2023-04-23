package general;

import core.driver.SingleDriver;
import core.util.Constants;
import io.qameta.allure.Flaky;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.ProductPage;
import tools.Hook;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest extends Hook {

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

    @Test
    @Flaky
    @DisplayName("Failed flaky test")
    public void flakyTest(){
        SingleDriver.open(Constants.URLS[1]);
        ProductPage productPage = new ProductPage();
        assertTrue(productPage.isPricePresented());
        productPage.clickOnCart();
        productPage.clickOnOpenCart();
    }
}
