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
import pages.TextBoxPage;

@Epic("Elements")
@Feature("Text Box")
@Owner("Student")
public class TextBoxTest extends BaseTest {

    @Test(
            description =
                    "Позитив: валидные данные отображают результат"
    )
    @Story("Корректное заполнение Text Box")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldSubmitValidData() {

        TextBoxPage page =
                new TextBoxPage(driver, wait);

        page.open();

        page.fill(
                "Ivan Petrov",
                "ivan.petrov@example.com",
                "Current address",
                "Permanent address"
        );

        Assert.assertTrue(
                page.isOutputVisible(),
                "Ожидалось отображение блока результатов"
        );
    }

    @Test(
            description =
                    "Негатив: невалидный email не проходит HTML5-валидацию"
    )
    @Story("Проверка email")
    @Severity(SeverityLevel.NORMAL)
    public void shouldRejectInvalidEmail() {

        TextBoxPage page =
                new TextBoxPage(driver, wait);

        page.open();

        page.fill(
                "Ivan Petrov",
                "not-an-email",
                "Current address",
                "Permanent address"
        );

        Assert.assertFalse(
                page.isEmailValid(),
                "Невалидный email должен не проходить проверку"
        );
    }
}