package tools;

import java.text.SimpleDateFormat;
import java.util.*;

import com.google.common.collect.ImmutableMap;
import core.driver.SingleDriver;
import core.driver.WebDriverAction;
import core.driver.browsers.ChromeDriver;
import core.driver.browsers.EdgeDriver;
import core.driver.browsers.OperaDriver;
import core.driver.browsers.SafariDriver;

import core.exceptions.PropertyException;
import core.util.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class Hook {
    private static final Logger LOGGER = Logger.getLogger(Hook.class.getSimpleName());
    private static final Map<String, WebDriverAction> drivers = new HashMap<String, WebDriverAction>() {{
        put("chrome", new ChromeDriver());
        put("edge", new EdgeDriver());
        put("opera", new OperaDriver());
        put("safari", new SafariDriver());
    }};

    @BeforeAll
    public static void setupDriver() {
        PropertyConfigurator.configure(Constants.LOG4J_PROPERTY_PATH);
        LOGGER.info("Starting tuning browser...");
        WebDriverManager webDriverManager = verifyAndGetBrowser();
        webDriverManager.setup();
        configureAllureEnv(webDriverManager.create());
        LOGGER.info("Browser was initialized successfully");
    }

    @AfterAll
    public static void tearDown() {
        LOGGER.info("Ending tests");
        SingleDriver.webDriver().quit();
    }

    private static WebDriverManager verifyAndGetBrowser() {
        String browserName = System.getenv("browser");
        if (browserName != null && drivers.containsKey(browserName)) {
            return drivers.get(browserName).getInstance();
        } else {
            LOGGER.warn("As a default was selected Chrome", new PropertyException("Browser was not defined. Set up this property in environment variables."));
            return drivers.get("edge").getInstance();
        }
    }

    private static void configureAllureEnv(WebDriver webDriver){
        SingleDriver.setWebDriver(webDriver);
        Capabilities caps = ((RemoteWebDriver) webDriver).getCapabilities();
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", caps.getBrowserName())
                        .put("Browser.Version", caps.getBrowserVersion())
                        .put("LatestRun.Date",  new SimpleDateFormat(Constants.dateFormat).format(new Date()))
                        .build(), System.getProperty("user.dir").concat("target/allure-results/"));
    }
}
