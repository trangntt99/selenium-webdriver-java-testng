package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_16_Shadow_DOM {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_shadow_DOM() {
        // driver = đại diện cho Real DOM (DOM bên ngoài)
        driver.get("https://automationfc.github.io/shadow-dom");

        // shadowRootContext = đại diện cho shadow DOM 1 bên trong
        WebElement shadowHostElement = driver.findElement(By.cssSelector("div#shadow_host"));
        SearchContext shadowRootContext = shadowHostElement.getShadowRoot();

        Assert.assertEquals(shadowRootContext.findElement(By.cssSelector("span#shadow_content>span")).getText(), "some text");
        Assert.assertFalse(shadowRootContext.findElement(By.cssSelector("input[type='checkbox']")).isSelected());

        List<WebElement> allInput = shadowRootContext.findElements(By.cssSelector("input"));
        System.out.println(allInput.size());

        // nestedShadowHostElement = đại diện cho cái nested shadow DOM 2 (nằm trong shadow DOM 1)
        WebElement nestedShadowHostElement = shadowRootContext.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext nestedShadowRootContext = nestedShadowHostElement.getShadowRoot();
        Assert.assertEquals(nestedShadowRootContext.findElement(By.cssSelector("div#nested_shadow_content>div")).getText(), "nested text");
    }

    @Test
    public void TC_02_shadow_DOM_Shopee() {
        driver.get("https://shopee.vn");

        WebElement shopeeBanner = driver.findElement(By.cssSelector("shopee-banner-popup-stateful"));
        SearchContext shadowRootContext = shopeeBanner.getShadowRoot();

        // Verify popup hiển thị
        if (shadowRootContext.findElements(By.cssSelector("div.home-popup__content")).size() > 0
                && shadowRootContext.findElements(By.cssSelector("div.home-popup__content")).get(0).isDisplayed()) {

            // Click để close popup đi
            shadowRootContext.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
            sleepInSeconds(3);
        }

        // Ko hiển thị/ đã bị đóng rồi thì qua step Search dữ liệu
        driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("iphone 15 Pro Max");
        driver.findElement(By.cssSelector("button.shopee-searchbar__search-button")).click();
        sleepInSeconds(3);
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
