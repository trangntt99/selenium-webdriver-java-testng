package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_29_Wait_09_Fluent {
    WebDriver driver;
    FluentWait<WebDriver> fluentDriver;
    FluentWait<WebElement> fluentElement;
    long fullTimeoutInSecond = 30;
    long pollingTimeoutInMillisecond = 300;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        fluentDriver = new FluentWait<>(driver);
    }

    @Test
    public void TC_01_() {
        driver.get("https://automationfc.github.io/fluent-wait/");

        WebElement countDownTime = driver.findElement(By.cssSelector("div#javascript_countdown_time"));
        fluentElement = new FluentWait<WebElement>(countDownTime);

        // Setting
        fluentElement.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class, TimeoutException.class);

        fluentElement.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                String text = webElement.getText();
                System.out.println(text);
                return text.endsWith("00");
            }
        });
    }

    @Test
    public void TC_02_() {
        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        // Chờ cho HelloWorld text hiển thị trong vòng 10s
        // Condition
        String helloText = waitAndFindElement(By.cssSelector("div#finish>h4")).getText();

        Assert.assertEquals(helloText, "Hello World!");
    }

    public WebElement waitAndFindElement(By locator) {
        FluentWait<WebDriver> fluentDriver = new FluentWait<>(driver);

        fluentDriver.withTimeout(Duration.ofSeconds(fullTimeoutInSecond))
                .pollingEvery(Duration.ofMillis(pollingTimeoutInMillisecond))
                .ignoring(NoSuchElementException.class, TimeoutException.class);

        return fluentDriver.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(locator);
            }
        });
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
