import JPetStore.UserRegistrationBuild.RegisterUserBuilder;
import JPetStore.UserRegistrationBuild.RegisterUserDirector;
import JPetStore.pages.RegisterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class RegisterPageTest {

    private WebDriver driver;
    private RegisterPage registerPage;
    public String testURL = "https://petstore.octoperf.com/actions/Account.action?newAccountForm=";

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
    }

    @Test
    @Parameters("username")
    public void registrationSuccessTest(String username){

        //Considering that the "username" is unique and is not inside the database.
        // Database resets every 10 minutes.

        String password = "password";

        RegisterUserDirector registerUserDirector = new RegisterUserDirector();
        registerUserDirector.createFullRequirementsTestUser(username,password,registerPage);

        boolean registrationSuccessful = registerPage.isRegistrationSuccessful();

        Assert.assertTrue(registrationSuccessful, "Registration was not successful");
    }

    @Test
    public void registrationWithNonRequiredInput(){
        //Registration form information

        String firstName = "testName";
        String lastName = "testSurname";
        String city = "testCity";
        String favoriteCategoryId = "DOGS";
        boolean enableMyList = true;

        ///////////////////////////////

        RegisterUserBuilder registerUserBuilder = new RegisterUserBuilder();
        registerUserBuilder.setFirstName(firstName)
                .setLastName(lastName)
                .setCity(city)
                .setFavoriteCategoryId(favoriteCategoryId)
                .setEnableList(enableMyList)
                .Build(registerPage);

        boolean registrationSuccessful = registerPage.isRegistrationSuccessful();

        Assert.assertFalse(registrationSuccessful, "Registration was successful");
    }

    @Test
    @Parameters("username")
    public void registrationWithRequiredInput(String username){

        //Considering that the "username" is unique and is not inside the database.
        // Database resets every 10 minutes.

        String password = "password";

        RegisterUserDirector registerUserDirector = new RegisterUserDirector();
        registerUserDirector.createMinimumRequirementsTestUser(username,password,registerPage);

        boolean registrationSuccessful = registerPage.isRegistrationSuccessful();

        Assert.assertTrue(registrationSuccessful, "Registration was not successful");
    }

    @AfterMethod
    public void teardownTest(){
        driver.quit();
    }
}
