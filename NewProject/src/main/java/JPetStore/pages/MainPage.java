package JPetStore.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MainPage {

    private final WebDriver driver;

    //private final By fishImageLocator = By.xpath("//area[@alt='Fish']");

    private final By fishImageLocator = By.cssSelector("area[alt='Fish']");

    private final By searchInputLocator = By.name("keyword");
    private final By searchButtonLocator = By.name("searchProducts");

    private final By signInButtonLocator = By.xpath("//a[contains(@href, '/actions/Account.action;jsessionid=') and text()='Sign In']");


    private final By searchResultsLocator = By.cssSelector("#Catalog table tbody tr:nth-child(2)");
    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public void clickFishImage() throws InterruptedException {
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.elementToBeClickable(fishImageLocator)).click();
    }
    public void performSearch(String keyword){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.presenceOfElementLocated(searchInputLocator));

        WebElement searchInput = driver.findElement(searchInputLocator);
        searchInput.clear();
        searchInput.sendKeys(keyword);
        driver.findElement(searchButtonLocator).click();
    }

    public String searchResult() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            WebElement secondRow = wait.until(ExpectedConditions.presenceOfElementLocated(searchResultsLocator));
            WebElement lastTdInSecondRow = secondRow.findElement(By.cssSelector("td:last-child"));

            return lastTdInSecondRow.getText();
        } catch (TimeoutException e) {
            return null;
        }
    }

    public void clickSignInButton()
    {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(signInButtonLocator));
        signInButton.click();
    }
}
