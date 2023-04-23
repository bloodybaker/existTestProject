package core;

import core.driver.SingleDriver;
import org.apache.log4j.Logger;
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
    private static final Logger LOGGER = Logger.getLogger(PageTool.class.getSimpleName());
    private final FluentWait<WebDriver> fluentWait;
    private final WebDriverWait wait;
    private final JavascriptExecutor jsExecutor = (JavascriptExecutor) SingleDriver.webDriver();

    public PageTool() {
        wait = new WebDriverWait(SingleDriver.webDriver(), Duration.ofSeconds(15));
        fluentWait = new FluentWait<>(SingleDriver.webDriver());
        fluentWait.pollingEvery(Duration.ofSeconds(3));
        fluentWait.withTimeout(Duration.ofSeconds(3));
    }

    protected void click(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        LOGGER.info(String.format("Clicked on element: %s", element.toString()));
    }

    protected String getText(By element) {
        LOGGER.info(String.format("Got text of element: %s", element.toString()));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element)).getText();
    }

    protected List<String> getTextFromAllElements(By element) {
        waitForElement(element);
        List<WebElement> elements = SingleDriver.webDriver().findElements(element);
        LOGGER.info(String.format("Got text from all elements: %s", element.toString()));
        return elements.stream().map(WebElement::getText)
                .collect(Collectors.toList());
    }

    protected void scrollToElement(By element) {
        LOGGER.info(String.format("Scrolled to element: %s", element.toString()));
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", SingleDriver.webDriver().findElement(element));
    }

    protected void clickJs(By element) {
        jsExecutor.executeScript("arguments[0].click();", SingleDriver.webDriver().findElement(element));
        LOGGER.info(String.format("Clicked on element with JS: %s", element.toString()));
    }

    protected boolean isElementPresent(By element) {
        return SingleDriver.webDriver().findElement(element).isDisplayed();
    }

    protected void waitForElementWithChangedText(By element, String currentExpectedValue) {
        Function<WebDriver, Boolean> function = func -> {
            WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            LOGGER.info(String.format("Waiting for element: %s", element.toString()));
            return webElement.isDisplayed() && getText(element).equals(currentExpectedValue);
        };
        fluentWait.until(function);
    }

    protected void waitForElement(By element) {
        Function<WebDriver, Boolean> function = func ->
                wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
        fluentWait.until(function);
        LOGGER.info(String.format("Waiting for element: %s", element.toString()));
    }
}
