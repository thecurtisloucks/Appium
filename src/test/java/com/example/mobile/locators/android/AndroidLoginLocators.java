package com.example.mobile.locators.android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public final class AndroidLoginLocators {
    public static final By USERNAME = AppiumBy.accessibilityId("test-Username");
    public static final By PASSWORD = AppiumBy.accessibilityId("test-Password");
    public static final By LOGIN_BUTTON = AppiumBy.accessibilityId("test-LOGIN");
    public static final By ERROR_MESSAGE = AppiumBy.accessibilityId("test-Error message");

    private AndroidLoginLocators() {
    }
}

