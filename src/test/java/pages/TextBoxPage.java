package pages;

import base.BasePage;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TextBoxPage extends BasePage {

    private final By userName =
            By.id("userName");

    private final By userEmail =
            By.id("userEmail");

    private final By currentAddress =
            By.id("currentAddress");

    private final By permanentAddress =
            By.id("permanentAddress");

    private final By submit =
            By.id("submit");

    private final By output =
            By.id("output");

    public TextBoxPage(
            WebDriver driver,
            WebDriverWait wait
    ) {
        super(driver, wait);
    }

    public void open() {

        Allure.step(
                "Открыть страницу Text Box",
                () -> driver.get(
                        "https://demoqa.com/text-box"
                )
        );
    }

    public void fill(
            String name,
            String email,
            String current,
            String permanent
    ) {

        Allure.step(
                "Заполнить форму Text Box",
                () -> {

                    type(userName, name);
                    type(userEmail, email);
                    type(currentAddress, current);
                    type(permanentAddress, permanent);

                    WebElement submitButton = el(submit);

                    ((JavascriptExecutor) driver)
                            .executeScript(
                                    "arguments[0].scrollIntoView({block: 'center'});",
                                    submitButton
                            );

                    ((JavascriptExecutor) driver)
                            .executeScript(
                                    "arguments[0].click();",
                                    submitButton
                            );
                }
        );
    }

    public boolean isOutputVisible() {

        return Allure.step(
                "Проверить отображение результата",
                () -> el(output).isDisplayed()
        );
    }

    public boolean isEmailValid() {

        return Allure.step(
                "Проверить HTML5-валидацию email",
                () -> (Boolean) ((JavascriptExecutor) driver)
                        .executeScript(
                                "return arguments[0].checkValidity();",
                                el(userEmail)
                        )
        );
    }

    public String emailFieldClass() {

        return el(userEmail)
                .getAttribute("class");
    }
}