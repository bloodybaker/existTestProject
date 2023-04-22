package general;

import core.driver.*;
import core.driver.browsers.ChromeDriver;
import core.driver.browsers.EdgeDriver;
import core.driver.browsers.OperaDriver;
import core.driver.browsers.SafariDriver;
import core.exceptions.PropertyException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class Hook {
    private static final Map<String, WebDriverAction> drivers = new HashMap<String, WebDriverAction>() {{
        put("chrome", new ChromeDriver());
        put("edge", new EdgeDriver());
        put("opera", new OperaDriver());
        put("safari", new SafariDriver());
    }};

    @BeforeAll
    public static void setupDriver() throws PropertyException {
        verifyAndGetBrowser().setup();
        SingleDriver.setWebDriver(verifyAndGetBrowser().create());
    }

    @AfterAll
    static void tearDown(){
        SingleDriver.webDriver().quit();
    }

    private static WebDriverManager verifyAndGetBrowser() throws PropertyException {
        String browserName = System.getenv("browser");
        if (browserName != null && drivers.containsKey(browserName)) {
            return drivers.get(browserName).getInstance();
        } else {
            throw new PropertyException("Browser was not defined. Set up this property in environment variables.");
        }
    }
}
