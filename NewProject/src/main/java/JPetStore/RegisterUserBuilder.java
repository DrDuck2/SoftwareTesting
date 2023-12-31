package JPetStore;

import JPetStore.pages.RegisterPage;

public class RegisterUserBuilder {
    private String userId = "";
    private String password = "";
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String phone = "";
    private String address1 = "";
    private String address2 = "";
    private String city = "";
    private String state = "";
    private String zip = "";
    private String country = "";
    private String languagePreference = "english";
    private String favoriteCategoryId = "DOGS";
    private boolean enableList = false;
    private boolean enableBanner = false;

    public RegisterUserBuilder userId(String userId){
        this.userId = userId;
        return this;
    }
    public RegisterUserBuilder password(String password){
        this.password = password;
        return this;
    }
    public RegisterUserBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public RegisterUserBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public RegisterUserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public RegisterUserBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public RegisterUserBuilder address1(String address1) {
        this.address1 = address1;
        return this;
    }

    public RegisterUserBuilder address2(String address2) {
        this.address2 = address2;
        return this;
    }

    public RegisterUserBuilder city(String city) {
        this.city = city;
        return this;
    }

    public RegisterUserBuilder state(String state) {
        this.state = state;
        return this;
    }

    public RegisterUserBuilder zip(String zip) {
        this.zip = zip;
        return this;
    }

    public RegisterUserBuilder country(String country) {
        this.country = country;
        return this;
    }

    public RegisterUserBuilder languagePreference(String languagePreference) {
        this.languagePreference = languagePreference;
        return this;
    }

    public RegisterUserBuilder favoriteCategoryId(String favoriteCategoryId) {
        this.favoriteCategoryId = favoriteCategoryId;
        return this;
    }

    public RegisterUserBuilder enableList(boolean enableList) {
        this.enableList = enableList;
        return this;
    }

    public RegisterUserBuilder enableBanner(boolean enableBanner) {
        this.enableBanner = enableBanner;
        return this;
    }
    public void build(RegisterPage registerPage) {
        registerPage.registerUser(userId, password, firstName, lastName, email, phone, address1, address2,
                city, state, zip, country, languagePreference, favoriteCategoryId,
                enableList, enableBanner);
    }
}
