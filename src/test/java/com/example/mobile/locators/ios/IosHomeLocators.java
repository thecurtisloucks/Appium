package com.example.mobile.locators.ios;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public final class IosHomeLocators {
    public static final By TITLE = AppiumBy.iOSNsPredicateString("label == 'PRODUCTS'");
    public static final By CART_BUTTON = AppiumBy.accessibilityId("test-Cart");

    private IosHomeLocators() {
    }
}

