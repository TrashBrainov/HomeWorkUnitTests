package config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.URL;
import java.time.Duration;

public class AppiumConfig {
    private static AndroidDriver driver;
    private static AppiumDriverLocalService service;

    public static AndroidDriver getDriver() {
        if (driver == null) {
            setupDriver();
        }
        return driver;
    }

    public static void setupDriver() {
        // Запуск Appium сервера
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .build();
        service.start();

        // Настройка опций для Android драйвера
        UiAutomator2Options options = new UiAutomator2Options()
                .setApp(new File("src/test/resources/TheApp.apk").getAbsolutePath())
                .setDeviceName("Android Emulator")
                .setAutomationName("UiAutomator2")
                .setPlatformName("Android")
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(Duration.ofSeconds(60));

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (Exception e) {
            e.printStackTrace();
            if (service != null) {
                service.stop();
            }
        }
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        if (service != null) {
            service.stop();
            service = null;
        }
    }
}