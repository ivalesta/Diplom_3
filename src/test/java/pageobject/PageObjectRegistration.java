package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class PageObjectRegistration {
    WebDriver driver;

    public PageObjectRegistration(WebDriver driver) {
        this.driver = driver;
    }

    //Локаторы
    //Поля для регистрации
    public final By nameRegField = By.xpath(".//label[text()='Имя']/following-sibling::input");
    public final By emailRegField = By.xpath(".//label[text()='Email']/following-sibling::input");
    public final By passwordRegField = By.xpath(".//input[@name='Пароль']");
    //Кнопка "Зарегистрироваться"
    public final By registrationButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()='Зарегистрироваться']");
    //Кнопка "Войти" на странице регистрации
    public final By buttonLogin = By.xpath(".//a[text()='Войти']");
    //Сообщение об ошибке "Некорректный пароль" на странице регистрации
    public final By shortPasswordError = By.xpath(".//p[text()='Некорректный пароль']");

    //Методы
    //Заполнить поля данными на странице регистрации
    public void sendRegistrationParam(String name, String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(nameRegField)).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailRegField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordRegField)).sendKeys(password);
    }

    //Нажать кнопку "Зарегистрироваться" на странице регистрации
    public void clickRegisterButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(registrationButton));
        driver.findElement(registrationButton).click();
    }

    //Нажать кнопку "Зарегистрироваться" на странице регистрации
    public void clickEnterButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(buttonLogin));
        driver.findElement(buttonLogin).click();
    }

    //Найти сообщение об ошибке на странице регистрации
    public void findErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(shortPasswordError));
        assertTrue(errorMessage.isDisplayed());
    }
}
