package util.driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EdgeDriver implements WebDriverAction{

    @Override
    public WebDriverManager getInstance() {
        return WebDriverManager.edgedriver();
    }
}
