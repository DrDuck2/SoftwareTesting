import JPetStore.pages.LoginPage;
import JPetStore.pages.RegisterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    public String testURL = "https://petstore.octoperf.com/actions/Account.action?signonForm=";

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
        loginPage = new LoginPage(driver);
    }

    @Test
    @Parameters({"username", "password"})
    public void loginSuccessTest(String username, String password){

        loginPage.login(username,password);

        Assert.assertFalse(loginPage.isLoginButtonDisplayed(), "Login has failed");
    }

    @Test
    @Parameters({"username","password"})
    public void loginFailTest(String username, String password){

        loginPage.login(username,password);

        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login was successful with false information.");
    }

    @AfterMethod
    public void teardownTest(){
        driver.quit();
    }
}
