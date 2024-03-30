/**
 * @author [Fathir Wafda]
 * @email [fathir.wafda@gmail.com]
 * @create date 2024-03-30 17:09:49
 * @modify date 2024-03-30 17:09:49
 * @desc [A class for Payment page]
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration; // Import the Duration class

public class PaymentPage {
  private WebDriver driver;

  public PaymentPage(WebDriver driver) {
    this.driver = driver;
  }

  public void selectCreditCardPayment() {
    driver.switchTo().frame("snap-midtrans");
    WebElement creditCardOption = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#/credit-card']")));
    creditCardOption.click();
  }

  public void enterCardDetails(String cardNumber, String expiryDate, String cvv) {
    WebElement cardNumberInput = driver.findElement(By.className("valid-input-value"));
    cardNumberInput.sendKeys(cardNumber);

    WebElement expiryDateInput = driver.findElement(By.xpath("//input[@placeholder='MM/YY']"));
    expiryDateInput.sendKeys(expiryDate);

    WebElement cvvInput = driver.findElement(By.xpath("//input[@placeholder='123']"));
    cvvInput.sendKeys(cvv);

    WebElement payNowButton = driver.findElement(By.xpath("//button[contains(text(),'Pay now')]"));
    payNowButton.click();
  }

  public void enterOTP(String otp) {
    // driver.switchTo().frame(0);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));

    WebElement otpInput = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(By.id("otp")));
    otpInput.sendKeys(otp);

    WebElement okButton = driver.findElement(By.name("ok"));
    okButton.click();
  }

  public String getTransactionStatus() {
    driver.switchTo().defaultContent();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    WebElement transactionStatus = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.cssSelector("div.trans-status.trans-success")));
    return transactionStatus.getText();
  }

  public String getInvalidCardStatus() {
    WebElement transactionStatus = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-warning text-failed']")));

    return transactionStatus.getText();
  }

  public String getExpiredDateStatus() {
    WebElement transactionStatus = new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-warning text-failed']")));

    return transactionStatus.getText();
  }
}
