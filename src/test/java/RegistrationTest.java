import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import driver.Driver;
import pageobject.PageObjectLogin;
import pageobject.PageObjectMain;
import pageobject.PageObjectRegistration;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class RegistrationTest {
    WebDriver driver;
    String browser;
    Driver driverWrapper;
    PageObjectMain mainStellarBurgers;
    PageObjectLogin enterStellarBurgers;
    PageObjectRegistration registrationStellarBurgers;

    public RegistrationTest (String browser){
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
        enterStellarBurgers.checkRegistrationSuccess();
        System.out.println("Заголовок Вход появился");
        System.out.println("   ");
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
        registrationStellarBurgers.findErrorMessage();
        System.out.println("Сообщение об ошибке появилось");
        System.out.println("   ");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}