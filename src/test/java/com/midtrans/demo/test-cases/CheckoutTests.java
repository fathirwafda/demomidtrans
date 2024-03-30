/**
 * @author [Fathir Wafda]
 * @email [fathir.wafda@gmail.com]
 * @create date 2024-03-30 17:09:49
 * @modify date 2024-03-30 17:09:49
 * @desc [A class for Checkout test cases]
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.SkipException;

public class CheckoutTests extends InitTest {
    private HomePage homePage;
    private PaymentPage paymentPage;

    @BeforeMethod
    public void initPages() {
        homePage = new HomePage(driver);
        paymentPage = new PaymentPage(driver);
    }

    @AfterTest
    public void tearDown() {
        // to write or update test information to reporter text-headline medium
        extent.flush();
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void successfulCheckoutTest() {
        homePage.clickBuyNow();
        homePage.clickCheckout();
        paymentPage.selectCreditCardPayment();
        paymentPage.enterCardDetails("4811111111111114", "1224", "123");
        paymentPage.enterOTP("112233");
        String actualText = paymentPage.getTransactionStatus().replaceAll("\\s+", " ").trim();
        String expectedText = "Thank you for your purchase. Get a nice sleep.".replaceAll("\\s+", " ").trim();
        Assert.assertEquals(actualText, expectedText);
        test = extent.createTest("Test Case: Checkout CC Valid", "Checkout with Valid Card");
        // test.pass("Details of what the pass actually means");
    }

    @Test
    public void checkoutWithInvalidCardTest() {
        homePage.clickBuyNow();
        homePage.clickCheckout();
        paymentPage.selectCreditCardPayment();
        paymentPage.enterCardDetails("1234 5678 9012 3456", "12/24", "123");
        Assert.assertEquals(paymentPage.getInvalidCardStatus(), "Make sure your card info is correct.");
        test = extent.createTest("Test Case: Checkout CC Invalid", "Checkout with Invalid Card Number");
    }

    @Test
    public void checkoutWithExpiredDateTest() {
        homePage.clickBuyNow();
        homePage.clickCheckout();
        paymentPage.selectCreditCardPayment();
        paymentPage.enterCardDetails("4811111111111114", "12/21", "123");
        Assert.assertEquals(paymentPage.getExpiredDateStatus(), "Invalid expiry.");
        test = extent.createTest("Test Case: Checkout CC Expired", "Checkout with Expired Date Card");
    }
}
