package pages;

import core.PageTool;
import core.util.Constants;
import core.util.StringUtil;

import java.util.List;
import java.util.Objects;

import org.openqa.selenium.By;

public class CartPage extends PageTool {

    private final By priceOfProduct = By.xpath("//tr/td[@data-field='Всього']/div/div");
    private final By totalPrice = By.xpath("//span[contains(text(),'грн')]//..");

    public Integer getTotalPrice() {
        String gatheredPrice = getText(totalPrice);
        return Integer.parseInt(Objects.requireNonNull(StringUtil.getSubStringByPattern(Constants.PRICE_REGEX, gatheredPrice)));
    }

    public Integer getSumOfAllProducts() {
        List<String> pricesStrings = getTextFromAllElements(priceOfProduct);
        return pricesStrings.stream().mapToInt(price ->
                Integer.parseInt(Objects.requireNonNull(StringUtil.getSubStringByPattern(Constants.PRICE_REGEX, price))))
                .sum();
    }
}
