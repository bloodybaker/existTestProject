package tools;

import core.util.FileUtil;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class MyWatcher implements TestWatcher {
    private static final Logger LOGGER = Logger.getLogger(MyWatcher.class.getSimpleName());

    @Override
    public void testSuccessful(ExtensionContext context) {
        LOGGER.info("Taking screenshot on SUCCESSFULLY completed case");
        FileUtil.getScreenshot();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        LOGGER.info("Taking screenshot on FAILED case", cause);
        FileUtil.getScreenshot();
    }
}
