package general;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GeneralTest extends Hook {

    @Test
    @DisplayName("Verify all required fields")
    public void nameAndPriceVerification(){
        webDriver.get("https://google.com");
    }
}
