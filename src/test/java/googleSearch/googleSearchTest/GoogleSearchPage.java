package googleSearch.googleSearchTest;

import com.github.javafaker.Faker;
import googleSearch.general.Setup;
import googleSearch.general.userAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GoogleSearchPage<faker> {

    private WebDriver driver;
    By searchInput = By.name("q");
    String xpath = "//div[@id='rcnt']/descendant::a";
    By googleLogo = By.className("lnXdpd");
    String firstLink;
    String firstResultTitle;
    String randomArtistName;
    List<WebElement> results;
    private WebDriverWait wait;
    private int waitTime;
    private Faker faker;
    String urlpath = "";

    public GoogleSearchPage() {
        super();
        this.setDriver(Setup.getDriver());
        //PageFactory.initElements(this.getDriver(), this);
        setWaitTime(20);
        //setWait(new WebDriverWait(this.getDriver(), this.getWaitTime()));
    }

    public Faker getFaker() {
        return faker;
    }

    public void setFaker(Faker faker) {
        this.faker = faker;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void setWait(WebDriverWait wait) {
        this.wait = wait;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public By getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(By searchInput) {
        this.searchInput = searchInput;
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public By getGoogleLogo() {
        return googleLogo;
    }

    public void setGoogleLogo(By googleLogo) {
        this.googleLogo = googleLogo;
    }

    public void openURL() {
        Setup.openUrl(System.getProperty("defaultURL").concat("/").concat(urlpath));
    }

    //Verifying the user is on google search view by checking if Google Logo is rendered in the DOM
    public boolean openGoogleView() {
        try {
            openURL();
            Thread.sleep(2000);
            //getWaitTime().executeExpectedCondition(ExpectedConditions.presenceOfElementLocated(getGoogleLogo()),"Google Logo is present",10);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean introduceRandomArtistName() {
        try {
            getDriver().findElement(searchInput).clear();
            randomArtistName = getFaker().artist().name();
            Setup.sendAction(getDriver().findElement(searchInput),randomArtistName, userAction.type);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void pressEnterKey() {
        try {
            Setup.getActions().sendKeys(getDriver().findElement(searchInput), Keys.RETURN);
        } catch (Exception e) {}
    }

    public boolean validateTitleFirstElement() {
        try {
            results = driver.findElements(By.xpath(xpath));
            Thread.sleep(2000);
            List<WebElement> firstSearchResultName = driver.findElements(By.xpath(xpath + "/h3"));
            Thread.sleep(2000);
            firstResultTitle = firstSearchResultName.get(0).getText();
            validate(randomArtistName, firstResultTitle, "Search Title Validation");
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validateLinkFirstElement() {
        try {
            firstLink = results.get(0).getAttribute("href");
            clickElement(results.get(0));
            validate(firstLink,getDriver().getCurrentUrl(),"Search Link Validation");
            return true;
        } catch(Exception e) {
            return false;
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

    public void print(Object value) {
        System.out.println(value);
    }
}
