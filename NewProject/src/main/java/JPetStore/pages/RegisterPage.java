package JPetStore.pages;

import JPetStore.pages.RegisterPageContainers.AccountInformation;
import JPetStore.pages.RegisterPageContainers.ProfileInformation;
import JPetStore.pages.RegisterPageContainers.UserInformation;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class RegisterPage {
    private final UserInformation userInformation;
    private final AccountInformation accountInformation;
    private final ProfileInformation profileInformation;
    private final By saveAccountButton = By.name("newAccount");
    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.userInformation = new UserInformation(driver);
        this.accountInformation = new AccountInformation(driver);
        this.profileInformation = new ProfileInformation(driver);
    }
    public void registerUser(String userId, String password, String firstName, String lastName,
                             String email, String phone, String address1, String address2, String city, String state,
                             String zip, String country, String languagePreference, String favoriteCategoryId,
                             boolean enableList, boolean enableBanner) {

        enterUserDetails(userId, password);
        enterPersonalDetails(firstName, lastName, email, phone, address1, address2, city, state, zip, country);
        enterProfileDetails(languagePreference, favoriteCategoryId);
        enableOptions(enableList, enableBanner);

        clickSaveAccount();
    }

    private void enterUserDetails(String userId, String password) {
        userInformation.enterUserId(userId);
        userInformation.enterPassword(password);
        userInformation.enterRepeatPassword(password);
    }

    private void enterPersonalDetails(String firstName, String lastName, String email, String phone,
                                      String address1, String address2, String city, String state, String zip, String country) {
        accountInformation.enterFirstName(firstName);
        accountInformation.enterLastName(lastName);
        accountInformation.enterEmail(email);
        accountInformation.enterPhone(phone);
        accountInformation.enterAddress1(address1);
        accountInformation.enterAddress2(address2);
        accountInformation.enterCity(city);
        accountInformation.enterState(state);
        accountInformation.enterZip(zip);
        accountInformation.enterCountry(country);
    }

    private void enterProfileDetails(String languagePreference, String favoriteCategoryId) {
        profileInformation.selectLanguagePreference(languagePreference);
        profileInformation.selectFavouriteCategory(favoriteCategoryId);
    }

    private void enableOptions(boolean enableList, boolean enableBanner) {
        if (enableList) {
            profileInformation.enableMyList();
        }
        if (enableBanner) {
            profileInformation.enableMyBanner();
        }
    }
    private void clickSaveAccount() {
        driver.findElement(saveAccountButton).click();
    }
    public boolean isRegistrationSuccessful () {

        return !(isErrorPageDisplayed() || isSaveAccountPresent());
    }

    private boolean isErrorPageDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body h1")));
            return driver.findElement(By.cssSelector("body h1")).getText().equals("HTTP Status 500 – Internal Server Error");
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    private boolean isSaveAccountPresent(){
        try{
            WebDriverWait wait = new WebDriverWait(driver, 2);
            wait.until(ExpectedConditions.presenceOfElementLocated(saveAccountButton));
            return true;
        }catch(TimeoutException | NoSuchElementException e){
            return false;
        }
    }
}
