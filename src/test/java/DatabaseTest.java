import JPetStore.UserRegistrationBuild.RegisterUserDirector;
import JPetStore.pages.RegisterPage;
import JPetStore.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.*;


public class DatabaseTest {

    private WebDriver driver;
    private RegisterPage registerPage;
    private LoginPage loginPage;

    private final String testURL = "https://petstore.octoperf.com/actions/Account.action?signonForm=";

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
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @Parameters({"username","password"})
    public void loginRegistrationDatabaseSequenceTest(String username, String password){

        // TEST 1 -> Trying to log in with wrong credentials //

        loginPage.login(username, password);

        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login was successful with false information.");

        // TEST 2 -> Registering with the wrong credentials //

        loginPage.clickRegister();


        RegisterUserDirector registerUserDirector = new RegisterUserDirector();
        registerUserDirector.createMinimumRequirementsTestUser(username,password,registerPage);


        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration was not successful");

        //Return to original login page
        driver.navigate().to(testURL);

        // TEST 3 -> Log in with the previous credentials //
        loginPage.login(username, password);

        Assert.assertFalse(loginPage.isLoginButtonDisplayed(), "Login has failed");
    }

    @AfterMethod
    public void teardownTest(){
        driver.quit();
    }
}
