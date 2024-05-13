package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_Frame_Iframe {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Iframe_Form_Site() {
        // Trang A - domain: formsite.com
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        driver.findElement(By.cssSelector("div#imageTemplateContainer")).click();
        sleepInSeconds(5);

        // Chứa iframe - trang B
        // Từ A vào B
        WebElement formIframe = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));
        Assert.assertTrue(formIframe.isDisplayed());

        // Switch vào frame/ iframe trước khi thao tác vs các element bên trong
        //driver.switchTo().frame(0);
        //driver.switchTo().frame("frame-one85593366");
        driver.switchTo().frame(formIframe);

        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-3"))).selectByVisibleText("East Dorm");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input#RESULT_RadioButton-4_0")));
        sleepInSeconds(3);

        // Thao tác vs 1 element bên ngoài iframe (trang A)
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("button#login")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(), "Username and password are both required.");
    }

    @Test
    public void TC_02_Frame_HDFC_Bank() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        WebElement formIframe = driver.findElement(By.cssSelector("frame[name='login_page']"));
        Assert.assertTrue(formIframe.isDisplayed());
        sleepInSeconds(3);

        driver.switchTo().frame(formIframe);

        driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("luis_suarez");
        driver.findElement(By.cssSelector("a.login-btn")).click();
        sleepInSeconds(5);

        Assert.assertTrue(driver.findElement(By.cssSelector("input#keyboard")).isDisplayed());

        driver.findElement(By.cssSelector("input#keyboard")).sendKeys("123456789");
        sleepInSeconds(3);
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
