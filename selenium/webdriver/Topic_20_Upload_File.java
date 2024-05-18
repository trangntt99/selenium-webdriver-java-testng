package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_20_Upload_File {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String bg1Name = "bg1.jpg";
    String bg2Name = "bg2.jpg";
    String bg3Name = "bg3.jpg";

    //String character = Platform.getCurrent().is(Platform.WINDOWS) ? "\\" : "/";
    String character = File.separator;

    String bg1FilePath = projectPath + character + "uploadFiles" + character + bg1Name;
    String bg2FilePath = projectPath + character + "uploadFiles" + character + bg2Name;
    String bg3FilePath = projectPath + character + "uploadFiles" + character + bg3Name;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Single_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(bg1FilePath);
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(bg2FilePath);
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(bg3FilePath);
        sleepInSeconds(2);

        // Verify file loaded success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + bg1Name + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + bg2Name + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + bg3Name + "']")).isDisplayed());

        List<WebElement> startButtons = driver.findElements(By.cssSelector("td>button.start"));
        for (WebElement button : startButtons) {
            if (button.isDisplayed()) {
                button.click();
                sleepInSeconds(3);
            }
        }

        // Verify file loaded success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + bg1Name + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + bg2Name + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + bg3Name + "']")).isDisplayed());
    }

    @Test
    public void TC_02_Multiple_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(bg1FilePath + "\n" + bg2FilePath + "\n" + bg3FilePath);
        sleepInSeconds(2);

        // Verify file loaded success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + bg1Name + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + bg2Name + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + bg3Name + "']")).isDisplayed());

        List<WebElement> startButtons = driver.findElements(By.cssSelector("td>button.start"));
        for (WebElement button : startButtons) {
            if (button.isDisplayed()) {
                button.click();
                sleepInSeconds(3);
            }
        }

        // Verify file loaded success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + bg1Name + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + bg2Name + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + bg3Name + "']")).isDisplayed());
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
