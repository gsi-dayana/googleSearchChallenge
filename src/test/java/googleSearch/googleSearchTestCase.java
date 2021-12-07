package googleSearch;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class googleSearchTestCase {

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
            WebElement searchInputElement = getElement(By.name("q"));
            searchInputElement.clear();
            String searchValue = "Selenium";
            By googleLogo = By.id("hplogo");
            By seleniumLogo = By.cssSelector("svg[id='selenium_logo']");
            WebDriverWait wait = new WebDriverWait(driver,10);

            if(driver.findElement(googleLogo).isDisplayed()){
                searchInputElement.sendKeys(searchValue, Keys.RETURN);
                wait.until(ExpectedConditions.titleContains(searchValue));
                String xpath = "//div[@id='rcnt']/descendant::a";
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
                List<WebElement> results = getElements(By.xpath(xpath));
                List<WebElement> searchResultsNames = getElements(By.xpath(xpath + "/h3"));
                String firstLink = results.get(0).getAttribute("href");
                String firstResultTitle = searchResultsNames.get(0).getText();
                clickElement(results.get(0));
                validate(searchValue, firstResultTitle, "Search Title Validation");
                validate(firstLink, driver.getCurrentUrl(), "Search Link Validation");
                wait.until(ExpectedConditions.visibilityOf(getElement(seleniumLogo)));
            }
            else {
                print("Google Search view was not found");
            }
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

    public WebElement getElement(By by){
        return driver.findElement(by);
    }

    public List<WebElement> getElements(By by){
        return driver.findElements(by);
    }

    public void print(String value) {
        System.out.println(value);
    }

    @After
    public void tearDow() {
        driver.quit();
    }
}
