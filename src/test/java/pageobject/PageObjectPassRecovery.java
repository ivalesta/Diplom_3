package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageObjectPassRecovery {
    WebDriver driver;

    public PageObjectPassRecovery(WebDriver driver) {
        this.driver = driver;
    }

    //Локаторы
    //Кнопка "Войти" на странице восстановления пароля
    public final By buttonLogin = By.xpath(".//a[text()='Войти']");

    //Методы
    @Step("Нажать кнопку Войти на странице восстановления пароля")
    public void clickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(buttonLogin));
        driver.findElement(buttonLogin).click();
    }
}
