package tools;

import java.text.SimpleDateFormat;
import java.util.*;

import core.driver.SingleDriver;
import core.driver.WebDriverAction;
import core.driver.browsers.ChromeDriver;
import core.driver.browsers.EdgeDriver;
import core.driver.browsers.OperaDriver;
import core.driver.browsers.SafariDriver;

import core.dto.EnvironmentDto;
import core.dto.PropertyDto;
import core.exceptions.PropertyException;
import core.util.Constants;

import core.util.FileUtil;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Hook {
    private static final Logger LOGGER = Logger.getLogger(Hook.class.getSimpleName());
    private static final Map<String, WebDriverAction> drivers = new HashMap<>() {{
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
        configureAllureEnv(SingleDriver.webDriver());
        SingleDriver.webDriver().quit();
    }

    private static WebDriverManager verifyAndGetBrowser() {
        String browserName = System.getProperty("browser");
        if (browserName != null && drivers.containsKey(browserName.toLowerCase(Locale.ROOT))) {
            return drivers.get(browserName).getInstance();
        } else {
            LOGGER.warn("As a default was selected Chrome", new PropertyException("Browser was not defined. Set up this property in environment variables."));
            return drivers.get("edge").getInstance();
        }
    }

    private static void configureAllureEnv(WebDriver webDriver){
        SingleDriver.setWebDriver(webDriver);
        Capabilities caps = ((RemoteWebDriver) webDriver).getCapabilities();
        List<PropertyDto> properties = new ArrayList<>(){{
            add(new PropertyDto("Browser", caps.getBrowserName()));
            add(new PropertyDto("Browser.Version", caps.getBrowserVersion()));
            add(new PropertyDto("LatestRun.Date",  new SimpleDateFormat(Constants.DATE_FORMAT).format(new Date())));
        }};
        FileUtil.createXmlEnvFile(new EnvironmentDto(properties));
    }
}
