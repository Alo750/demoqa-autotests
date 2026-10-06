package pages;

import base.BasePage;
import io.qameta.allure.Allure;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsPage extends BasePage {

    private final By alertButton =
            By.id("alertButton");

    private final By confirmButton =
            By.id("confirmButton");

    private final By promptButton =
            By.id("promtButton");

    private final By confirmResult =
            By.id("confirmResult");

    private final By promptResult =
            By.id("promptResult");

    public AlertsPage(
            WebDriver driver,
            WebDriverWait wait
    ) {
        super(driver, wait);
    }

    public void open() {

        Allure.step(
                "Открыть страницу Alerts",
                () -> driver.get(
                        "https://demoqa.com/alerts"
                )
        );
    }

    public void openAlertAndAccept() {

        Allure.step(
                "Открыть и принять alert",
                () -> {

                    click(alertButton);

                    wait.until(
                            ExpectedConditions.alertIsPresent()
                    ).accept();
                }
        );
    }

    public void openConfirmAndDismiss() {

        Allure.step(
                "Открыть Confirm и нажать Cancel",
                () -> {

                    click(confirmButton);

                    wait.until(
                            ExpectedConditions.alertIsPresent()
                    ).dismiss();
                }
        );
    }

    public void openPromptAndSend(String text) {

        Allure.step(
                "Открыть Prompt и ввести текст",
                () -> {

                    click(promptButton);

                    Alert alert = wait.until(
                            ExpectedConditions.alertIsPresent()
                    );

                    alert.sendKeys(text);
                    alert.accept();
                }
        );
    }

    public String confirmText() {

        return text(confirmResult);
    }

    public String promptText() {

        return text(promptResult);
    }
}