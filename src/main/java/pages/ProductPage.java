package pages;

import core.PageTool;
import org.openqa.selenium.By;
import core.util.BuilderXpath;

public class ProductPage extends PageTool {
    private static final String productInfoBlockPath = "//div[starts-with(@class,'ProductOrderInfostyle')]//div[@data-testid='ProductPrice']";

    private final By productTitle = By.xpath("//h1");
    private final By priceLabel = BuilderXpath.concat(productInfoBlockPath, "/*[starts-with(@class,'ProductPrice')]");
    private final By buyButton = BuilderXpath.concat(productInfoBlockPath, "/button[text()='Купити']");
    private final By cartButton = By.xpath("//button[@aria-label='dropdown-cart']");
    private final By cartItemsAmount = By.xpath("//div[contains(@class,'HeaderUserMenuBadge')]");
    private final By openCartButton = By.xpath("//a[@aria-label='Перейти до кошика']");

    public String getTitle() {
        return getText(productTitle);
    }

    public String getPrice() {
        return getText(priceLabel);
    }

    public ProductPage clickBuy() {
        click(buyButton);
        return this;
    }

    public ProductPage clickOnCart() {
        click(cartButton);
        return this;
    }

    public CartPage clickOnOpenCart() {
        click(openCartButton);
        return new CartPage();
    }

    public Boolean isPricePresented() {
        return isElementPresent(priceLabel);
    }

    public Boolean isTitlePresented() {
        return isElementPresent(productTitle);
    }

    public void waitForUpdatingCart(String expected) {
        waitForElementWithChangedText(cartItemsAmount, expected);
    }
}
