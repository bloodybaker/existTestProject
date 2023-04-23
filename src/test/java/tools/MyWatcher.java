package tools;

import core.driver.SingleDriver;
import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class MyWatcher implements TestWatcher {
    private static final Logger LOGGER = Logger.getLogger(MyWatcher.class.getSimpleName());

    @Override
    public void testSuccessful(ExtensionContext context) {
        LOGGER.info("Taking screenshot on SUCCESSFULLY completed case");
        captureScreen();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        LOGGER.info("Taking screenshot on FAILED case", cause);
        captureScreen();
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] captureScreen() {
        return ((TakesScreenshot) SingleDriver.webDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
