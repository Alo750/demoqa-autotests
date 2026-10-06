package pages;

import base.BasePage;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ButtonsPage extends BasePage {

    private final By doubleClickButton =
            By.id("doubleClickBtn");

    private final By rightClickButton =
            By.id("rightClickBtn");

    private final By doubleClickMessage =
            By.id("doubleClickMessage");

    private final By rightClickMessage =
            By.id("rightClickMessage");


    public ButtonsPage(
            WebDriver driver,
            WebDriverWait wait
    ) {
        super(driver, wait);
    }


    public void open() {

        Allure.step(
                "Открыть страницу Buttons",
                () -> driver.get(
                        "https://demoqa.com/buttons"
                )
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        doubleClickButton
                )
        );
    }


    public void doubleClickButton() {

        Allure.step(
                "Выполнить двойной клик",
                () -> {

                    WebElement button =
                            wait.until(
                                    ExpectedConditions
                                            .visibilityOfElementLocated(
                                                    doubleClickButton
                                            )
                            );

                    scrollTo(button);

                    wait.until(
                            ExpectedConditions
                                    .elementToBeClickable(
                                            doubleClickButton
                                    )
                    );

                    new Actions(driver)
                            .moveToElement(button)
                            .doubleClick()
                            .perform();

                    wait.until(
                            ExpectedConditions
                                    .visibilityOfElementLocated(
                                            doubleClickMessage
                                    )
                    );
                }
        );
    }


    public void rightClickButton() {

        Allure.step(
                "Выполнить правый клик",
                () -> {

                    WebElement button =
                            wait.until(
                                    ExpectedConditions
                                            .visibilityOfElementLocated(
                                                    rightClickButton
                                            )
                            );

                    scrollTo(button);

                    wait.until(
                            ExpectedConditions
                                    .elementToBeClickable(
                                            rightClickButton
                                    )
                    );

                    new Actions(driver)
                            .moveToElement(button)
                            .contextClick()
                            .perform();

                    wait.until(
                            ExpectedConditions
                                    .visibilityOfElementLocated(
                                            rightClickMessage
                                    )
                    );
                }
        );
    }


    public String getDoubleClickMessage() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        doubleClickMessage
                )
        ).getText();
    }


    public String getRightClickMessage() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        rightClickMessage
                )
        ).getText();
    }


    private void scrollTo(WebElement element) {

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block: 'center'});",
                        element
                );
    }
}