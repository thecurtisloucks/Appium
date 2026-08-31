package com.example.mobile.config;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;
import java.net.URI;

/** Creates a driver from external configuration; no device details are hard-coded in tests. */
public final class DriverFactory {
    private DriverFactory() {
    }

    public static AppiumDriver createDriver() {
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("platformName", required("platform.name"));
        capabilities.setCapability("appium:automationName", required("automation.name"));
        capabilities.setCapability("appium:deviceName", required("device.name"));
        capabilities.setCapability("appium:noReset", Boolean.parseBoolean(FrameworkConfig.get("no.reset")));

        setWhenPresent(capabilities, "appium:app", "app.path");
        setWhenPresent(capabilities, "appium:appPackage", "app.package");
        setWhenPresent(capabilities, "appium:appActivity", "app.activity");
        setWhenPresent(capabilities, "appium:bundleId", "bundle.id");

        try {
            return new AppiumDriver(URI.create(required("server.url")).toURL(), capabilities);
        } catch (MalformedURLException | IllegalArgumentException error) {
            throw new IllegalArgumentException("Invalid server.url: " + FrameworkConfig.get("server.url"), error);
        }
    }

    private static void setWhenPresent(MutableCapabilities capabilities, String capability, String key) {
        String value = FrameworkConfig.get(key);
        if (!value.isBlank()) {
            capabilities.setCapability(capability, value);
        }
    }

    private static String required(String key) {
        String value = FrameworkConfig.get(key);
        if (value.isBlank()) {
            throw new IllegalArgumentException("Required configuration is missing: " + key);
        }
        return value;
    }
}

