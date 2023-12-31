import JPetStore.RegisterUserBuilder;
import JPetStore.pages.RegisterPage;
import JPetStore.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @Parameters({"username","password"})
    private void loginRegistrationDatabaseSequenceTest(String username, String password){
        // TEST 1 //


        loginPage.login(username, password);

        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login was successful with false information.");

        // TEST 2 //

        loginPage.clickRegister();

        //Register information

        String firstName = "testName";
        String lastName = "testSurname";
        String email = "testEmail@gmail.com";
        String phone = "123456789";
        String address1 = "Test street 1";
        String city = "testCity";
        String state = "OS";
        String zip = "12345";
        String country = "Croatia";

        ///////////////////////////////////

        RegisterUserBuilder registerUserBuilder = new RegisterUserBuilder();
        registerUserBuilder.userId(username);
        registerUserBuilder.password(password);
        registerUserBuilder.firstName(firstName);
        registerUserBuilder.lastName(lastName);
        registerUserBuilder.email(email);
        registerUserBuilder.phone(phone);
        registerUserBuilder.address1(address1);
        registerUserBuilder.city(city);
        registerUserBuilder.state(state);
        registerUserBuilder.zip(zip);
        registerUserBuilder.country(country);

        registerUserBuilder.build(registerPage);

        Assert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration was not successful");

        //Have to return to login page

        driver.navigate().to(testURL);

        // TEST 3 //
        loginPage.login(username, password);

        Assert.assertFalse(loginPage.isLoginButtonDisplayed(), "Login has failed");
    }

    @AfterMethod
    public void teardownTest(){
        driver.quit();
    }

}
