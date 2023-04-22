package core.driver.browsers;

import core.driver.WebDriverAction;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriver implements WebDriverAction {
    @Override
    public WebDriverManager getInstance() {
        return WebDriverManager.chromedriver();
    }
}
