package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePageAppium {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePageAppium(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected WebElement waitForElement(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void clickElement(WebElement element) {
        waitForElement(element).click();
    }

    protected void enterText(WebElement element, String text) {
        waitForElement(element).sendKeys(text);
    }

    protected String getText(WebElement element) {
        return waitForElement(element).getText();
    }
}