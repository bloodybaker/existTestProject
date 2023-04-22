package core.util;

import org.openqa.selenium.By;

public class BuilderXpath {

    public static By concat(String prefix, String appendix){
        return By.xpath(prefix.concat(appendix));
    }

    public static By format(String body, String param){
        return By.xpath(String.format(body, param));
    }
}
