package core;

import core.driver.SingleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageTool {
    private final FluentWait<WebDriver> fluentWait;
    private final WebDriverWait wait;
    private final JavascriptExecutor jsExecutor = (JavascriptExecutor) SingleDriver.webDriver();

    public PageTool() {
        wait = new WebDriverWait(SingleDriver.webDriver(), Duration.ofSeconds(10));
        fluentWait = new FluentWait<>(SingleDriver.webDriver());
        fluentWait.pollingEvery(Duration.ofSeconds(1));
        fluentWait.withTimeout(Duration.ofSeconds(1));
    }

    protected void click(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected String getText(By element) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
    }

    protected List<String> getTextFromAllElements(By element) {
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElements(SingleDriver.webDriver().findElements(element)));
        return elements.stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }

    protected void scrollToElement(By element) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", SingleDriver.webDriver().findElement(element));

    }

    protected void clickJs(By element) {
        jsExecutor.executeScript("arguments[0].click();", SingleDriver.webDriver().findElement(element));
    }

    protected boolean isElementPresent(By element) {
        return SingleDriver.webDriver().findElement(element).isDisplayed();
    }

    protected void waitForElementWithChangedText(By element, String currentExpectedValue) {
        Function<WebDriver, Boolean> function = func -> {
            WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            if (webElement.isDisplayed() && getText(element).equals(currentExpectedValue)) {
                return true;
            }
            return false;
        };
        fluentWait.until(function);
    }

    protected void waitForElement(By element) {
        Function<WebDriver, Boolean> function = func ->
                wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
        fluentWait.until(function);
    }
}
