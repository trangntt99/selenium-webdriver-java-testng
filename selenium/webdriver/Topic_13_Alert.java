package webdriver;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v118.network.Network;
import org.openqa.selenium.devtools.v118.network.model.Headers;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Topic_13_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;
    By resultText = By.cssSelector("p#result");
    String projectLocation = System.getProperty("user.dir");
    String username = "admin";
    String password = "admin";

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        sleepInSeconds(2);

        // Chờ cho alert present
        // Nếu trong thời gian chờ mà xuất hiện thì tự switch vào
        // Nếu hết thời gian chờ mà chưa xuất hiện mới fail
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        alert.accept();

        Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked an alert successfully");
    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        sleepInSeconds(2);

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        alert.dismiss();

        Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");

    }

    @Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        sleepInSeconds(2);

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        String text = "Selenium WebDriver";
        alert.sendKeys(text);
        alert.accept();

        Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: " + text);
    }

    @Test
    public void TC_04_Authenticatio_ByPass_To_URL() {
        // Cách 1: Truyền thẳng user/ pass vào Url
        // Trick - ByPass
        //driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
        //Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());

        // Cách 2: Từ page A thao tác lên 1 element nó sẽ qua page B (cần phải thao tác với Authen Alert trước
        driver.get("http://the-internet.herokuapp.com/");

        String authenLinkUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");

        driver.get(getAuthenAlertByUrl(authenLinkUrl, username, password));
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    @Test
    public void TC_05_Authentication_AutoIT() throws IOException {
        // Cách 2: Chạy trên Windows (AutoIT)
        // MAC/ Linux ko work
        // Mỗi browser sẽ cần 1 đoạn script khác nhau
        // Thực thi đoạn code AutoIT để chờ Alert xuất hiện
        driver.get("http://the-internet.herokuapp.com/basic_auth");
        Runtime.getRuntime().exec(new String[] { projectLocation + "\\autoIT\\authen_firefox.exe", "admin", "admin" });
        sleepInSeconds(10);
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    //@Test
    public void TC_06_Authentication_Selenium_4x() {
        // Cách 3:
        // Thư viện Alert ko sử dụng cho Authentication Alert được
        // Chrome DevTool Protocol (CDP) - Chrome/ Edge (Chromium)
        // Cốc Cốc/ Opera/ Brave (Chromium) - Work Around

        //driver = new ChromeDriver();

        // Get DevTool object
        DevTools devTools = ((HasDevTools) driver).getDevTools();

        // Start new session
        devTools.createSession();

        // Enable the Network domain of devtools
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Encode username/ password
        Map<String, Object> headers = new HashMap<String, Object>();
        String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s", username, password).getBytes()));
        headers.put("Authorization", basicAuthen);

        // Set to Header
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.get("https://the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public String getAuthenAlertByUrl(String url, String username, String password) {
        String[] authenArray = url.split("//");
        return authenArray[0] +  "//" + username + ":" + password + "@" + authenArray[1];
    }

    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
