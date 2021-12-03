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
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
    }

    @Test
    public void testGoogleSearch() {
        WebElement searchInputElement = driver.findElement(By.name("q"));
        searchInputElement.clear();
        String searchValue = "Selenium";
        searchInputElement.sendKeys(searchValue, Keys.RETURN);

        waitTime();
        //List<WebElement> firstSearchResultName = driver.findElements(By.xpath("//div[@id='rcnt']/descendant::h3"));
        String xpath = "//div[@id='rcnt']/descendant::a";
        List<WebElement> results = driver.findElements(By.xpath(xpath));
        waitTime();
        WebElement firstSearchResultName = driver.findElement(By.xpath(xpath + "/h3"));
        waitTime();
        String firstLink = results.get(0).getAttribute("href");
        clickElement(results.get(0));

        print(firstSearchResultName.getText());
        validate(searchValue,firstSearchResultName.getText(),"Search Title Validation");
        validate(firstLink, driver.getCurrentUrl(),"Link Validation");

    }

    public void validate(String value1, String value2, String Action) {
        if(value1.equals(value2))
        {
            print(Action + ":\n Test Result: El primer link coincide con la URL mostrada");
        } else {
            print(Action + ":\n Test Result: El primer link no coincide con la URL mostrada \n Expected value: " + value1 + "\n Actual value: " + value2);
        }
    }

    public void clickElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        action.click(element).build().perform();
    }

    public void waitTime() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void print(String value) {
        System.out.println(value);
    }

    @After
    public void tearDow() {
        driver.quit();
    }
}
