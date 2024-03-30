/**
 * @author [Fathir Wafda]
 * @email [fathir.wafda@gmail.com]
 * @create date 2024-03-30 17:09:49
 * @modify date 2024-03-30 17:09:49
 * @desc [A class for Managing Reports]
 */

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;

public class ReportManager {
    private static ExtentReports extent;

    public synchronized static ExtentReports getInstance() {
        if (extent == null) {

            String reportPath = System.getProperty("user.dir") + "/test-output/testReport.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

            // Initialize a new ExtentReports instance and attach the reporter
            extent = new ExtentReports();
            extent.attachReporter(reporter);

            reporter.config().setTheme(Theme.STANDARD);
            reporter.config().setDocumentTitle("Automation Report");
            reporter.config().setEncoding("utf-8");
            reporter.config().setReportName("Automation Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }
}
