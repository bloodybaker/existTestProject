package general;

import core.driver.SingleDriver;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.ProductPage;
import tools.Hook;
import tools.MyWatcher;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyWatcher.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductsTest extends Hook {

    @ParameterizedTest
    @MethodSource("tools.DataProvider#getListOfUrls")
    @DisplayName("Verify all required fields are presented")
    @Description("Test indicates all required fields are presented")
    public void nameAndPriceArePresented(String link) {
        SingleDriver.open(link);
        ProductPage productPage = new ProductPage();
        assertTrue(productPage.isTitlePresented());
        assertTrue(productPage.isPricePresented());
    }

    @ParameterizedTest
    @MethodSource("tools.DataProvider#getListOfUrls")
    @DisplayName("Verify all required fields contains text")
    @Description("Test indicates all required fields contains text")
    public void nameAndPriceVerification(String link) {
        SingleDriver.open(link);
        ProductPage productPage = new ProductPage();
        assertNotNull(productPage.getTitle());
        assertNotNull(productPage.getPrice());
    }
}
