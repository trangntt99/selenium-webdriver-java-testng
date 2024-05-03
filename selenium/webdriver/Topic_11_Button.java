package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_11_Button {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Egov_Button() {
        driver.get("https://egov.danang.gov.vn/reg");

        WebElement registerButton = driver.findElement(By.cssSelector("input.egov-button"));

        // Verify button bị disable khi chưa click vào checkbox
        Assert.assertFalse(registerButton.isEnabled());

        driver.findElement(By.cssSelector("input#chinhSach")).click();
        sleepInSeconds(2);

        // Verify button đã được enable sau khi click vào checkbox
        Assert.assertTrue(registerButton.isEnabled());

        // Lấy ra mã màu nền của button
        String registerBackgroundRGB = registerButton.getCssValue("background-color");
        System.out.println("Background color RGB = " + registerBackgroundRGB);

        Color registerBackgroundColour = Color.fromString(registerBackgroundRGB);

        // Convert qua kiểu Hexa
        String registerBackGroundHexa = registerBackgroundColour.asHex();
        System.out.println("Background color Hexa = " + registerBackGroundHexa);

        // Convert qua viết thường
        Assert.assertEquals(registerBackGroundHexa.toLowerCase(), "#ef5a00");
        Assert.assertEquals(Color.fromString(registerButton.getCssValue("background-color")).asHex().toLowerCase(), "#ef5a00");
    }

    @Test
    public void TC_02_Fahasa_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");

        // // Chuyển qua tab Đăng nhập
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        sleepInSeconds(2);

        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));

        // Verify login button is disabled
        Assert.assertFalse(loginButton.isEnabled());

        // Verify login button = background
        System.out.println(loginButton.getCssValue("background-color"));
        Assert.assertEquals(loginButton.getCssValue("background-color"), "rgba(0, 0, 0, 0)");

        // Nhập email/ pass
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("auto@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");

        // Verify login button is enabled
        Assert.assertTrue(loginButton.isEnabled());

        // Verify login button = background
        System.out.println(loginButton.getCssValue("background-color"));
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(), "#c92127");
    }

    @Test
    public void TC_03_Default_Checkbox_Radio_Button() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).click();
        sleepInSeconds(1);
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).isSelected());

        driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).click();
        sleepInSeconds(1);
        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")).isSelected());

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input")).click();
        sleepInSeconds(1);
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input")).isSelected());

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
