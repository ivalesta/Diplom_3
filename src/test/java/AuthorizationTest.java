import driver.Driver;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobject.PageObjectLogin;
import pageobject.PageObjectMain;
import pageobject.PageObjectPassRecovery;
import pageobject.PageObjectRegistration;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class AuthorizationTest {
    WebDriver driver;
    String browser;
    Driver driverWrapper;
    PageObjectMain mainStellarBurgers;
    PageObjectLogin enterStellarBurgers;
    PageObjectRegistration registrationStellarBurgers;
    PageObjectPassRecovery passRecoveryStellarBurgers;

    public AuthorizationTest (String browser){
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "browser")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"chrome"},
                {"yandex"}
        });
    }

    @Before
    public void setUp() {
        driverWrapper = new Driver(browser);
        driver = driverWrapper.getDriver();

        mainStellarBurgers = new PageObjectMain(driver);
        enterStellarBurgers = new PageObjectLogin(driver);
        registrationStellarBurgers = new PageObjectRegistration(driver);
        passRecoveryStellarBurgers = new PageObjectPassRecovery(driver);
    }

    @Test
    @DisplayName("Успешный вход с главной страницы. Авторизация по кнопке Войти в аккаунт")
    @Description("После успешной авторизации открывается главная страница")
    public void mainPageLoginTest() {
        String name = RandomStringUtils.randomAlphanumeric(6, 10);
        String email = RandomStringUtils.randomAlphanumeric(6, 10) + "@yandex.ru";
        String password = RandomStringUtils.randomAlphabetic(6, 10);

        System.out.println("Регистрация пользователя, чтобы была возможность авторизации");
        mainStellarBurgers.openMain();
        mainStellarBurgers.openPersonalAccount();
        enterStellarBurgers.clickRegistrationButton();
        registrationStellarBurgers.sendRegistrationParam(name, email, password);
        registrationStellarBurgers.clickRegisterButton();
        System.out.println("Пользователь зарегистрирован");
        enterStellarBurgers.clickConstructorButton();
        System.out.println("Возвращение на главную страницу");
        mainStellarBurgers.openEnterPage();
        System.out.println("Кнопка Войти в аккаунт нажата");
        enterStellarBurgers.sendRegistrationParam(email, password);
        System.out.println("Поля заполнены");
        enterStellarBurgers.clickLoginButton();
        System.out.println("Кнопка Войти нажата");
        mainStellarBurgers.checkMainLoginButton();
        System.out.println("Открылась главная страница");
        System.out.println("   ");
    }

    @Test
    @DisplayName("Успешный вход из Личного Кабинета")
    @Description("После успешной авторизации открывается главная страница")
    public void personalAccountLoginTest() {
        String name = RandomStringUtils.randomAlphanumeric(6, 10);
        String email = RandomStringUtils.randomAlphanumeric(6, 10) + "@yandex.ru";
        String password = RandomStringUtils.randomAlphabetic(6, 10);

        System.out.println("Регистрация пользователя, чтобы была возможность авторизации");
        mainStellarBurgers.openMain();
        mainStellarBurgers.openPersonalAccount();
        enterStellarBurgers.clickRegistrationButton();
        registrationStellarBurgers.sendRegistrationParam(name, email, password);
        registrationStellarBurgers.clickRegisterButton();
        System.out.println("Пользователь зарегистрирован");
        enterStellarBurgers.clickConstructorButton();
        System.out.println("Возвращение на главную страницу");
        mainStellarBurgers.openPersonalAccount();
        System.out.println("Кнопка Личный кабинет нажата");
        enterStellarBurgers.sendRegistrationParam(email, password);
        System.out.println("Поля заполнены");
        enterStellarBurgers.clickLoginButton();
        System.out.println("Кнопка Войти нажата");
        mainStellarBurgers.checkMainLoginButton();
        System.out.println("Открылась главная страница");
        System.out.println("   ");
    }

    @Test
    @DisplayName("Успешный вход со страницы регистрации")
    @Description("После успешной авторизации открывается главная страница")
    public void registrationPageLoginTest() {
        String name = RandomStringUtils.randomAlphanumeric(6, 10);
        String email = RandomStringUtils.randomAlphanumeric(6, 10) + "@yandex.ru";
        String password = RandomStringUtils.randomAlphabetic(6, 10);

        System.out.println("Регистрация пользователя, чтобы была возможность авторизации");
        mainStellarBurgers.openMain();
        mainStellarBurgers.openPersonalAccount();
        enterStellarBurgers.clickRegistrationButton();
        registrationStellarBurgers.sendRegistrationParam(name, email, password);
        registrationStellarBurgers.clickRegisterButton();
        System.out.println("Пользователь зарегистрирован");
        enterStellarBurgers.clickConstructorButton();
        System.out.println("Возвращение на главную страницу");
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
        mainStellarBurgers.checkMainLoginButton();
        System.out.println("Открылась главная страница");
        System.out.println("   ");
    }

    @Test
    @DisplayName("Успешный вход со страницы восстановления пароля")
    @Description("После успешной авторизации открывается главная страница")
    public void passwordRecoveryLoginTest() {
        String name = RandomStringUtils.randomAlphanumeric(6, 10);
        String email = RandomStringUtils.randomAlphanumeric(6, 10) + "@yandex.ru";
        String password = RandomStringUtils.randomAlphabetic(6, 10);

        System.out.println("Регистрация пользователя, чтобы была возможность авторизации");
        mainStellarBurgers.openMain();
        mainStellarBurgers.openPersonalAccount();
        enterStellarBurgers.clickRegistrationButton();
        registrationStellarBurgers.sendRegistrationParam(name, email, password);
        registrationStellarBurgers.clickRegisterButton();
        System.out.println("Пользователь зарегистрирован");
        enterStellarBurgers.clickConstructorButton();
        System.out.println("Возвращение на главную страницу");
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
        mainStellarBurgers.checkMainLoginButton();
        System.out.println("Открылась главная страница");
        System.out.println("   ");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
