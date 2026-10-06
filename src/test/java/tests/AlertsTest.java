package tests;

import base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AlertsPage;

@Epic("Alerts, Frame & Windows")
@Feature("Alerts")
@Owner("Student")
public class AlertsTest extends BaseTest {

    @Test(
            description =
                    "Confirm: Cancel отображается корректно"
    )
    @Story("Confirm")
    @Severity(SeverityLevel.MINOR)
    public void shouldDismissConfirm() {

        AlertsPage page =
                new AlertsPage(driver, wait);

        page.open();

        page.openConfirmAndDismiss();

        String result =
                page.confirmText().toLowerCase();

        Assert.assertTrue(
                result.contains("cancel")
                        || result.contains("cancelled"),
                "Ожидался текст о выборе Cancel"
        );
    }

    @Test(
            description =
                    "Prompt: введённый текст отображается"
    )
    @Story("Prompt")
    @Severity(SeverityLevel.NORMAL)
    public void shouldAcceptPromptWithText() {

        AlertsPage page =
                new AlertsPage(driver, wait);

        page.open();

        page.openPromptAndSend("demo");

        Assert.assertTrue(
                page.promptText()
                        .toLowerCase()
                        .contains("demo"),
                "Ожидалось отображение введённого текста"
        );
    }
}