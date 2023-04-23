package core.driver;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public final class SingleDriver {
    private static WebDriver webDriver;
    private final static Logger LOGGER = Logger.getLogger(SingleDriver.class.getSimpleName());

    public static void setWebDriver(WebDriver webDriver) {
        SingleDriver.webDriver = webDriver;
        webDriver.manage().window().maximize();
    }

    public static void open(String url){
        LOGGER.info(String.format("Opening: %s", url));
        Objects.requireNonNull(webDriver()).get(url);
    }

    public static WebDriver webDriver(){
        return webDriver;
    }
}
