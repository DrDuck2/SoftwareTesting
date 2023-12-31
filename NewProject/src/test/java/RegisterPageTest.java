import JPetStore.RegisterUserBuilder;
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
    public void registrationSuccessProcessTest(String username){
        //Registration form information

        //Considering that the "username" is unique and is not inside the database.
        // Database resets every 10 minutes.

        String password = "testpassword";
        String firstName = "testName";
        String lastName = "testSurname";
        String email = "testEmail@gmail.com";
        String phone = "123456789";
        String address1 = "Test street 1";
        String address2 = "Test street 2";
        String city = "testCity";
        String state = "OS";
        String zip = "12345";
        String country = "Croatia";
        String languagePreference = "english";
        String favoriteCategoryId = "DOGS";
        boolean enableMyList = false;
        boolean enableMyBanner = false;

        ///////////////////////////////

        registerPage.registerUser(username,password,firstName,lastName,email,phone,address1,address2,city,state,zip,country,languagePreference,favoriteCategoryId,enableMyList,enableMyBanner);

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
        registerUserBuilder.firstName(firstName);
        registerUserBuilder.lastName(lastName);
        registerUserBuilder.city(city);
        registerUserBuilder.favoriteCategoryId(favoriteCategoryId);
        registerUserBuilder.enableList(enableMyList);
        registerUserBuilder.build(registerPage);

        boolean registrationSuccessful = registerPage.isRegistrationSuccessful();

        Assert.assertFalse(registrationSuccessful, "Registration was successful");
    }

    @Test
    @Parameters("username")
    public void registrationWithRequiredInput(String username){
        //Registration form information

        //Considering that the "username" is unique and is not inside the database.
        // Database resets every 10 minutes.
        String password = "testpassword";
        String firstName = "testName";
        String lastName = "testSurname";
        String email = "testEmail@gmail.com";
        String phone = "123456789";
        String address1 = "Test street 1";
        String city = "testCity";
        String state = "OS";
        String zip = "12345";
        String country = "Croatia";

        ///////////////////////////////

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

        boolean registrationSuccessful = registerPage.isRegistrationSuccessful();

        Assert.assertTrue(registrationSuccessful, "Registration was not successful");
    }

    @AfterMethod
    public void teardownTest(){
        driver.quit();
    }
}
