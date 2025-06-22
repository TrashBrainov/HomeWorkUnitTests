import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.title;

import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Tag("selenide")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SelenideTests {

    @BeforeAll
    void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox");// Bypass OS security model
        options.addArguments("--window-size=1920,1080");
        Configuration.browserCapabilities = options;
    }

    @Test
    void successfulLoginTest() {
        open("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");

        SelenideElement subTitle = $(By.className("display-6"));
        WebElement loginInput = $("#username");
        WebElement passwordInput = $("#password");
        WebElement submitButton = $(By.xpath("//button[@type='submit']"));

        loginInput.sendKeys("user");
        passwordInput.sendKeys("user");
        String textBeforeClick = subTitle.getText();
        submitButton.click();

        assertThat(textBeforeClick).isEqualTo("Login form");
        subTitle.shouldHave(text("Login form"));
        WebElement successMessage = $("#success");
        assertThat(successMessage.isDisplayed()).isTrue();
    }

    @Test
    void openSiteTest() {
        open("https://bonigarcia.dev/selenium-webdriver-java/");
        assertEquals("Hands-On Selenium WebDriver with Java", title());
    }

    @Test
    void logoClickTest() {
        open("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement logo = $(By.className("img-fluid"));
        logo.click();
        assertEquals("https://github.com/bonigarcia/selenium-webdriver-java", url());
    }

    @Test
    void textInputTest() {
        open("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        $("#my-text-id").shouldBe(visible);
        $("#my-text-id").setValue("всем привет");
        $("#my-text-id").shouldHave(value("всем привет"));
        assertEquals("всем привет", $("#my-text-id").getValue());
    }


    @Test
    void disabledFieldTest() {
        open("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        $(By.name("my-disabled")).shouldBe(disabled);
    }

    @Test
    void readOnlyInputTest() {
        open("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        $(By.name("my-readonly")).sendKeys("Тестовый текст");
        $(By.name("my-readonly")).shouldHave(value("Readonly input"));
    }

    @Test
    void textAreaInputClearTest() {
        open("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        $(By.name("my-textarea")).sendKeys("asdsdsadsddsa skdjsadsajdhkj hasjkdh jkshdjksah jdshkdh asjkhdsjkhd jkashdk jashdkj haskddsakj hdskajhd kashd kjashdk jhasdjk shdjksah khdskajhdjkash89-099-");
        $(By.name("my-textarea")).clear();
        $(By.name("my-textarea")).shouldHave(value(""));
    }

    @Test
    void selectFromListTest() {
        open("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        $("select[name='my-select']").selectOption("Two");
        $("select[name='my-select']").getSelectedOption().shouldHave(text("Two"));
        assertEquals("Two", $("select[name='my-select']").getSelectedOption().getText());
    }

    @Test
    void dataListInputTest(){
        open("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        $(By.name("my-datalist")).sendKeys("San Francisco");
        $(By.name("my-datalist")).shouldHave(value("San Francisco"));
    }

    @Test
    void selectAllCheckBoxTest(){
        open("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        if ( $(By.id("my-check-1")).isSelected()) {
            $(By.id("my-check-1")).click();
        }
        $(By.id("my-check-1")).click();

        if ( $(By.id("my-check-2")).isSelected()) {
            $(By.id("my-check-2")).click();
        }
        $(By.id("my-check-2")).click();
        $(By.id("my-check-1")).shouldBe(selected);
        $(By.id("my-check-2")).shouldBe(selected);
    }

    @Test
    void defaultSelectCheckBoxTest(){
        open("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        $(By.id("my-radio-1")).shouldBe(selected);
        $(By.id("my-radio-2")).shouldNotBe(selected);
    }

    @Test
    void defaultSelectRadioTest(){
        open("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        $(By.id("my-radio-1")).shouldBe(selected);
        $(By.id("my-radio-2")).shouldNotBe(selected);;
    }

    @Test
    void selectSecondAndSelectFirstAfterSecondRadio() {
        open("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
       $(By.id("my-radio-2")).click();

        $(By.id("my-radio-1")).shouldNotBe(selected);
        $(By.id("my-radio-2")).shouldBe(selected);;

        $(By.id("my-radio-1")).click();
        $(By.id("my-radio-1")).shouldBe(selected);
        $(By.id("my-radio-2")).shouldNotBe(selected);;

    }

}