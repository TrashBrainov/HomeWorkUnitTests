package tests;

import config.WebDriverConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseWebTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = WebDriverConfig.getDriver();
    }

    @AfterEach
    public void tearDown() {
        WebDriverConfig.tearDown();
    }
}