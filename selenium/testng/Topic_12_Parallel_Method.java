package testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Random;

public class Topic_12_Parallel_Method {
    private WebDriver driver;

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        driver = new FirefoxDriver();
        driver.get("http://live.techpanda.org/");
        Thread.sleep(5000);
    }

    @Test
    public void TC_01_() {
        System.out.println("TC01");
    }

    @Test
    public void TC_02_() {
        System.out.println("TC02");
    }

    @Test
    public void TC_03_() {
        System.out.println("TC03");
    }

    @Test
    public void TC_04_() {
        System.out.println("TC04");
    }

    @Test
    public void TC_05_() {
        System.out.println("TC01");
    }

    @Test
    public void TC_06_() {
        System.out.println("TC06");
    }
    
    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        if (driver != null) {
            driver.quit();
        }
    }

}
