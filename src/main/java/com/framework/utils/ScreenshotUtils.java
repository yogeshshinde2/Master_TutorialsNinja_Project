
 //Taken from PIM Automation for Reporting
 
package com.framework.utils;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotUtils {

    private ScreenshotUtils() {}

    public static String getBase64Image() {
        return ((TakesScreenshot) Utilities.getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}


