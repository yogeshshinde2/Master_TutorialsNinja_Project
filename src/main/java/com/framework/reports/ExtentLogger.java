//ExtentLogger, ExtentManager,ExtentReport, FrameworkLogger classes are taken 
 //from PIM Automation
 
package com.framework.reports;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.framework.enums.ConfigProperties;
import com.framework.utils.PropertyFileRead;
import com.framework.utils.ScreenshotUtils;

public class ExtentLogger {
    private ExtentLogger(){

    }

    public static void pass(String message){
        ExtentManager.getExtentTest().pass(message);
    }

    public static void fail(String message){
        ExtentManager.getExtentTest().fail(message);
    }

    public static void skip(String message){
        ExtentManager.getExtentTest().skip(message);
    }

    public static void pass(String message, boolean isScreenshotNeeded) {
        if(PropertyFileRead.getPropValue(ConfigProperties.PASSEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes")
                && isScreenshotNeeded){
            ExtentManager.getExtentTest().pass(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
        }else{
            pass(message);
        }

    }

    public static void fail(String message, boolean isScreenshotNeeded){
        if(PropertyFileRead.getPropValue(ConfigProperties.FAILEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes")
                && isScreenshotNeeded){
            ExtentManager.getExtentTest().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
        }else{
            fail(message);
        }
    }

    public static void skip(String message, boolean isScreenshotNeeded) {
        if(PropertyFileRead.getPropValue(ConfigProperties.SKIPPEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes")
                && isScreenshotNeeded){
            ExtentManager.getExtentTest().skip(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
        }else{
            skip(message);
        }
    }
}

