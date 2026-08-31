package com.example.mobile.locators;

import com.example.mobile.config.FrameworkConfig;
import org.openqa.selenium.By;

/** Selects the correct locator set once, leaving page behavior platform-agnostic. */
public final class PlatformLocators {
    private PlatformLocators() {
    }

    public static By current(By android, By ios) {
        return FrameworkConfig.isAndroid() ? android : ios;
    }
}

