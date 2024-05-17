package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_19_JavascriptExecutor {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        // Ép kiểu tường minh
        // Từ kiểu dữ liệu này qua kiểu khác
        jsExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_TechPanda() {
        executeForBrowser("window.location = 'http://live.techpanda.org/'");
        //navigateToUrlByJS("http://live.techpanda.org/");
        sleepInSeconds(5);

        String terchPandaTitle = (String) jsExecutor.executeScript("return document.title;");
        Assert.assertEquals(terchPandaTitle, "Home page");

        String terchPandaDomain = (String) jsExecutor.executeScript("return document.domain;");
        Assert.assertEquals(terchPandaDomain, "live.techpanda.org");

        String terchPandaUrl = (String) jsExecutor.executeScript("return document.URL;");
        Assert.assertEquals(terchPandaUrl, "http://live.techpanda.org/");

        hightlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");
        sleepInSeconds(3);

        clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");

        Assert.assertTrue(isExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

        // Ko giống hành vi thực của End User
        hightlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");
        sleepInSeconds(5);

        String customerServiceTitle = (String) jsExecutor.executeScript("return document.title;");
        Assert.assertEquals(customerServiceTitle, "Customer Service");

        scrollToBottomPage();

        sendkeyToElementByJS("//input[@id='newsletter']", getEmailAddress());

        clickToElementByJS("//button[@title='Subscribe']");

        Assert.assertTrue(isExpectedTextInInnerText("Thank you for your subscription."));
        Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));

        navigateToUrlByJS("https://www.facebook.com/");
        sleepInSeconds(3);

        Assert.assertEquals(jsExecutor.executeScript("return document.domain;"), "facebook.com");
    }

    @Test
    public void TC_02_AutomationFC() {
        navigateToUrlByJS("https://automationfc.github.io/html5/index.html");
        sleepInSeconds(5);

        clickToElementByJS("//input[@name='submit-btn']");
        sleepInSeconds(3);

        Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"), "Please fill out this field.");

        sendkeyToElementByJS("//input[@id='fname']", "Dam Dao");

        Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"), "Please fill out this field.");

        sendkeyToElementByJS("//input[@id='pass']", "123456");

        Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please fill out this field.");

        sendkeyToElementByJS("//input[@id='em']", "123!@#$");
        sleepInSeconds(3);

        clickToElementByJS("//input[@name='submit-btn']");

        Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please enter an email address.");

        sendkeyToElementByJS("//input[@id='em']", "daominhdam@gmail.com");

        Assert.assertEquals(getElementValidationMessage("//select"), "Please select an item in the list.");
    }

    @Test
    public void TC_03_SieuThi() {
        driver.get("https://sieuthimaymocthietbi.com/account/register");

        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(getElementValidationMessage("//input[@id='lastName']"), "Please fill out this field.");
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Automation");
        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(getElementValidationMessage("//input[@id='firstName']"), "Please fill out this field.");
        driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Testing");
        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(getElementValidationMessage("//input[@id='email']"), "Please fill out this field.");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("aa@bb@cc");
        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(getElementValidationMessage("//input[@id='email']"), "Please enter an email address.");
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
        sleepInSeconds(2);

        Assert.assertEquals(getElementValidationMessage("//input[@id='password']"), "Please fill out this field.");
    }

    @Test
    public void TC_04_TechPanda() {
        navigateToUrlByJS("http://live.techpanda.org/");
        sleepInSeconds(5);

        clickToElementByJS("//a[contains(@class,'skip-account')]");
        sleepInSeconds(3);

        clickToElementByJS("//div[@id='header-account']//a[@title='My Account']");
        sleepInSeconds(3);

        clickToElementByJS("//a[@title='Create an Account']");
        sleepInSeconds(3);

        String firstName = "Automation", lastName = "FC", emailAddress = getEmailAddress(), password = "123456789";
        String fullName = firstName + " " + lastName;

        sendkeyToElementByJS("//input[@id='firstname']", firstName);
        sendkeyToElementByJS("//input[@id='lastname']", lastName);
        sendkeyToElementByJS("//input[@id='email_address']", emailAddress);
        sendkeyToElementByJS("//input[@id='password']", password);
        sendkeyToElementByJS("//input[@id='confirmation']", password);
        clickToElementByJS("//button[@title='Register']");
        sleepInSeconds(2);

        Assert.assertTrue(isExpectedTextInInnerText("Thank you for registering with Main Website Store."));

        // Logout
        clickToElementByJS("//a[contains(@class,'skip-account')]");
        sleepInSeconds(2);
        clickToElementByJS("//a[@title='Log Out']");
        sleepInSeconds(5);

        String title = (String) jsExecutor.executeScript("return document.title;");
        Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title")).isDisplayed());
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

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInSeconds(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInSeconds(3);
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    public String getEmailAddress() {
        return "kevinlamp" + new Random().nextInt(99999) + "@gmail.com";
    }
}
