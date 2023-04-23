package general;

import core.driver.SingleDriver;
import core.util.Constants;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProductPage;
import tools.Hook;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsTest extends Hook {

    @Test
    @DisplayName("Verify all required fields are presented")
    @Description("Test indicates all required fields are presented")
    public void nameAndPriceArePresented() {
        ProductPage productPage = new ProductPage();
        for (String link : Constants.URLS) {
            SingleDriver.open(link);
            assertTrue(productPage.isTitlePresented());
            assertTrue(productPage.isPricePresented());
        }
    }

    @Test
    @DisplayName("Verify all required fields contains text")
    @Description("Test indicates all required fields contains text")
    public void nameAndPriceVerification() {
        ProductPage productPage = new ProductPage();
        for (String link : Constants.URLS) {
            SingleDriver.open(link);
            assertNotNull(productPage.getTitle());
            assertNotNull(productPage.getPrice());
        }
    }
}
