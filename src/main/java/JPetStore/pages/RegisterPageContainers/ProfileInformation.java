package JPetStore.pages.RegisterPageContainers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfileInformation {
    private final WebDriver driver;
    private final By languagePreferenceSelect = By.name("account.languagePreference");
    private final By favouriteCategorySelect = By.name("account.favouriteCategoryId");
    private final By myListCheckbox = By.name("account.listOption");
    private final By myBannerCheckbox = By.name("account.bannerOption");

    public ProfileInformation(WebDriver driver) {
        this.driver = driver;
    }

    public void selectLanguagePreference(String languagePreference) {
        WebElement languagePreferenceSelectElement = driver.findElement(languagePreferenceSelect);

        // Assuming the languagePreference parameter corresponds to the available options

        languagePreferenceSelectElement.sendKeys(languagePreference);
    }

    public void selectFavouriteCategory(String favouriteCategory) {
        WebElement categorySelect = driver.findElement(favouriteCategorySelect);

        // Assuming the favouriteCategory parameter corresponds to the available options

        categorySelect.sendKeys(favouriteCategory);
    }

    public void enableMyList() {
        driver.findElement(myListCheckbox).click();
    }

    public void enableMyBanner() {
        driver.findElement(myBannerCheckbox).click();
    }
}
