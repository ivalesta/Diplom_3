import driver.DriverProvider;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.PageObjectMain;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class ConstructorTest {
    WebDriver driver;
    PageObjectMain mainStellarBurgers;

    @Before
    public void setUp() {
        DriverProvider driverProvider = new DriverProvider();
        driver = driverProvider.getDriver();

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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> driver.findElement(mainStellarBurgers.sectionSauceActive).getAttribute("class").contains(mainStellarBurgers.ACTIVE_SECTION));
        assertTrue("Вкладка не активна", driver.findElement(mainStellarBurgers.sectionSauceActive).getAttribute("class").contains(mainStellarBurgers.ACTIVE_SECTION));

        System.out.println("Раздел Соусы активен");
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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> driver.findElement(mainStellarBurgers.sectionBunActive).getAttribute("class").contains(mainStellarBurgers.ACTIVE_SECTION));
        assertTrue("Вкладка не активна", driver.findElement(mainStellarBurgers.sectionBunActive).getAttribute("class").contains(mainStellarBurgers.ACTIVE_SECTION));

        System.out.println("Раздел Булки активен");
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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver -> driver.findElement(mainStellarBurgers.sectionFillingActive).getAttribute("class").contains(mainStellarBurgers.ACTIVE_SECTION));
        assertTrue("Вкладка не активна", driver.findElement(mainStellarBurgers.sectionFillingActive).getAttribute("class").contains(mainStellarBurgers.ACTIVE_SECTION));

        System.out.println("Раздел Начинки активен");
        System.out.println("   ");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
