package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class PageObjectMain {
    WebDriver driver;

    public PageObjectMain(WebDriver driver) {
        this.driver = driver;
    }

    //Локаторы
    //URL Stellar Burgers
    public final String mainStellarBurgers = "https://stellarburgers.nomoreparties.site/";
    //Кнопка ЛК на главной странице
    public final By personalAccount = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");
    //Кнопка "Войти в аккаунт" на главной странице
    public final By loginInToAccount = By.xpath(".//button[text()='Войти в аккаунт']");
    //Кнопка "Оформить заказ" на главной странице
    public final By buttonOrderCreate = By.xpath(".//button[text()='Оформить заказ']");
    //Вкладка раздела "Булки"
    public final By sectionBun = By.xpath(".//span[text()='Булки']");
    //Вкладка раздела "Соусы"
    public final By sectionSauce = By.xpath(".//span[text()='Соусы']");
    //Вкладка раздела "Начинки"
    public final By sectionFilling = By.xpath(".//span[text()='Начинки']");
    //Заголовок раздела "Булки"
    public final By headerBun = By.xpath(".//h2[text()='Булки']");
    //Заголовок раздела "Соусы"
    public final By headerSauce = By.xpath(".//h2[text()='Соусы']");
    //Заголовок раздела "Начинки"
    public final By headerFilling = By.xpath(".//h2[text()='Начинки']");

    //Методы
    //Открыть Главную страницу
    public void openMain () {
        driver.get(mainStellarBurgers);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(personalAccount));
    }

    //Открыть "Личный Кабинет"
    public void openPersonalAccount() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(personalAccount));
        driver.findElement(personalAccount).click();
    }

    //Открыть страницу авторизации по кнопке "Войти в аккаунт"
    public void openEnterPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(loginInToAccount));
        driver.findElement(loginInToAccount).click();
    }

    //Клик по разделу "Булки"
    public void clickBunSection() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(sectionBun));
        driver.findElement(sectionBun).click();
    }
    //Клик по разделу "Соусы"
    public void clickSauceSection() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(sectionSauce));
        driver.findElement(sectionSauce).click();
    }
    //Клик по разделу "Начинки"
    public void clickFillingSection() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(sectionFilling));
        driver.findElement(sectionFilling).click();
    }

    //Проверка возвращения на главную страницу: появляется кнопка "Оформить заказ"
    public void checkMainLoginButton () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(buttonOrderCreate));
        assertTrue(header.isDisplayed());
    }

    //Проверка видимости заголовка "Булки"
    public void headerBunIsVisible () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(headerBun));
        assertTrue(header.isDisplayed());
    }
    //Проверка видимости заголовка "Соусы"
    public void headerSauceIsVisible () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(headerSauce));
        assertTrue(header.isDisplayed());
    }
    //Проверка видимости заголовка "Начинки"
    public void headerFillingIsVisible () {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(headerFilling));
        assertTrue(header.isDisplayed());
    }
}
