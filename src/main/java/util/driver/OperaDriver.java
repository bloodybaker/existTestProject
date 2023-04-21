package util.driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OperaDriver implements WebDriverAction {

    @Override
    public WebDriverManager getInstance() {
        return WebDriverManager.operadriver();
    }
}
