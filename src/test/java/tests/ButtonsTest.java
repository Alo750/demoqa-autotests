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
import pages.ButtonsPage;

@Epic("Elements")
@Feature("Buttons")
@Owner("Novruzova")
public class ButtonsTest extends BaseTest {

    @Test(
            description = "Double Click отображает корректное сообщение"
    )
    @Story("Double Click")
    @Severity(SeverityLevel.NORMAL)
    public void shouldHandleDoubleClick() {

        ButtonsPage page =
                new ButtonsPage(driver, wait);

        page.open();

        page.doubleClickButton();

        String message =
                page.getDoubleClickMessage();

        Assert.assertTrue(
                message != null && !message.isBlank(),
                "После двойного клика должно появиться сообщение"
        );

        Assert.assertTrue(
                message.toLowerCase().contains("double click"),
                "Ожидалось сообщение о двойном клике. Фактически: "
                        + message
        );
    }


    @Test(
            description = "Right Click отображает корректное сообщение"
    )
    @Story("Right Click")
    @Severity(SeverityLevel.NORMAL)
    public void shouldHandleRightClick() {

        ButtonsPage page =
                new ButtonsPage(driver, wait);

        page.open();

        page.rightClickButton();

        String message =
                page.getRightClickMessage();

        Assert.assertTrue(
                message != null && !message.isBlank(),
                "После правого клика должно появиться сообщение"
        );

        Assert.assertTrue(
                message.toLowerCase().contains("right click"),
                "Ожидалось сообщение о правом клике. Фактически: "
                        + message
        );
    }
}