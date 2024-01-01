package JPetStore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstPage {
    private final WebDriver driver;
    private final By welcomeMessageLocator = By.tagName("h2");
    private final By storeLinkLocator = By.xpath("//a[text()='Enter the Store']");

    public FirstPage(WebDriver driver){
        this.driver = driver;
    }
    public WebElement getWelcomeMessage(){
        return driver.findElement(welcomeMessageLocator);
    }
    public WebElement getStoreLink(){
        return driver.findElement(storeLinkLocator);
    }
    public void clickEnterStoreLink(){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.presenceOfElementLocated(storeLinkLocator));

        getStoreLink().click();
    }
}
