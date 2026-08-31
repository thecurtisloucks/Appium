package com.example.mobile.locators.android;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public final class AndroidHomeLocators {
    public static final By TITLE = AppiumBy.xpath("//*[@text='PRODUCTS']");
    public static final By CART_BUTTON = AppiumBy.accessibilityId("test-Cart");

    private AndroidHomeLocators() {
    }
}

