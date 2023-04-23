package tools;

import java.util.*;

import core.driver.SingleDriver;
import core.driver.WebDriverAction;
import core.driver.browsers.ChromeDriver;
import core.driver.browsers.EdgeDriver;
import core.driver.browsers.OperaDriver;
import core.driver.browsers.SafariDriver;
import core.exceptions.PropertyException;

import core.util.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

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
        SingleDriver.setWebDriver(webDriverManager.create());
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
            return drivers.get("chrome").getInstance();
        }
    }
}
