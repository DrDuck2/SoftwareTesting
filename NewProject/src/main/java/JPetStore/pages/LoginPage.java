package JPetStore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

public class LoginPage {
    private final WebDriver driver;
    private final By usernameInput = By.name("username");
    private final By passwordInput = By.name("password");
    private final By loginButton = By.name("signon");
    private final By registerButton = By.linkText("Register Now!");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(passwordInput).clear(); //When accessing the login page, "trash value" password is present
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void clickRegister() {driver.findElement(registerButton).click();}

    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLogin();
    }
    public boolean isLoginButtonDisplayed() {
        try{
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));
            return true;
        }catch (TimeoutException | NoSuchElementException e){
            return false;
        }
    }
}
