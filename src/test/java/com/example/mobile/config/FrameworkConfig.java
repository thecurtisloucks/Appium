package com.example.mobile.config;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Locale;
import java.util.Properties;

/** Loads framework settings with system property > environment > file precedence. */
public final class FrameworkConfig {
    private static final Properties FILE_VALUES = loadDefaults();

    private FrameworkConfig() {
    }

    public static String get(String key) {
        String systemValue = System.getProperty(key);
        if (hasText(systemValue)) {
            return systemValue.trim();
        }

        String environmentKey = "APPIUM_" + key.toUpperCase(Locale.ROOT).replace('.', '_');
        String environmentValue = System.getenv(environmentKey);
        if (hasText(environmentValue)) {
            return environmentValue.trim();
        }
        return FILE_VALUES.getProperty(key, "").trim();
    }

    public static Duration waitTimeout() {
        return Duration.ofSeconds(Long.parseLong(get("wait.seconds")));
    }

    public static boolean isAndroid() {
        return "android".equalsIgnoreCase(get("platform.name"));
    }

    private static boolean hasText(String value) {
        return value != null && !value.isBlank();
    }

    private static Properties loadDefaults() {
        Properties properties = new Properties();
        try (InputStream stream = FrameworkConfig.class.getClassLoader()
                .getResourceAsStream("config/default.properties")) {
            if (stream == null) {
                throw new IllegalStateException("Missing config/default.properties");
            }
            properties.load(stream);
            return properties;
        } catch (IOException error) {
            throw new IllegalStateException("Unable to load framework configuration", error);
        }
    }
}

