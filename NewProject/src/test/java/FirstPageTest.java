import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if (browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else{
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.navigate().to(testURL);
        firstPage = new FirstPage(driver);
    }
    @Test
    public void enterStoreLinkTest() throws InterruptedException {
        firstPage.clickEnterStoreLink();

        String expectedUrl = "https://petstore.octoperf.com/actions/Catalog.action";

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl, "URL after clicking 'Enter the Store' link is incorrect" );
    }

    @Test(dependsOnMethods = "enterStoreLinkTest")
    public void returnFirstPage() throws InterruptedException{
        driver.navigate().back();

        Assert.assertTrue(firstPage.getWelcomeMessage().isDisplayed(),"Not on the home page after navigating back");
    }

    @AfterClass
    public void teardownTest() {
        driver.quit();
    }
}
