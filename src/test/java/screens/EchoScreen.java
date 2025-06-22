package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class EchoScreen extends BaseScreen {

    @AndroidFindBy(accessibility = "messageInput")
    private WebElement messageInput;

    @AndroidFindBy(accessibility = "messageSaveBtn")
    private WebElement saveButton;

    @AndroidFindBy(id = "savedMessage")
    private WebElement savedMessage;

    public EchoScreen(AndroidDriver driver) {
        super(driver);
    }

    public EchoScreen enterMessage(String message) {
        enterText(messageInput, message);
        return this;
    }

    public EchoScreen tapSaveButton() {
        clickElement(saveButton);
        return this;
    }

    public boolean isSavedMessageDisplayed() {
        try {
            return waitForElement(savedMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getSavedMessage() {
        return getText(savedMessage);
    }
}