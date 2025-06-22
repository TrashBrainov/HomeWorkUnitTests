package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import patterns.WebDriverFactory;

import java.time.Duration;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class BasePage {

    WebDriver driver;

    public BasePage(String browser) {
        driver = WebDriverFactory.createWebDriver(browser);
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void visit(String url) {
        driver.get(url);
    }

    public void quit() {
        if (driver != null) {
            driver.quit();
        }


    }
}
