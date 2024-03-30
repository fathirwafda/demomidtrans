/**
 * @author [Fathir Wafda]
 * @email [fathir.wafda@gmail.com]
 * @create date 2024-03-30 17:09:49
 * @modify date 2024-03-30 17:09:49
 * @desc [A class for Driver settings]
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
    public static WebDriver getDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}
