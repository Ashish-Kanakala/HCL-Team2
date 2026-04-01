package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    static ExtentReports extent;

    public static ExtentReports getReport() {

        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("extentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }

        return extent;
    }
}