package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_12_Checkbox_Radio {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Default_Telerik_Checkbox() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        By rearSideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span/input");
        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        // Click vào checkbox
        // Case 1: Nếu như app này mở ra mà checkbox đã được chọn thì sao
        checkToElement(rearSideCheckbox);

        // Case 2: Nếu như app này mở ra mà checkbox chưa được chọn
        checkToElement(dualZoneCheckbox);

        // Verify checkbox đã được chọn thành công
        Assert.assertTrue(driver.findElement(rearSideCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

        // Bỏ chọn 2 checkbox này
        uncheckToElement(rearSideCheckbox);
        uncheckToElement(dualZoneCheckbox);

        // Verify checkbox đã được bỏ chọn thành công
        Assert.assertFalse(driver.findElement(rearSideCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());
    }

    @Test
    public void TC_02_Default_Telerik_Radio() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        By twoPetrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");
        By twoDieselRadio = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::span/input");

        // Click chọn 1 trong 2
        checkToElement(twoPetrolRadio);

        // Verify
        Assert.assertTrue(driver.findElement(twoPetrolRadio).isSelected());
        Assert.assertFalse(driver.findElement(twoDieselRadio).isSelected());

        checkToElement(twoDieselRadio);

        // Verify
        Assert.assertFalse(driver.findElement(twoPetrolRadio).isSelected());
        Assert.assertTrue(driver.findElement(twoDieselRadio).isSelected());
    }

    @Test
    public void TC_03_Default_Material_Radio_Checkbox() {
        driver.get("https://material.angular.io/components/radio/examples");

        By summerRadio = By.xpath("//input[@value='Summer']");
        checkToElement(summerRadio);
        Assert.assertTrue(driver.findElement(summerRadio).isSelected());

        driver.get("https://material.angular.io/components/checkbox/examples");
        By checkedCheckbox = By.xpath("//label[text()='Checked']/preceding-sibling::div/input");
        By indeterminateCheckbox = By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input");

        checkToElement(checkedCheckbox);
        checkToElement(indeterminateCheckbox);

        // Verify
        Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());

        uncheckToElement(checkedCheckbox);
        uncheckToElement(indeterminateCheckbox);

        // Verify
        Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());
    }

    @Test
    public void TC_04_Select_All_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");

        // Chọn hết tất cả các checkbox trong màn hình đó
        List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));
        for (WebElement checkbox : allCheckboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }

        // Verify hết tất cả các checkbox{
        for (WebElement checkbox : allCheckboxes) {
            Assert.assertTrue(checkbox.isSelected());
        }

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        // Chọn 1 checkbox/ radio nào đó trong tất cả các checkbox/ radio
        allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

        for (WebElement checkbox : allCheckboxes) {
            if (checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()) {
                checkbox.click();
            }
        }

        // Verify hết tất cả các checkbox{
        for (WebElement checkbox : allCheckboxes) {
            if (checkbox.getAttribute("value").equals("Heart Attack")) {
                Assert.assertTrue(checkbox.isSelected());
            } else {
                Assert.assertFalse(checkbox.isSelected());
            }
        }

    }

    @Test
    public void TC_05_Custom_Checkbox() {
        // Case 1: Dùng thẻ input để click => Thẻ input bị che bởi 1 element khác => Failed
        // WebElement click(): The element must be visible, and it must have a height and a width greater than 0
        // isSelected: obly applies to input elements

        // Case 2:
        // Dùng thẻ div bên ngoài để click => Passed
        // Dùng thẻ div để verìy => Failed

        // Case 3:
        // Dùng thẻ div bên ngoài để click => Passed
        // Dùng thẻ input để verìy => Passed
        // 1 element mà cần define tới 2 locator thì sau này => Maintain mất nhiều thời gian hơn

        // Case 4:
        // Dùng thẻ input để click => JavascriptExecutor (JS)
        // Dùng thẻ input để verify => isSelected: obly applies to input elements
        // Chỉ cần 1 locator

        driver.get("https://login.ubuntu.com/");

        // interface WebDriver
        // interface JavascriptExecutor
        // Ép kiểu interface qua kiểu interface khác
        By registerRadio = By.xpath("//input[@id='id_new_user']");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(registerRadio));
        sleepInSeconds(2);

        By acceptCheckbox = By.xpath("//input[@id='id_accept_tos']");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(acceptCheckbox));
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(registerRadio).isSelected());
        Assert.assertTrue(driver.findElement(acceptCheckbox).isSelected());
    }

    @Test
    public void TC_06_Custom_Google_Docs() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        By canThoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
        By quangNamCheckbox = By.xpath("//div[@aria-label='Quảng Nam']");
        By quangBinhCheckbox = By.xpath("//div[@aria-label='Quảng Bình']");

        // Verify radio is not selected
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"), "false");

        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='false']")).isDisplayed());

        driver.findElement(canThoRadio).click();
        driver.findElement(quangNamCheckbox).click();
        driver.findElement(quangBinhCheckbox).click();
        sleepInSeconds(2);

        // Verify radio is selected
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"), "true");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());

        Assert.assertEquals(driver.findElement(quangNamCheckbox).getAttribute("aria-checked"), "true");
        Assert.assertEquals(driver.findElement(quangBinhCheckbox).getAttribute("aria-checked"), "true");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void checkToElement(By byXpath) { // Nếu như element chưa được chọn thì click
        if (!driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
            sleepInSeconds(2);
        }
    }

    public void uncheckToElement(By byXpath) {
        if (driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
            sleepInSeconds(2);
        }
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
