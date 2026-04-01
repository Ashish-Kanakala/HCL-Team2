package tests;

import org.testng.annotations.*;
import base.BaseTest;
import pages.FlightPage;
import utils.*;
import com.aventstack.extentreports.*;

public class FlightTest extends BaseTest {

    ExtentReports extent = ExtentManager.getReport();
    ExtentTest test;

    @DataProvider(name = "data")
    public Object[][] dataProvider() throws Exception {
        return ExcelReader.getData("src/test/java/data/testdata.xlsx");
    }

    @Test(dataProvider = "data")
    public void testFlight(String tc, String from, String to,
                           String days, String trip, String passengers,
                           String cabin, String expected) throws Exception {

        test = extent.createTest(tc);

        FlightPage page = new FlightPage(driver);

        // 🔥 IMPORTANT FIX
        page.handlePopups();

        int dayValue = (int) Double.parseDouble(days);
        int passengerCount = (int) Double.parseDouble(passengers);

        try {
            page.enterFrom(from);
            page.enterTo(to);
            page.selectDate(dayValue);
            page.search();

            boolean result = page.verify();

            if (expected.toLowerCase().contains("valid") && result) {
                test.pass("Test Passed");
                ReportGenerator.addPassengers(passengerCount);
            } else {
                test.fail("Test Failed");
            }

        } catch (Exception e) {
            String path = ScreenshotUtil.capture(driver, tc);
            test.fail("Exception Occurred").addScreenCaptureFromPath(path);
        }
    }

    @AfterSuite
    public void teardown() throws Exception {
        extent.flush();
        ReportGenerator.generateReport();
    }
}