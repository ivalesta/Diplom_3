package driver;

import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class Driver {
    WebDriver driver;
    ChromeOptions options = new ChromeOptions();

    String YANDEX_BROWSER_PATH = "C:\\Users\\Sayomi.DESKTOP-8FUPSHJ\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";
    String YANDEX_DRIVER_PATH = "D:\\projects\\Diplom\\Diplom_3\\yandexdriver.exe";

    public Driver(String browser) {
        this.driver = initDriver(browser);
    }

    public WebDriver getDriver() {
        return driver;
    }

    private ChromeDriver initDriver(String browser) {

        if ("yandex".equalsIgnoreCase(browser)) {
            // Путь к Yandex.Browser
            options.setBinary(YANDEX_BROWSER_PATH);
            //Путь к драйверу yandexdriver
            File driverFile = new File(YANDEX_DRIVER_PATH);
            System.setProperty("webdriver.chrome.driver", driverFile.getAbsolutePath());
            return new ChromeDriver(options);
        }
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
