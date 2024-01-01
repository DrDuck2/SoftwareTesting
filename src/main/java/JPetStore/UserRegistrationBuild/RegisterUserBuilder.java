package JPetStore.UserRegistrationBuild;

import JPetStore.pages.RegisterPage;

public class RegisterUserBuilder implements IBuilder{
    private String userId;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String languagePreference;
    private String favoriteCategoryId;
    private boolean enableList;
    private boolean enableBanner;
    public RegisterUserBuilder(){
        this.userId = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.phone = "";
        this.address1 = "";
        this.address2 = "";
        this.city = "";
        this.zip = "";
        this.country = "";
        this.languagePreference = "";
        this.favoriteCategoryId = "";
        this.enableList = false;
        this.enableBanner = false;
    }

    public RegisterUserBuilder setUserId(String userId){
        this.userId = userId;
        return this;
    }

    public RegisterUserBuilder setPassword(String password){
        this.password = password;
        return this;
    }

    public RegisterUserBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public RegisterUserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public RegisterUserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public RegisterUserBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public RegisterUserBuilder setAddress1(String address1) {
        this.address1 = address1;
        return this;
    }

    public RegisterUserBuilder setAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public RegisterUserBuilder setCity(String city) {
        this.city = city;
        return this;
    }

    public RegisterUserBuilder setState(String state) {
        this.state = state;
        return this;
    }

    public RegisterUserBuilder setZip(String zip) {
        this.zip = zip;
        return this;
    }

    public RegisterUserBuilder setCountry(String country) {
        this.country = country;
        return this;
    }

    public RegisterUserBuilder setLanguagePreference(String languagePreference){
        this.languagePreference = languagePreference;
        return this;
    }

    public RegisterUserBuilder setFavoriteCategoryId(String favoriteCategoryId){
        this.favoriteCategoryId = favoriteCategoryId;
        return this;
    }

    public RegisterUserBuilder setEnableList(Boolean enableList){
        this.enableList = enableList;
        return this;
    }

    public RegisterUserBuilder setEnableBanner(Boolean enableBanner){
        this.enableBanner = enableBanner;
        return this;
    }
    @Override
    public void Build(RegisterPage registerPage) {
        registerPage.registerUser(userId,password,firstName,lastName,email,phone,address1,address2,city,state,zip,country,languagePreference,favoriteCategoryId,enableList,enableBanner);
    }
}
