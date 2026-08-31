package com.example.mobile.pages;

import com.example.mobile.locators.PlatformLocators;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import static com.example.mobile.locators.android.AndroidLoginLocators.*;

public final class LoginPage extends BasePage {
    private final By username = PlatformLocators.current(USERNAME,
            com.example.mobile.locators.ios.IosLoginLocators.USERNAME);
    private final By password = PlatformLocators.current(PASSWORD,
            com.example.mobile.locators.ios.IosLoginLocators.PASSWORD);
    private final By loginButton = PlatformLocators.current(LOGIN_BUTTON,
            com.example.mobile.locators.ios.IosLoginLocators.LOGIN_BUTTON);
    private final By errorMessage = PlatformLocators.current(ERROR_MESSAGE,
            com.example.mobile.locators.ios.IosLoginLocators.ERROR_MESSAGE);

    public LoginPage(AppiumDriver driver) {
        super(driver);
    }

    public LoginPage enterUsername(String value) {
        type(username, value);
        return this;
    }

    public LoginPage enterPassword(String value) {
        type(password, value);
        return this;
    }

    public HomePage pressLoginButton() {
        tap(loginButton);
        return new HomePage(driver);
    }

    public LoginPage pressLoginExpectingFailure() {
        tap(loginButton);
        return this;
    }

    public HomePage loginAs(String username, String password) {
        return enterUsername(username).enterPassword(password).pressLoginButton();
    }

    public String errorMessage() {
        return text(errorMessage);
    }
}

