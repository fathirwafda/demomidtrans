
/**
 * @author [Fathir Wafda]
 * @email [fathir.wafda@gmail.com]
 * @create date 2024-03-30 17:09:49
 * @modify date 2024-03-30 17:09:49
 * @desc [A class for Home page]
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickBuyNow() {
        WebElement buyNowButton = driver.findElement(By.xpath("//a[contains(text(),'BUY NOW')]"));
        buyNowButton.click();
    }

    public void clickCheckout() {
        WebElement checkoutButton = driver.findElement(By.xpath("//div[@class='cart-checkout']"));
        checkoutButton.click();
    }
}
