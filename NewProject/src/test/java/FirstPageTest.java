import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import JPetStore.pages.FirstPage;

public class FirstPageTest {
    private WebDriver driver;
    private FirstPage firstPage;
    public String testURL = "https://petstore.octoperf.com/";

    @BeforeClass
    @Parameters("browser")
    public void setupTest(String browser) {
        System.out.println("Setting up WebDriver for browser: " + browser);
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            driver = new FirefoxDriver(options);
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.navigate().to(testURL);
        firstPage = new FirstPage(driver);
    }
    @Test
    public void enterStoreLinkTest() {
        System.out.println("Executing enterStoreLinkTest");
        firstPage.clickEnterStoreLink();

        String expectedUrl = "https://petstore.octoperf.com/actions/Catalog.action";

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl, "URL after clicking 'Enter the Store' link is incorrect" );
    }

    @Test(dependsOnMethods = "enterStoreLinkTest")
    public void returnFirstPage() {
        driver.navigate().back();

        Assert.assertTrue(firstPage.getWelcomeMessage().isDisplayed(),"Not on the home page after navigating back");
    }

    @AfterClass
    public void teardownTest() {
        driver.quit();
    }
}
