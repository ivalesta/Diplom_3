import driver.DriverProvider;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.PageObjectLogin;
import pageobject.PageObjectMain;
import pageobject.PageObjectPassRecovery;
import pageobject.PageObjectRegistration;
import ru.practicum.LoginOperations;
import ru.practicum.basis.Constants;
import ru.practicum.basis.UserBasis;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class AuthorizationTest {
    WebDriver driver;
    PageObjectMain mainStellarBurgers;
    PageObjectLogin enterStellarBurgers;
    PageObjectRegistration registrationStellarBurgers;
    PageObjectPassRecovery passRecoveryStellarBurgers;
    LoginOperations userCreate;
    UserBasis user;
    Constants constants = new Constants();
    private String accessToken;
    String name;
    String email;
    String password;


    @Before
    public void setUp() {
        DriverProvider driverProvider = new DriverProvider();
        driver = driverProvider.getDriver();

        RestAssured.baseURI = constants.BASE_URL;

        userCreate = new LoginOperations();
        name = RandomStringUtils.randomAlphanumeric(6, 10);
        email = RandomStringUtils.randomAlphanumeric(6, 10) + "@yandex.ru";
        password = RandomStringUtils.randomAlphabetic(6, 10);

        user = new UserBasis(name, email, password);

        Response response = userCreate.sendPostRequestCreateUser(user);
        accessToken = response.then().extract().path("accessToken").toString();

        mainStellarBurgers = new PageObjectMain(driver);
        enterStellarBurgers = new PageObjectLogin(driver);
        registrationStellarBurgers = new PageObjectRegistration(driver);
        passRecoveryStellarBurgers = new PageObjectPassRecovery(driver);
    }

    @Test
    @DisplayName("Успешный вход с главной страницы. Авторизация по кнопке Войти в аккаунт")
    @Description("После успешной авторизации открывается главная страница")
    public void mainPageLoginTest() {
        System.out.println("Начало проверки, открывается сайт Stellar Burgers");
        mainStellarBurgers.openMain();
        mainStellarBurgers.openEnterPage();
        System.out.println("Кнопка Войти в аккаунт нажата");
        enterStellarBurgers.sendRegistrationParam(email, password);
        System.out.println("Поля заполнены");
        enterStellarBurgers.clickLoginButton();
        System.out.println("Кнопка Войти нажата");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(mainStellarBurgers.buttonOrderCreate));
        assertTrue(header.isDisplayed());

        System.out.println("Открылась главная страница");
        System.out.println("   ");
    }

    @Test
    @DisplayName("Успешный вход из Личного Кабинета")
    @Description("После успешной авторизации открывается главная страница")
    public void personalAccountLoginTest() {
        System.out.println("Начало проверки, открывается сайт Stellar Burgers");
        mainStellarBurgers.openMain();
        mainStellarBurgers.openPersonalAccount();
        System.out.println("Кнопка Личный кабинет нажата");
        enterStellarBurgers.sendRegistrationParam(email, password);
        System.out.println("Поля заполнены");
        enterStellarBurgers.clickLoginButton();
        System.out.println("Кнопка Войти нажата");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(mainStellarBurgers.buttonOrderCreate));
        assertTrue(header.isDisplayed());

        System.out.println("Открылась главная страница");
        System.out.println("   ");

        Response response = userCreate.sendPostRequestLoginUser(user);
        response.then().log().all()
                .assertThat().statusCode(200).and().body( "success", Matchers.is(true));

        if(response.path("accessToken") != null) {
            accessToken = response.then().extract().path("accessToken").toString();
        }
    }

    @Test
    @DisplayName("Успешный вход со страницы регистрации")
    @Description("После успешной авторизации открывается главная страница")
    public void registrationPageLoginTest() {
        System.out.println("Начало проверки, открывается сайт Stellar Burgers");
        mainStellarBurgers.openMain();
        mainStellarBurgers.openPersonalAccount();
        System.out.println("Кнопка Личный кабинет нажата");
        enterStellarBurgers.clickRegistrationButton();
        System.out.println("Кнопка Зарегистрироваться в ЛК нажата");
        registrationStellarBurgers.clickEnterButton();
        System.out.println("Кнопка Войти на странице регистрации нажата");
        enterStellarBurgers.sendRegistrationParam(email, password);
        System.out.println("Поля заполнены");
        enterStellarBurgers.clickLoginButton();
        System.out.println("Кнопка Войти на странице авторизации нажата");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(mainStellarBurgers.buttonOrderCreate));
        assertTrue(header.isDisplayed());

        System.out.println("Открылась главная страница");
        System.out.println("   ");
    }

    @Test
    @DisplayName("Успешный вход со страницы восстановления пароля")
    @Description("После успешной авторизации открывается главная страница")
    public void passwordRecoveryLoginTest() {
        System.out.println("Начало проверки, открывается сайт Stellar Burgers");
        mainStellarBurgers.openMain();
        mainStellarBurgers.openPersonalAccount();
        System.out.println("Кнопка Личный кабинет нажата, переход на страницу авторизации");
        enterStellarBurgers.clickPassRecoveryButton();
        System.out.println("Кнопка Восстановить пароль на странице авторизации нажата");
        passRecoveryStellarBurgers.clickLoginButton();
        System.out.println("Кнопка Войти на странице восстановления пароля нажата");
        enterStellarBurgers.sendRegistrationParam(email, password);
        System.out.println("Поля заполнены");
        enterStellarBurgers.clickLoginButton();
        System.out.println("Кнопка Войти на странице авторизации нажата");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(mainStellarBurgers.buttonOrderCreate));
        assertTrue(header.isDisplayed());

        System.out.println("Открылась главная страница");
        System.out.println("   ");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (accessToken != null) {
            userCreate.sendDeleteRequestUser(accessToken);
        }
    }
}
