package core.driver.browsers;

import core.driver.WebDriverAction;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EdgeDriver implements WebDriverAction {

    @Override
    public WebDriverManager getInstance() {
        return WebDriverManager.edgedriver();
    }
}
