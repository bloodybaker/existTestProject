package core.driver;

import org.openqa.selenium.WebDriver;

import java.util.Objects;

public final class SingleDriver {
    private static WebDriver webDriver;

    public static void setWebDriver(WebDriver webDriver) {
        SingleDriver.webDriver = webDriver;
    }

    public static void open(String url){
        Objects.requireNonNull(webDriver()).get(url);
    }

    public static WebDriver webDriver(){
        return webDriver;
    }
}
