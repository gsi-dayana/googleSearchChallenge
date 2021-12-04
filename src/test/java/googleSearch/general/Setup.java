package googleSearch.general;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class Setup {
    private static WebDriver driver;
    private static Actions actions;
    private static WaitingObject waitingObject;
    private static String defaultURL = "https://www.google.com/";
    public static Map<String, Object> timeouts;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        driver = new ChromeDriver();
        //driver.get("https://www.google.com/");
        ChromeOptions options = new ChromeOptions();
        timeouts = new HashMap<String, Object>();
        timeouts.put("implicit", 50);
        timeouts.put("pageLoad", 5000000);
        timeouts.put("script", 300000);
        options.setCapability("timeouts", timeouts);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        actions = new Actions(driver);
        waitingObject = new WaitingObject(driver);
        System.setProperty("defaultURL", defaultURL);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static WaitingObject getWaitTime() {
        return waitingObject;
    }
    public static Map<String, Object> getTimeouts() {
        return timeouts;
    }

    void setTimeouts(Map<String, Object> timeouts) {
        Setup.timeouts = timeouts;
    }

    public static Actions getActions() {
        return actions;
    }

    public static void setActions(Actions actions) {
        Setup.actions = actions;
    }

    public static void sendAction(WebElement element, String value, userAction action) {
        actions.moveToElement(element).build().perform();
        switch (action) {
            case type:
                actions.sendKeys(element, value).build().perform();
                break;
            case click:
                actions.click(element).build().perform();
                break;
            default:
                System.out.print("There is not need to apply any action");
                break;
        }
    }

    public static void openUrl(String url) {
        driver.get(url);
    }
    public static void print(Object string) {
        System.out.println(string);
    }

    /*public WebElement getWebElement(By by) {
        return this.getDriver().findElement(by);
    }

    protected List<WebElement> getWebElements(By by) {
        return this.getDriver().findElements(by);
    }*/

    public String getCurrentUrl() {
        return this.getDriver().getCurrentUrl();
    }

    @After
    public void tearDow() {
        getWaitTime().thread(2000);
        driver.quit();
    }
}
