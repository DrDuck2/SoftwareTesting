package JPetStore.pages.RegisterPageContainers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserInformation {
    private final WebDriver driver;
    private final By userIdInput = By.name("username");
    private final By passwordInput = By.name("password");
    private final By repeatPasswordInput = By.name("repeatedPassword");
    public UserInformation(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUserId(String userId) {
        driver.findElement(userIdInput).sendKeys(userId);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void enterRepeatPassword(String repeatPassword) {
        driver.findElement(repeatPasswordInput).sendKeys(repeatPassword);
    }
}
