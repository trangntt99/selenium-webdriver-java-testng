package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_12_Driver_Info {
    WebDriver driver;

    @Test
    public void testDriverInformation() {
        driver = new FirefoxDriver();
        // Ở trên OS nào
        // Browser nào
        // Browser class
        // ID của driver là gì
        // FirefoxDriver: firefox on windows (b7ad8921-b2e4-4a50-a483-d848a2957fe9)
        System.out.println(driver.toString());
        if (driver.toString().contains("firefox")) {
            // Scroll
        }

        driver.quit();
    }
}
