package util.driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxDriver implements WebDriverAction {

    @Override
    public WebDriverManager getInstance() {
        return WebDriverManager.firefoxdriver();
    }
}
