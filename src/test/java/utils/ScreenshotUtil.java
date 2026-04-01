package utils;

import org.openqa.selenium.*;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

    public static String capture(WebDriver driver, String name) throws Exception {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String path = "screenshots/" + name + ".png";
        FileUtils.copyFile(src, new File(path));

        return path;
    }
}