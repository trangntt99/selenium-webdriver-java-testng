package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_FindElement {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        driver.get("http://live.techpanda.org/index.php/");

        // Click vào My Account ở trên Header
        // Ko hiển thị để thao tác nên sẽ fail
        // Cơ chế của Selenium: findElement sẽ luôn thao tác vs element đầu tiên được tìm thấy
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

        // Có nhiều thng tin để tìm ra element này nếu testcript chạy bị lỗi
        //  Reproduce xem có đúng là bug hay không

        // Sẽ đi tìm những nguyên nhân khách quan
        // Code mình viết sai
        // Thiếu thời gian (timeout)
        // Page chưa load xong
        // ...

        //String successMessageText = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
        //Assert.assertEquals(successMessageText, "Samsung Galaxy was added to your shopping cart");
    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
