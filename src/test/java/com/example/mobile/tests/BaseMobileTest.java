package com.example.mobile.tests;

import com.example.mobile.config.DriverFactory;
import io.appium.java_client.AppiumDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseMobileTest {
    protected AppiumDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void startSession() {
        if (!Boolean.parseBoolean(System.getProperty("runMobileTests", "false"))) {
            throw new SkipException("Set -DrunMobileTests=true to run tests against a device");
        }
        driver = DriverFactory.createDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void stopSession() {
        if (driver != null) {
            driver.quit();
        }
    }
}
