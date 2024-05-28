package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_30_Wait_10_PageReady {
    WebDriver driver;

    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Admin_NopCommerce() {
        driver.get("https://admin-demo.nopcommerce.com");

        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");

        driver.findElement(By.cssSelector("input#Password")).clear();
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSeconds(2);

        Assert.assertTrue(isPageLoadedSuccess());
        //Assert.assertTrue(waitAjaxLoadingInvisible());

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(@class,'fa-user')]/following-sibling::p"))).click();
        driver.findElement(By.xpath("//ul[contains(@style,'display: block;')]//i[contains(@class,'fa-dot-circle')]/following-sibling::p[contains(string(),'Customers')]")).click();
        Assert.assertTrue(isPageLoadedSuccess());
        //Assert.assertTrue(waitAjaxLoadingInvisible());

        driver.findElement(By.xpath("//i[contains(@class,'fa-book')]/following-sibling::p")).click();
        driver.findElement(By.xpath("//ul[contains(@style,'display: block;')]//i[contains(@class,'fa-dot-circle')]/following-sibling::p[contains(string(),'Products')]")).click();
        Assert.assertTrue(isPageLoadedSuccess());
        //Assert.assertTrue(waitAjaxLoadingInvisible());
    }

    @Test
    public void TC_02_Admin_NopCommerce() {
        driver.get("https://admin-demo.nopcommerce.com");

        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Password")).clear();
        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSeconds(3);

        Assert.assertTrue(isPageLoadedSuccess());

        driver.findElement(By.xpath("//a[text()='Logout']")).click();

        Assert.assertTrue(isPageLoadedSuccess());

        Assert.assertEquals(driver.getTitle(), "Your store. Login");
    }

    @Test
    public void TC_03_OrangeHRM_API_Document() {
        driver.get("https://api.orangehrm.com/");

        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loader>div.spinner"))));

        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='OrangeHRM REST API Documentation']")).isDisplayed());
    }

    public boolean isPageLoadedSuccess() {
        /*return new WebDriverWait(driver, Duration.ofSeconds(30)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        });*/
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public boolean waitAjaxLoadingInvisible() {
        return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
