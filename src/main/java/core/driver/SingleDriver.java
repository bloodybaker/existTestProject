package core.driver;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public final class SingleDriver {
    private static WebDriver webDriver;
    private final static Logger LOGGER = Logger.getLogger("Driver");

    public static void setWebDriver(WebDriver webDriver) {
        SingleDriver.webDriver = webDriver;
    }

    public static void open(String url){
        LOGGER.info(String.format("Opening: %s", url));
        Objects.requireNonNull(webDriver()).get(url);
    }

    public static WebDriver webDriver(){
        return webDriver;
    }
}
