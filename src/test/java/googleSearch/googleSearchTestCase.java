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

import java.util.HashMap;
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
            String xpath = "//div[@id='rcnt']/descendant::a";
            By seleniumLogo = By.cssSelector("svg[id='selenium_logo']");

            if(driver.findElement(googleLogo).isDisplayed()){
                introduceValue(searchInputElement,searchValue,xpath);
                List<WebElement> results = getElements(By.xpath(xpath));
                List<WebElement> searchResultsNames = getElements(By.xpath(xpath + "/h3"));
                HashMap<Integer,WebElement> machResults = getMatchingResults(searchValue,xpath);
                for (int i=0;i<machResults.size();i++) {
                    print(machResults.size());
                    if(i>=machResults.size()) {
                        driver.get("https://www.google.com/");
                        print(searchInputElement);
                        print(searchValue);
                        print(xpath);
                        introduceValue(searchInputElement,searchValue,xpath);
                    }

                    String href = machResults.get(i).getAttribute("href");
                    machResults.get(i).click();
                    if(href.equals(driver.getCurrentUrl())) {
                        print("Links and Text matches");
                    } else {
                        print("Links and Text do NOT match");
                    }
                }
            }
            else {
                print("Google Search view was not found");
            }
        } catch (Exception e) {
            print("An error occured: " + e.getMessage());
        }

    }

    public HashMap<Integer,WebElement> getMatchingResults(String value,String xpath) {
        HashMap<Integer,WebElement> matchingResults = new HashMap<>();
        List<WebElement> listElements = driver.findElements(By.xpath(xpath + "/h3"));
        for(int i=0;i<listElements.size();i++){
            if(value.equals(listElements.get(i).getText())){
                matchingResults.put(i, driver.findElement(By.xpath(xpath)));
            }
        }
        return matchingResults;
    }

    public void introduceValue (WebElement element,String value,String xpath) {
        element.sendKeys(value, Keys.RETURN);
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.titleContains(value));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
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

    public void print(Object value) {
        System.out.println(value);
    }

    @After
    public void tearDow() {
        driver.quit();
    }
}
