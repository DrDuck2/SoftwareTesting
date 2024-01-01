package JPetStore.UserRegistrationBuild;

import JPetStore.pages.RegisterPage;

public class RegisterUserDirector {

    RegisterUserBuilder registerUserBuilder;

    public RegisterUserDirector(){
        this.registerUserBuilder = new RegisterUserBuilder();
    }
    public void createMinimumRequirementsTestUser(String userId, String password, RegisterPage registerPage){
        registerUserBuilder.setUserId(userId)
                .setPassword(password)
                .setFirstName("TestName")
                .setLastName("TestLastName")
                .setEmail("TestMail@gmail.com")
                .setPhone("123456789")
                .setAddress1("Test address")
                .setCity("Test")
                .setState("TS")
                .setZip("12345")
                .setCountry("Test")
                .Build(registerPage);
    }

    public void createFullRequirementsTestUser(String userId, String password, RegisterPage registerPage){
        registerUserBuilder.setUserId(userId)
                .setPassword(password)
                .setFirstName("TestName")
                .setLastName("TestLastName")
                .setEmail("TestMail@gmail.com")
                .setPhone("123456789")
                .setAddress1("Test address")
                .setAddress2("Test address 2")
                .setCity("Test")
                .setState("TS")
                .setZip("12345")
                .setCountry("Test")
                .setLanguagePreference("English")
                .setFavoriteCategoryId("DOGS")
                .setEnableList(true)
                .setEnableBanner(true)
                .Build(registerPage);
    }






}
