package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class PageObjectLogin {
    WebDriver driver;

    public PageObjectLogin(WebDriver driver) {
        this.driver = driver;
    }

    //Локаторы
    //Кнопка "Зарегистрироваться" на странице авторизации
    public final By registerUserButton = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Зарегистрироваться']");
    //Кнопка "Конструктор" на странице авторизации
    public final By buttonConstructor = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Конструктор']");
    //Кнопка "Войти" на странице авторизации
    public final By buttonLogIn = By.xpath(".//button[text()='Войти']");
    //Кнопка "Восстановить пароль" на странице авторизации
    public final By buttonPassRecovery = By.xpath(".//a[text()='Восстановить пароль']");
    //Заголовок "Вход" на странице авторизации
    public final By headerEnter = By.xpath(".//div[@class='Auth_login__3hAey']/h2[text()='Вход']");
    //Поля для авторизации
    public final By emailLoginField = By.xpath(".//input[@type='text']");
    public final By passwordLoginField = By.xpath(".//input[@type='password']");


    //Методы
    //Нажать кнопку "Зарегистрироваться" на странице авторизации
    public void clickRegistrationButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(registerUserButton));
        driver.findElement(registerUserButton).click();
    }

    //Нажать кнопку "Конструктор" — возвращение на главную
    public void clickConstructorButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(buttonConstructor));
        driver.findElement(buttonConstructor).click();
    }

    //Нажать кнопку "Войти" на странице авторизации
    public void clickLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(buttonLogIn));
        driver.findElement(buttonLogIn).click();
    }

    //Нажать кнопку "Восстановить пароль" на странице авторизации
    public void clickPassRecoveryButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(buttonPassRecovery));
        driver.findElement(buttonPassRecovery).click();
    }

    //Проверка открытия страницы авторизации: появляется заголовок "Вход"
    public void checkRegistrationSuccess() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(headerEnter));
        assertTrue(header.isDisplayed());
    }

    //Заполнить поля данными на странице авторизации
    public void sendRegistrationParam(String email, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(emailLoginField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLoginField)).sendKeys(password);
    }
}
