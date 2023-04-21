package util.driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriver implements WebDriverAction{
    @Override
    public WebDriverManager getInstance() {
        return WebDriverManager.chromedriver();
    }
}
