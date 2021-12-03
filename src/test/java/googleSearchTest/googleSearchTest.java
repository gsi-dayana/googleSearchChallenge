package googleSearchTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class googleSearchTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        driver = new ChromeDriver();
        driver.get("https://www.google.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void testGoogleSearch() {
        try {
            WebElement searchInputElement = driver.findElement(By.name("q"));
            searchInputElement.clear();
            String searchValue = "Selenium";
            searchInputElement.sendKeys(searchValue, Keys.RETURN);

            waitTime();
            String xpath = "//div[@id='rcnt']/descendant::a";
            List<WebElement> results = driver.findElements(By.xpath(xpath));
            waitTime();
            List<WebElement> firstSearchResultName = driver.findElements(By.xpath(xpath + "/h3"));
            waitTime();
            String firstLink = results.get(0).getAttribute("href");
            String firstResultTitle = firstSearchResultName.get(0).getText();
            clickElement(results.get(0));

            validate(searchValue, firstResultTitle, "Search Title Validation");
            validate(firstLink, driver.getCurrentUrl(), "Search Link Validation");
        } catch (Exception e) {
            print("An error occured: " + e.getMessage());
        }

    }

    public void validate(String value1, String value2, String Action) {
        try {
            if (value1.equals(value2)) {
                print(Action + ":\n Test Result: Validation passed");
            } else {
                print(Action + ":\n Test Result: Validation failed \n Expected value: " + value1 + "\n Actual value: " + value2);
            }
        } catch (Exception e) {
            print("An error occured: " + e.getMessage());
        }
    }

    public void clickElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        action.click(element).build().perform();
    }

    public void waitTime() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void print(String value) {
        System.out.println(value);
    }

    @After
    public void tearDow() {
        driver.quit();
    }
}
