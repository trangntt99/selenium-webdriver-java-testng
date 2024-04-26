package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.Key;
import java.time.Duration;
import java.util.List;

public class Topic_07_WebElement_Commands_02 {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Displayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Nếu như mong đợi 1 element hiển thị thì verifyTrue
        // Nếu như mong đợi 1 element ko hiển thị thì verifyFalse
        // isDisplayed() = true/ false

        if (driver.findElement(By.id("mail")).isDisplayed()) {
            driver.findElement(By.id("mail")).sendKeys("Automation Testing");
            System.out.println("Email Textbox is displayed");
        } else {
            System.out.println("Email Textbox is not displayed");
        }

        if (driver.findElement(By.id("under_18")).isDisplayed()) {
            driver.findElement(By.id("under_18")).click();
            System.out.println("Under 18 Radio is displayed");
        } else {
            System.out.println("Under 18 Radio is not displayed");
        }

        if (driver.findElement(By.id("mail")).isDisplayed()) {
            driver.findElement(By.id("edu")).sendKeys("Automation Testing");
            System.out.println("Education Textbox is displayed");
        } else {
            System.out.println("Education Textbox is not displayed");
        }

        if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
            System.out.println("Name User5 Text is displayed");
        } else {
            System.out.println("Name User5 Text is not displayed");
        }
    }

    @Test
    public void TC_02_Enabled() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if (driver.findElement(By.id("mail")).isEnabled()) {
            System.out.println("Email Textbox is enabled");
        } else {
            System.out.println("Email Textbox is disabled");
        }

        if (driver.findElement(By.id("under_18")).isEnabled()) {
            System.out.println("Under 18 Radio is enabled");
        } else {
            System.out.println("Under 18 Radio is disabled");
        }

        if (driver.findElement(By.id("edu")).isEnabled()) {
            System.out.println("Education Textbox is enabled");
        } else {
            System.out.println("Education Textbox is disabled");
        }

        if (driver.findElement(By.id("job1")).isEnabled()) {
            System.out.println("Job Role 01 Dropdown is enabled");
        } else {
            System.out.println("Job Role 01 Dropdown is disabled");
        }

        if (driver.findElement(By.id("job2")).isEnabled()) {
            System.out.println("Job Role 02 Dropdown is enabled");
        } else {
            System.out.println("Job Role 02 Dropdown is disabled");
        }

        if (driver.findElement(By.id("development")).isEnabled()) {
            System.out.println("Development Checkbox is enabled");
        } else {
            System.out.println("Development Checkbox is disabled");
        }

        if (driver.findElement(By.id("slider-1")).isEnabled()) {
            System.out.println("Slider 1 is enabled");
        } else {
            System.out.println("Slider 1 is disabled");
        }

        if (driver.findElement(By.id("disable_password")).isEnabled()) {
            System.out.println("Password Textbox is enabled");
        } else {
            System.out.println("Password Textbox is disabled");
        }

        if (driver.findElement(By.id("radio-disabled")).isEnabled()) {
            System.out.println("Age Checkbox is enabled");
        } else {
            System.out.println("Age Checkbox is disabled");
        }

        if (driver.findElement(By.id("bio")).isEnabled()) {
            System.out.println("Biography TextArea is enabled");
        } else {
            System.out.println("Biography TextArea is disabled");
        }

        if (driver.findElement(By.id("job3")).isEnabled()) {
            System.out.println("Job Role 03 Dropdown is enabled");
        } else {
            System.out.println("Job Role 03 Dropdown is disabled");
        }

        if (driver.findElement(By.id("check-disbaled")).isEnabled()) {
            System.out.println("Interests Checkbox is enabled");
        } else {
            System.out.println("Interests Checkbox is disabled");
        }

        if (driver.findElement(By.id("slider-2")).isEnabled()) {
            System.out.println("Slider 2 is enabled");
        } else {
            System.out.println("Slider 2 is disabled");
        }
    }

    @Test
    public void TC_03_Selected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.id("under_18")).click();
        driver.findElement(By.id("java")).click();

        Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
        Assert.assertTrue(driver.findElement(By.id("java")).isSelected());

        driver.findElement(By.id("java")).click();
        sleepInSeconds(2);

        Assert.assertTrue(driver.findElement(By.id("under_18")).isSelected());
        Assert.assertFalse(driver.findElement(By.id("java")).isSelected());
    }

    @Test
    public void TC_04_MailChimp() {
        driver.get("https://login.mailchimp.com/signup/");

        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.com");

        // Case 1 - Number
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("12345");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());

        // Case 2 - LowerCase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("auto");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());

        // Case 3 - UpperCase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("AUTO");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());

        // Case 4 - Special Chars
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("!@#$");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());

        // Case 5 - Max Length
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("12345678");
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());

        // Case 6 - Valid
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Auto123!A#");
        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());

        // Case 7 - Empty Data
        driver.navigate().refresh();
        driver.findElement(By.cssSelector("input#email")).sendKeys("automationfc@gmail.com");
        driver.findElement(By.cssSelector("input#new_password")).sendKeys(Keys.TAB);
        sleepInSeconds(3);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());

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
