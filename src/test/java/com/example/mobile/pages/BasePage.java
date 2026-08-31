package com.example.mobile.pages;

import com.example.mobile.config.FrameworkConfig;
import com.example.mobile.utils.Waits;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

/** Common interaction primitives used by all screen objects. */
public abstract class BasePage {
    protected final AppiumDriver driver;
    protected final Waits waits;

    protected BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver, FrameworkConfig.waitTimeout());
    }

    protected void type(By locator, String value) {
        var element = waits.visible(locator);
        element.clear();
        element.sendKeys(value);
    }

    protected void tap(By locator) {
        waits.clickable(locator).click();
    }

    protected String text(By locator) {
        return waits.visible(locator).getText();
    }

    protected boolean isVisible(By locator) {
        try {
            waits.visible(locator);
            return true;
        } catch (RuntimeException ignored) {
            return false;
        }
    }
}

