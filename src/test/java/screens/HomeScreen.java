package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomeScreen extends BaseScreen {

    @AndroidFindBy(accessibility = "Login Screen")
    private WebElement loginScreenButton;

    @AndroidFindBy(accessibility = "Echo Box")
    private WebElement echoBoxButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='The App']")
    private WebElement appTitle;

    @AndroidFindBy(className = "android.widget.TextView")
    private List<WebElement> menuItems;

    public HomeScreen(AndroidDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        return waitForElement(appTitle).isDisplayed();
    }

    public LoginScreen navigateToLoginScreen() {
        clickElement(loginScreenButton);
        return new LoginScreen(driver);
    }

    public EchoScreen navigateToEchoBox() {
        clickElement(echoBoxButton);
        return new EchoScreen(driver);
    }

    public int getNumberOfMenuItems() {
        return menuItems.size();
    }
}