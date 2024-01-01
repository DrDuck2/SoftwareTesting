package JPetStore.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class MainPage {

    private final WebDriver driver;
    private final By searchInputLocator = By.name("keyword");
    private final By searchButtonLocator = By.name("searchProducts");
    private final By signInButtonLocator = By.xpath("//a[contains(@href, '/actions/Account.action;jsessionid=') and text()='Sign In']");

    //FIRST CHILD represents HEADER of the container.
    // SECOND CHILD represents search result if it HAS a result, if not SECOND CHILD is FOOTER
    private final By searchResultsLocator = By.cssSelector("#Catalog table tbody tr:nth-child(2)");

    public MainPage(WebDriver driver){
        this.driver = driver;
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

            //Last child of the container element is elements NAME
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
