package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import util.BuilderXpath;

public class ProductPage {
    private final WebDriver driver;

    private final String productInfoBlockPath = "//div[starts-with(@class,'ProductOrderInfostyle')]//div[@data-testid='ProductPrice']";

    private final By productTitle = By.xpath("//h1");
    private final By priceLabel = BuilderXpath.concat(productInfoBlockPath,"/*[starts-with(@class,'ProductPrice')]");
    private final By buyButton = BuilderXpath.concat(productInfoBlockPath,"/button");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle(){
        return driver.findElement(productTitle).getText();
    }

    public String getPrice(){
        return driver.findElement(priceLabel).getText();
    }

    public CartPage clickBuy(){
        driver.findElement(buyButton).click();
        return new CartPage(driver);
    }
}
