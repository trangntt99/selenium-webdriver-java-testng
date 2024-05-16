package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_18_Window_Tab {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Basic_Form() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        String parentID = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Google");

        Assert.assertEquals(driver.getTitle(), "Google");
        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium");
        sleepInSeconds(3);

        switchToWindowByTitle("Selenium WebDriver");

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Facebook – log in or sign up");

        Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");

        switchToWindowByTitle("Selenium WebDriver");

        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        boolean isClosed = closeAllWindowsWithoutParent(parentID);
        sleepInSeconds(3);

        if (isClosed) {
            Assert.assertEquals(driver.getTitle(), "Selenium WebDriver");
            Assert.assertEquals(driver.getCurrentUrl(), "https://automationfc.github.io/basic-form/index.html");
        }
    }

    @Test
    public void TC_02_TechPanda() {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Mobile");
        driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product IPhone has been added to comparison list.");

        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");

        //driver.close();
        driver.findElement(By.cssSelector("button[title='Close Window']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Mobile");

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        sleepInSeconds(3);

        driver.switchTo().alert().accept();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The comparison list was cleared.");
    }

    @Test
    public void TC_03_Selenium_Version_4x() {
        driver.get("http://live.techpanda.org/");
        System.out.println("Title = " + driver.getTitle());

        // New 1 tab mới/ window mới
        WebDriver newWindow = driver.switchTo().newWindow(WindowType.TAB);
        newWindow.get("http://live.techpanda.org/index.php/customer/account/login/");
        System.out.println("Title = " + newWindow.getTitle());

        newWindow.findElement(By.cssSelector("input#email")).sendKeys("dam@gmail.com");
        newWindow.findElement(By.cssSelector("input#pass")).sendKeys("dam@gmail.com");
        sleepInSeconds(5);

        WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
        newTab.get("http://live.techpanda.org/index.php/customer/account/create/");
        System.out.println("Title = " + newWindow.getTitle());

        newTab.findElement(By.cssSelector("input#firstname")).sendKeys("Dam");
        sleepInSeconds(5);

        driver.switchTo().newWindow(WindowType.TAB).get("http://live.techpanda.org/index.php/catalogsearch/term/popular/");
        System.out.println("Title = " + newWindow.getTitle());
    }

    @Test
    public void TC_04_Dictionary_Cambridge() {
        driver.get("https://dictionary.cambridge.org/vi/");

        driver.findElement(By.cssSelector("span.cdo-login-button")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Login");
        driver.findElement(By.cssSelector("input[value='Log in']")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='gigya-login-screen']//input[@name='username']/following-sibling::span")).getText(), "This field is required");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='gigya-login-screen']//input[@name='password']/following-sibling::span")).getText(), "This field is required");

        driver.close();
        sleepInSeconds(3);

        switchToWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
        driver.findElement(By.cssSelector("input#searchword")).sendKeys("automation");
        driver.findElement(By.cssSelector("button[aria-label='Search']")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.page>div:first-child span.headword>span")).getText(), "automation");
    }

    @Test
    public void TC_05_Harvard() {
        driver.get("https://courses.dce.harvard.edu/");

        driver.findElement(By.cssSelector("a[data-action='login']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Harvard Division of Continuing Education Login Portal");
        sleepInSeconds(3);

        Assert.assertTrue(driver.findElement(By.cssSelector("main.login>section")).isDisplayed());

        driver.close();
        sleepInSeconds(3);

        switchToWindowByTitle("DCE Course Search");

        Assert.assertTrue(driver.findElement(By.cssSelector("div#sam-wait")).isDisplayed());

        driver.findElement(By.cssSelector("button.sam-wait__close")).click();
        sleepInSeconds(3);

        driver.findElement(By.cssSelector("input#crit-keyword")).sendKeys("Data Science: An Artificial Ecosystem");
        new Select(driver.findElement(By.cssSelector("select#crit-srcdb"))).selectByVisibleText("Harvard Summer School 2024");
        new Select(driver.findElement(By.cssSelector("select#crit-summer_school"))).selectByVisibleText("Harvard College");
        new Select(driver.findElement(By.cssSelector("select#crit-session"))).selectByVisibleText("Any Part of Term");
        driver.findElement(By.cssSelector("button#search-button")).click();
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("span.result__title")).getText(), "Data Science: An Artificial Ecosystem");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void switchToWindowByID(String expectedID) {
        // Lấy ra ID của tất cả các window/tab đang có
        Set<String> allIDs = driver.getWindowHandles();

        // Dùng vòng lặp duyệt qua từng ID
        for (String id : allIDs) {
            // Nếu như 1 ID nào mà khác vs basicFormID thì switch vào
            if (!id.equals(expectedID)) {
                driver.switchTo().window(id);
                break;
            }
        }
    }

    public void switchToWindowByTitle(String expectedTitle) {
        // Lấy hết tất cả ID của các window/tab
        Set<String> allIDs = driver.getWindowHandles();

        // Dùng vòng lặp duyệt qua Set ID ở trên
        for (String id : allIDs) {
            // Cho switch vào từng ID trước
            driver.switchTo().window(id);

            // Lấy ra title của tab/ window hiện tại
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle)) {
                break;
            }
        }
    }

    public boolean closeAllWindowsWithoutParent(String parentID) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
        if (driver.getWindowHandles().size() == 1) {
            return true;
        } else {
            return false;
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
