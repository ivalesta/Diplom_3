package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
    //Вкладка раздела "Булки" в активном состоянии
    public final By sectionBunActive = By.xpath(".//*[text()='Булки']/ancestor::div[contains(@class, 'tab')]");
    //Вкладка раздела "Соусы"
    public final By sectionSauce = By.xpath(".//span[text()='Соусы']");
    //Вкладка раздела "Соусы" в активном состоянии
    public final By sectionSauceActive = By.xpath(".//*[text()='Соусы']/ancestor::div[contains(@class, 'tab')]");
    //Вкладка раздела "Начинки"
    public final By sectionFilling = By.xpath(".//span[text()='Начинки']");
    //Вкладка раздела "Начинки" в активном состоянии
    public final By sectionFillingActive = By.xpath(".//*[text()='Начинки']/ancestor::div[contains(@class, 'tab')]");
    //Активное состояние вкладки
    public static String ACTIVE_SECTION = "tab_tab_type_current__2BEPc";

    //Методы
    @Step("Открыть Главную страницу")
    public void openMain () {
        driver.get(mainStellarBurgers);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(personalAccount));
    }

    @Step("Открыть Личный Кабинет")
    public void openPersonalAccount() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(personalAccount));
        driver.findElement(personalAccount).click();
    }

    @Step("Открыть страницу авторизации по кнопке Войти в аккаунт")
    public void openEnterPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(loginInToAccount));
        driver.findElement(loginInToAccount).click();
    }

    @Step("Клик по разделу Булки")
    public void clickBunSection() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(sectionBun));
        driver.findElement(sectionBun).click();
    }

    @Step("Клик по разделу Соусы")
    public void clickSauceSection() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(sectionSauce));
        driver.findElement(sectionSauce).click();
    }

    @Step("Клик по разделу Начинки")
    public void clickFillingSection() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(sectionFilling));
        driver.findElement(sectionFilling).click();
    }
}
