# Appium Page Object Framework

A small, scalable Java 25 test project for Android and iOS. It uses Appium,
TestNG, Maven, explicit waits, external configuration, and page objects that keep
test intent separate from UI details. The included locators target the Sauce Labs
sample application and are examples to replace with locators from your app.

## Project structure

```text
.
├── pom.xml                              Dependency and build configuration
├── testng.xml                          Suite definition
└── src/test
    ├── java/com/example/mobile
    │   ├── config                      Configuration loading and driver creation
    │   ├── locators
    │   │   ├── android                 Android-specific selectors
    │   │   └── ios                     iOS-specific selectors
    │   ├── pages                       One page object per screen/component
    │   ├── tests                       Test cases and session lifecycle
    │   └── utils                       Shared waits and future data helpers
    └── resources/config                Safe default device/test settings
```

The `pages` package exposes business-readable actions such as `loginAs` and
`pressLoginButton`. It does not expose selectors to tests. `locators` owns
platform differences, `config` owns environment setup, and `utils` owns shared
technical behavior. Tests therefore describe outcomes instead of WebDriver
mechanics.

## Prerequisites

- Java 25 and Maven 3.9+
- Node.js and Appium 3
- The Appium driver for the chosen platform (`uiautomator2` or `xcuitest`)
- A running emulator/simulator or connected device
- An installed app, or an `.apk`/`.app`/`.ipa` supplied through `app.path`

Example server setup:

```bash
npm install -g appium
appium driver install uiautomator2   # Android
# appium driver install xcuitest     # iOS, macOS only
appium
```

## Configure and run

Defaults live in `src/test/resources/config/default.properties`. Override them
without editing committed files by using Maven properties:

```bash
mvn test -DrunMobileTests=true \
  -Dplatform.name=Android \
  -Dautomation.name=UiAutomator2 \
  -Ddevice.name="Pixel_8_API_35" \
  -Dapp.path=/absolute/path/to/app.apk
```

Each property also has an environment-variable form prefixed with `APPIUM_`;
periods become underscores. For example, `platform.name` becomes
`APPIUM_PLATFORM_NAME`. Precedence is system property, environment variable,
then the defaults file.

Useful settings:

| Property | Purpose |
| --- | --- |
| `server.url` | Appium server URL; defaults to `http://127.0.0.1:4723` |
| `platform.name` | `Android` or `iOS` |
| `automation.name` | Usually `UiAutomator2` or `XCUITest` |
| `device.name` | Emulator, simulator, or device name |
| `app.path` | Absolute application binary path |
| `app.package`, `app.activity` | Android installed-app identifiers |
| `bundle.id` | iOS installed-app identifier |
| `wait.seconds` | Explicit wait timeout |
| `username`, `password` | Test account values |

Run `mvn test` without `runMobileTests` for a compilation/build check. The
device tests will be reported as skipped rather than attempting an accidental
connection.

## Add a screen

1. Add Android selectors under `locators/android` and iOS selectors under
   `locators/ios`. Prefer accessibility IDs, then stable IDs; use XPath only as a
   last resort.
2. Add one class under `pages`, extend `BasePage`, and select each platform
   locator with `PlatformLocators.current(android, ios)`.
3. Expose user actions and screen state, not raw elements. Return the resulting
   page object when navigation occurs.
4. Add a focused test under `tests` and register a new test class in `testng.xml`.
5. Put reusable timing, gestures, data loading, or device behavior in `utils`
   rather than duplicating it across screens.

## Platform considerations

- Android and iOS accessibility labels are not always identical. Keep their
  selectors separate even when the initial values match.
- `XCUITest` requires macOS/Xcode and may require WebDriverAgent signing on a
  physical iOS device. `UiAutomator2` requires a compatible Android SDK/JDK.
- Capabilities must use the `appium:` vendor prefix; `DriverFactory` already does
  this for Appium-specific values.
- iOS predicate/class-chain selectors and Android UIAutomator selectors are fast
  platform-specific alternatives. Avoid brittle hierarchy-based XPath where a
  stable accessibility ID can be added by the application team.
- Parallel execution needs a separate driver per thread plus unique ports such
  as Android `systemPort` or iOS `wdaLocalPort`. The starter suite is deliberately
  serial until that device-allocation policy is introduced.

## Why this scales

Changes to UI selectors affect locator classes instead of every test. Shared
interactions live in page objects, shared infrastructure lives in config/utilities,
and tests remain short enough to review as product behavior. Platform folders
make ownership and code review explicit, while external configuration lets the
same suite run locally and in CI without committed machine-specific values.
This separation also reduces merge conflicts: team members can usually add a
screen, its locators, and its tests without editing unrelated files.
