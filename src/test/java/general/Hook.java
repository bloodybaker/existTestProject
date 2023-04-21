package general;

import exceptions.PropertyException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import util.driver.*;

import java.util.HashMap;
import java.util.Map;

public class Hook {
    protected WebDriver webDriver;
    private static final Map<String, WebDriverAction> drivers = new HashMap<String, WebDriverAction>() {{
        put("chrome", new ChromeDriver());
        put("edge", new EdgeDriver());
        put("opera", new OperaDriver());
        put("safari", new SafariDriver());
    }};

    @BeforeAll
    public static void setupDriver() throws PropertyException {
        verifyAndGetBrowser().setup();
    }

    @BeforeEach
    public void setupTest() throws PropertyException {
        webDriver = verifyAndGetBrowser().create();
    }

    @AfterEach
    public void tearDown(){
        webDriver.quit();
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
