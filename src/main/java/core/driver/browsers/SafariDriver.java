package core.driver.browsers;

import core.driver.WebDriverAction;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SafariDriver implements WebDriverAction {
    @Override
    public WebDriverManager getInstance() {
        return WebDriverManager.safaridriver();
    }
}
