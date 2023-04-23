package core.util;

import core.driver.SingleDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class FileUtil {

    @Attachment
    public static byte[] getScreenshot(){
      return   ((TakesScreenshot) SingleDriver.webDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
