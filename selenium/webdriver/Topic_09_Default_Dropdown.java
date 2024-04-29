package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_09_Default_Dropdown {
    WebDriver driver;
    String firstName = "Kevin", lastName = "Lamping", emailAddress = getEmailAddress();
    String companyName = "Selenium WebDriver", password = "123456";
    String day = "1", month = "May", year = "1980";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com");
    }

    @Test
    public void TC_01_Register() {
        driver.findElement(By.cssSelector("a.ico-register")).click();
        sleepInSeconds(2);

        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);

        // Day Dropdown
        // Chọn ngày
        Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
        day.selectByVisibleText(this.day);

        // Verify dropdown này là Single (ko phải Multiple)
        Assert.assertFalse(day.isMultiple());

        // Verify số lượng item trong Dropdown này là 32 item
        Assert.assertEquals(day.getOptions().size(), 32);

        Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        month.selectByVisibleText(this.month);

        // Verify dropdown này là Single (ko phải Multiple)
        Assert.assertFalse(month.isMultiple());

        // Verify số lượng item trong Dropdown này là 13 item
        Assert.assertEquals(month.getOptions().size(), 13);

        Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
        year.selectByVisibleText(this.year);

        // Verify dropdown này là Single (ko phải Multiple)
        Assert.assertFalse(year.isMultiple());

        // Verify số lượng item trong Dropdown này là 112 item
        Assert.assertEquals(year.getOptions().size(), 112);

        driver.findElement(By.id("Email")).sendKeys(emailAddress);
        driver.findElement(By.id("Company")).sendKeys(companyName);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
        driver.findElement(By.id("register-button")).click();
        sleepInSeconds(2);


        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
    }

    @Test
    public void TC_02_Login() {
        driver.get("https://demo.nopcommerce.com");

        driver.findElement(By.cssSelector("a.ico-login")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("a.ico-account")).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);
        Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
        Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
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

    public String getEmailAddress() {
        return "kevinlamp" + new Random().nextInt(99999) + "@gmail.net";
    }
}
