import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import driver.DriverProvider;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.PageObjectLogin;
import pageobject.PageObjectMain;
import pageobject.PageObjectRegistration;
import ru.practicum.LoginOperations;
import ru.practicum.basis.Constants;
import ru.practicum.basis.UserBasis;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class RegistrationTest {
    WebDriver driver;
    PageObjectMain mainStellarBurgers;
    PageObjectLogin enterStellarBurgers;
    PageObjectRegistration registrationStellarBurgers;

    PageObjectLogin constantsPathLogin;
    PageObjectRegistration constantsPathReg;

    String accessToken;
    LoginOperations userCreate;
    UserBasis user;
    Constants constants = new Constants();

    @Before
    public void setUp() {
        DriverProvider driverProvider = new DriverProvider();
        driver = driverProvider.getDriver();

        mainStellarBurgers = new PageObjectMain(driver);
        enterStellarBurgers = new PageObjectLogin(driver);
        registrationStellarBurgers = new PageObjectRegistration(driver);

        RestAssured.baseURI = constants.BASE_URL;
        userCreate = new LoginOperations();
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("После успешной регистрации открывается страница авторизации")
    public void successRegistrationTest() {
        String name = RandomStringUtils.randomAlphanumeric(6, 10);
        String email = RandomStringUtils.randomAlphanumeric(6, 10) + "@yandex.ru";
        String password = RandomStringUtils.randomAlphabetic(6, 10);

        System.out.println("Начало проверки, открывается сайт Stellar Burgers");
        mainStellarBurgers.openMain();
        System.out.println("Главная страница открыта");
        mainStellarBurgers.openPersonalAccount();
        System.out.println("ЛК открыт");
        enterStellarBurgers.clickRegistrationButton();
        System.out.println("Кнопка регистрации нажата");
        registrationStellarBurgers.sendRegistrationParam(name, email, password);
        System.out.println("Поля заполнены");
        registrationStellarBurgers.clickRegisterButton();
        System.out.println("Кнопка регистрации нажата");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(enterStellarBurgers.headerEnter));
        assertTrue(header.isDisplayed());

        System.out.println("Заголовок Вход появился");
        System.out.println("   ");

        user = new UserBasis(name, email, password);
        Response response = userCreate.sendPostRequestLoginUser(user);
        if(response.path("accessToken") != null) {
            accessToken = response.then().extract().path("accessToken").toString();
        }
    }

    @Test
    @DisplayName("Появление сообщения об ошибке: Некорректный пароль")
    @Description("После попытки регистрации появляется сообщение об ошибке, если пароль меньше 6 символов")
    public void registrationShortPasswordTest() {
        String name = RandomStringUtils.randomAlphanumeric(6, 10);
        String email = RandomStringUtils.randomAlphanumeric(6, 10) + "@yandex.ru";
        String password = RandomStringUtils.randomAlphabetic(1, 5);

        System.out.println("Начало проверки, открывается сайт Stellar Burgers");
        mainStellarBurgers.openMain();
        System.out.println("Главная страница открыта");
        mainStellarBurgers.openPersonalAccount();
        System.out.println("ЛК открыт");
        enterStellarBurgers.clickRegistrationButton();
        System.out.println("Кнопка регистрации нажата");
        registrationStellarBurgers.sendRegistrationParam(name, email, password);
        System.out.println("Поля заполнены");
        registrationStellarBurgers.clickRegisterButton();
        System.out.println("Кнопка регистрации нажата");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(registrationStellarBurgers.shortPasswordError));
        assertTrue(errorMessage.isDisplayed());

        System.out.println("Сообщение об ошибке появилось");
        System.out.println("   ");

        user = new UserBasis(name, email, password);
        Response response = userCreate.sendPostRequestLoginUser(user);
        if(response.path("accessToken") != null) {
            accessToken = response.then().extract().path("accessToken").toString();
        }
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