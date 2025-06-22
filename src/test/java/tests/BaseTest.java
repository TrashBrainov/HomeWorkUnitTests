package tests;

import config.AppiumConfig;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import screens.HomeScreen;

public abstract class BaseTest {
    protected AndroidDriver driver;
    protected HomeScreen homeScreen;

    @BeforeEach
    public void setUp() {
        driver = AppiumConfig.getDriver();
        homeScreen = new HomeScreen(driver);
    }

    @AfterEach
    public void tearDown() {
        AppiumConfig.tearDown();
    }
}