/**
 * @author [Fathir Wafda]
 * @email [fathir.wafda@gmail.com]
 * @create date 2024-03-30 17:09:49
 * @modify date 2024-03-30 17:09:49
 * @desc [A class for Initializing Test]
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterSuite;

public class InitTest {
  protected WebDriver driver;
  protected ExtentReports extent;
  protected ExtentTest test;

  @BeforeMethod
  public void setUp() {
    WebDriverManager.firefoxdriver().setup();
    driver = new FirefoxDriver();
    driver.get("https://demo.midtrans.com/");
    if (extent == null) {
      // Initialize ExtentReports instance if not already done
      extent = ReportManager.getInstance();
    }
  }

  @AfterMethod
  public void tearDown(ITestResult result) {
    if (result.getStatus() == ITestResult.FAILURE) {
      test.fail(result.getThrowable());
    } else if (result.getStatus() == ITestResult.SUCCESS) {
      test.pass("Test passed");
    } else if (result.getStatus() == ITestResult.SKIP) {
      test.skip("Test skipped");
    }

    if (driver != null) {
      driver.quit();
    }

  }

  @AfterSuite
  public void afterSuite() {
    if (extent != null) {
      extent.flush();
    }
  }
}
