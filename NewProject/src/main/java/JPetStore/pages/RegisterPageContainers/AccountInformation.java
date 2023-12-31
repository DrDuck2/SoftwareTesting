package JPetStore.pages.RegisterPageContainers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountInformation {
    private final WebDriver driver;
    private final By firstNameInput = By.name("account.firstName");
    private final By lastNameInput = By.name("account.lastName");
    private final By emailInput = By.name("account.email");
    private final By phoneInput = By.name("account.phone");
    private final By address1Input = By.name("account.address1");
    private final By address2Input = By.name("account.address2");
    private final By cityInput = By.name("account.city");
    private final By stateInput = By.name("account.state");
    private final By zipInput = By.name("account.zip");
    private final By countryInput = By.name("account.country");

    public AccountInformation(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFirstName(String firstName) {
        driver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPhone(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void enterAddress1(String address1) {
        driver.findElement(address1Input).sendKeys(address1);
    }

    public void enterAddress2(String address2) {
        driver.findElement(address2Input).sendKeys(address2);
    }

    public void enterCity(String city) {
        driver.findElement(cityInput).sendKeys(city);
    }

    public void enterState(String state) {
        driver.findElement(stateInput).sendKeys(state);
    }

    public void enterZip(String zip) {
        driver.findElement(zipInput).sendKeys(zip);
    }

    public void enterCountry(String country) {
        driver.findElement(countryInput).sendKeys(country);
    }
}
