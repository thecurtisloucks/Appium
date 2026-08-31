package com.example.mobile.pages;

import com.example.mobile.locators.PlatformLocators;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import static com.example.mobile.locators.android.AndroidHomeLocators.CART_BUTTON;
import static com.example.mobile.locators.android.AndroidHomeLocators.TITLE;

public final class HomePage extends BasePage {
    private final By title = PlatformLocators.current(TITLE,
            com.example.mobile.locators.ios.IosHomeLocators.TITLE);
    private final By cartButton = PlatformLocators.current(CART_BUTTON,
            com.example.mobile.locators.ios.IosHomeLocators.CART_BUTTON);

    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isVisible(title);
    }

    public String title() {
        return text(title);
    }

    public void openCart() {
        tap(cartButton);
    }
}

