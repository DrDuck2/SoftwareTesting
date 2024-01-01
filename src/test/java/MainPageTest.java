import JPetStore.pages.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;
    public String testURL = "https://petstore.octoperf.com/actions/Catalog.action";

    @BeforeMethod
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
        mainPage = new MainPage(driver);
    }

    @Test
    public void badSearchInputTest() {
        String searchInput = "???";

        mainPage.performSearch(searchInput);

        String searchOutput = mainPage.searchResult(); //Should be empty string

        Assert.assertEquals(searchOutput, "", "Search results should not be displayed for bad search input");

    }

    @Test
    public void goodSearchInputTest() {
        String searchInput = "angel";
        searchInput = searchInput.toLowerCase();

        mainPage.performSearch(searchInput);

        String searchOutput = mainPage.searchResult();
        searchOutput = searchOutput.toLowerCase(); //Should be string containing SearchInput word

        Assert.assertTrue(searchOutput.contains(searchInput), "Search results should be displayed for good search input");
    }


    @Test
    public void signInButtonTest(){
        mainPage.clickSignInButton();

        //In between the first and last part is the ID that changes based on the user session.
        String expectedFirstPart = "https://petstore.octoperf.com/actions/Account.action";
        String expectedLastPart = "signonForm=";

        String currentUrl = driver.getCurrentUrl();

        Assert.assertTrue(currentUrl.contains(expectedFirstPart), "URL does not contain the expected first part");
        Assert.assertTrue(currentUrl.contains(expectedLastPart), "URL does not contain the expected last part");
    }

    @AfterMethod
    public void teardownTest(){

        driver.quit();
    }
}
