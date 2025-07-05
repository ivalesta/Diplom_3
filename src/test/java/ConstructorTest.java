import driver.Driver;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobject.PageObjectMain;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ConstructorTest {
    WebDriver driver;
    String browser;
    Driver driverWrapper;
    PageObjectMain mainStellarBurgers;

    public ConstructorTest (String browser){
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
    }

    @Test
    @DisplayName("Проверка работы перехода в разделе Конструктор. Соусы")
    @Description("После успешного перехода на вкладку становится видимым заголовок с текстом Соусы")
    public void constructorSauceTest() {

        System.out.println("Начало проверки, открывается сайт Stellar Burgers");
        mainStellarBurgers.openMain();
        System.out.println("Главная страница открыта");
        mainStellarBurgers.clickSauceSection();
        System.out.println("Переход на вкладку Соусы");
        mainStellarBurgers.headerSauceIsVisible();
        System.out.println("Проверка видимости заголовка Соусы");
        System.out.println("   ");
    }

    @Test
    @DisplayName("Проверка работы перехода в разделе Конструктор. Булки")
    @Description("После успешного перехода на вкладку становится видимым заголовок с текстом Булки")
    public void constructorBunTest() {

        System.out.println("Начало проверки, открывается сайт Stellar Burgers");
        mainStellarBurgers.openMain();
        System.out.println("Главная страница открыта");
        mainStellarBurgers.clickSauceSection();
        System.out.println("Переход на вкладку Соусы");
        mainStellarBurgers.clickBunSection();
        System.out.println("Переход на вкладку Булки");
        mainStellarBurgers.headerBunIsVisible();
        System.out.println("Проверка видимости заголовка Булки");
        System.out.println("   ");
    }

    @Test
    @DisplayName("Проверка работы перехода в разделе Конструктор. Начинки")
    @Description("После успешного перехода на вкладку становится видимым заголовок с текстом Начинки")
    public void constructorFillingTest() {

        System.out.println("Начало проверки, открывается сайт Stellar Burgers");
        mainStellarBurgers.openMain();
        System.out.println("Главная страница открыта");
        mainStellarBurgers.clickFillingSection();
        System.out.println("Переход на вкладку Начинки");
        mainStellarBurgers.headerFillingIsVisible();
        System.out.println("Проверка видимости заголовка Начинки");
        System.out.println("   ");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
