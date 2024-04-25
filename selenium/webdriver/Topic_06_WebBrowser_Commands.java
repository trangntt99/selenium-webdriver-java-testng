package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands {
    // Các câu lệnh để thao tác với Browser
    // driver.
    WebDriver driver;

    // Các câu lệnh thao tác với Element
    // element.
    WebElement element;

    @BeforeClass
    public void beforeClass() {
        // Muốn dùng được thì phải khởi tạo
        // Nếu ko khởi tạo sẽ gặp lỗi: NullPointerException
        driver = new FirefoxDriver(); //**
        // System.out.println(driver.toString());
        // FirefoxDriver: firefox on windows (721a5529-f50f-4e34-8bdd-9d8a5d1e3176)
        // GUID: Gobal Unique Identifier Number (duy nhất ko trùng lặp)

        driver = new ChromeDriver();
        driver = new EdgeDriver();
        driver = new SafariDriver();
        driver = new InternetExplorerDriver();
        // driver = new OperaDriver(); Selenium 4 ngưng support
        // driver = new HTMLUnit(); Headess browser
        // Từ năm 2016: Chrome/ Firefox có support chạy dạng headless
        // Headless: Crawl data (Data Analyst)/ Dev FE

        // Selenium ver 3/2/1 (Deprecated)
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // Selenium ver 4
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //**
    }

    @Test
    public void TC_01_() throws MalformedURLException {
        // Mở ra 1 page Url bất kì
        driver.get("https://www.facebook.com/"); //**

        // Nếu như có 1 tab/ window thì tính năng tương tự như quit
        // Nhiều hơn 1 thì nó sẽ đóng cái nó đang active (close tab)
        driver.close(); //*

        // Đóng browser (ko care bao nhiêu tab/ window)
        driver.quit(); //**

        // Nó sẽ đi tìm với loại By nào và trả về element nếu như được tìm thấy (WebElement)
        // Ko được tìm thấy: Fail tại step này - throw Exception:  NoSuchElementException
        // Trả về 1 element - Nếu như tìm tấy nhiều hơn 1 thì cũng chỉ lấy 1 (thao tác với cái đầu tiên)
        WebElement emailAddressTextbox = driver.findElement(By.id("email")); //**

        // 2 hàm này sẽ bị ảnh hưởng timeout của implicitlyWait
        // findElement/ findElements

        // Nó sẽ đi tìm với loại By nào và trả về 1 danh sách Element nếu như được tìm thấy (List WebElement)
        // Ko được tìm thấy: ko bị fail - trả về 1 List rỗng (0 element)
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox'")); //**

        // Tại sao lại cần phải lấy dữ liệu ra để làm cái gì?
        // Dùng để lấy ra Url của màn hình/ page hiện tại đang đứng
        driver.getCurrentUrl(); //*

        // Nếu chỉ dùng 1 lần thì ko khai báo biến
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");

        // Lấy ra page source HTML/ CSS/ JS của page hiện tại
        // Verify 1 cách tương đối
        driver.getPageSource();
        driver.getCurrentUrl().contains("The Apple and Google Play logos are trademarks of their respective owners.");
        Assert.assertTrue(driver.getCurrentUrl().contains("The Apple and Google Play logos are trademarks of their respective owners."));

        // Lấy ra title của pae hiện taại
        driver.getTitle();

        // Lấy ra ID của cửa sổ/ tab hiện tại
        // Handle Window/ Tab
        driver.getWindowHandle(); //*
        driver.getWindowHandles(); //*

        // Cookies - Framework
        driver.manage().getCookies(); //*

        // Get ra những cái log ở Dev Tool - Framework
        driver.manage().logs().get(LogType.DRIVER); //*

        // Apply cho việc tìm element (findElement/ findElements)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); //**

        // Chờ cho page được load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        // Set trước khi dùng với thư viện JavascriptExecutor
        // Inject 1 đoạn code JS vào trong Browser/ Element
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        // Selenium 4 mới có
        driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().getPageLoadTimeout();
        driver.manage().timeouts().getScriptTimeout();

        // Chạy full màn hình
        driver.manage().window().fullscreen();
        driver.manage().window().maximize(); //**
        driver.manage().window().minimize();

        // Test GUI
        // Test Responsive (Resolution)
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.manage().window().getSize();

        // Set cho browser ở vị trí nào so với độ phân giải màn hình (run trên màn hình có kích thước bao nhiêu)
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().getPosition();

        // Điiều hướng trang web
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        // Thao tác với history của web page (back/ forward)
        driver.navigate().to("https://www.facebook.com/");
        driver.navigate().to(new URL("https://www.facebook.com/"));

        // Alert/ Window (Tab)/ Frame (iFrame) //*
        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("Test");

        // Lấy ra ID của cửa sổ/ tab hiện tại //*
        // Handle Window/ Tab
        String homePageWindowID = driver.getWindowHandle();
        driver.switchTo().window(homePageWindowID);

        // Switch/ handle frame (iframe) //*
        // Index/ ID (name)/ Element
        driver.switchTo().frame(0);
        driver.switchTo().frame("39493494");
        driver.switchTo().frame(driver.findElement(By.id("")));

        // Switch về HTML chứa frame trước đó
        driver.switchTo().defaultContent();

        // Từ frame trong đi ra frame ngoài chứa nó
        driver.switchTo().parentFrame();

    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
