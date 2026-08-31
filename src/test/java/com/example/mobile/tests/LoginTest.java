package com.example.mobile.tests;

import com.example.mobile.config.FrameworkConfig;
import com.example.mobile.pages.HomePage;
import com.example.mobile.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public final class LoginTest extends BaseMobileTest {
    @Test
    public void validUserCanLogIn() {
        HomePage home = new LoginPage(driver)
                .loginAs(FrameworkConfig.get("username"), FrameworkConfig.get("password"));

        Assert.assertTrue(home.isLoaded(), "Home screen should be visible after login");
        Assert.assertEquals(home.title(), "PRODUCTS");
    }

    @Test
    public void invalidUserSeesAnError() {
        LoginPage login = new LoginPage(driver)
                .enterUsername("invalid_user")
                .enterPassword("invalid_password")
                .pressLoginExpectingFailure();

        Assert.assertFalse(login.errorMessage().isBlank(), "A login error should be displayed");
    }
}
