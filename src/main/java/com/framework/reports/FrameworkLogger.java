//ExtentLogger, ExtentManager,ExtentReport, FrameworkLogger classes are taken 
//from PIM Automation
 
package com.framework.reports;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.framework.utils.Utilities;
import com.framework.enums.ConfigProperties;
import com.framework.enums.LogType;
import com.framework.utils.PropertyFileRead;
import com.framework.utils.ScreenshotUtils;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class FrameworkLogger {

    private FrameworkLogger() {}

    private static final Consumer<String> PASS = (message) -> {
        ExtentTest extentTest = ExtentManager.getExtentTest();
        if (extentTest != null) {
            extentTest.pass(message);
        }
    };

    private static final Consumer<String> FAIL = (message) -> {
        ExtentTest extentTest = ExtentManager.getExtentTest();
        if (extentTest != null) {
            extentTest.fail(message);
        }
    };

    private static final Consumer<String> SKIP = (message) -> {
        ExtentTest extentTest = ExtentManager.getExtentTest();
        if (extentTest != null) {
            extentTest.skip(message);
        }
    };

    private static final Consumer<String> INFO = (message) -> {
        ExtentTest extentTest = ExtentManager.getExtentTest();
        if (extentTest != null) {
            extentTest.info(message);
        }
    };

    private static final Consumer<String> CONSOLE = (message) -> System.out.println("INFO---->" + message);

    private static final Consumer<String> EXTENTANDCONSOLE = PASS.andThen(CONSOLE);

    private static final Consumer<String> TAKESCREENSHOT = (message) -> {
        ExtentTest extentTest = ExtentManager.getExtentTest();
        if (extentTest != null && Utilities.getDriver() != null) {
            extentTest.info("", MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
        }
    };

    private static final Map<LogType, Consumer<String>> MAP = new EnumMap<>(LogType.class);

    private static final Map<LogType, Consumer<String>> SCREENSHOTMAP = new EnumMap<>(LogType.class);

    static {
        MAP.put(LogType.PASS, PASS);
        MAP.put(LogType.FAIL, FAIL.andThen(TAKESCREENSHOT));
        MAP.put(LogType.SKIP, SKIP);
        MAP.put(LogType.INFO, INFO);
        MAP.put(LogType.CONSOLE, CONSOLE);
        MAP.put(LogType.EXTENTANDCONSOLE, EXTENTANDCONSOLE);

        SCREENSHOTMAP.put(LogType.PASS, PASS.andThen(TAKESCREENSHOT));
        SCREENSHOTMAP.put(LogType.FAIL, FAIL.andThen(TAKESCREENSHOT));
        SCREENSHOTMAP.put(LogType.SKIP, SKIP.andThen(TAKESCREENSHOT));
        SCREENSHOTMAP.put(LogType.INFO, INFO);
        SCREENSHOTMAP.put(LogType.CONSOLE, CONSOLE);
        SCREENSHOTMAP.put(LogType.EXTENTANDCONSOLE, EXTENTANDCONSOLE.andThen(TAKESCREENSHOT));
    }

    public static void log(LogType status, String message) {
        if (!PropertyFileRead.getPropValue(ConfigProperties.PASSEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes")) {
            MAP.getOrDefault(status, EXTENTANDCONSOLE).accept(message);
        } else {
            SCREENSHOTMAP.getOrDefault(status, EXTENTANDCONSOLE).accept(message);
        }
    }
}

/*
package com.framework.reports;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.framework.utils.Utilities;

import com.framework.enums.LogType;

import com.framework.utils.PropertyUtils;
import com.framework.utils.ScreenshotUtils;
//import com.pim.driver.*;

//import java.sql.DriverManager;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

public class FrameworkLogger{

    private FrameworkLogger(){}

    private static final Consumer<String> PASS = (message)->ExtentManager.getExtentTest().pass(message);
    private static final Consumer<String> FAIL = (message)->ExtentManager.getExtentTest().fail(message);
    private static final Consumer<String> SKIP = (message)->ExtentManager.getExtentTest().skip(message);
    private static final Consumer<String> INFO = (message)->ExtentManager.getExtentTest().info(message);
    private static final Consumer<String> CONSOLE = (message) -> System.out.println("INFO---->"+message);
    private static final Consumer<String> EXTENTANDCONSOLE = PASS.andThen(CONSOLE);
    private static final Consumer<String> TAKESCREENSHOT = (message)->{
        if(Utilities.getDriver()!=null){
        ExtentManager.getExtentTest().info("",
           MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build()) ;
    }};
    private static final Map<LogType,Consumer<String>> MAP = new EnumMap<>(LogType.class);
    private static final Map<LogType,Consumer<String>> SCREENSHOTMAP = new EnumMap<>(LogType.class);

    static {
        MAP.put(LogType.PASS, PASS);
        MAP.put(LogType.FAIL, FAIL.andThen(TAKESCREENSHOT));
        MAP.put(LogType.SKIP, SKIP);
        MAP.put(LogType.INFO, INFO);
        MAP.put(LogType.CONSOLE, CONSOLE);
        MAP.put(LogType.EXTENTANDCONSOLE, EXTENTANDCONSOLE);
        SCREENSHOTMAP.put(LogType.PASS, PASS.andThen(TAKESCREENSHOT));
        SCREENSHOTMAP.put(LogType.FAIL, FAIL.andThen(TAKESCREENSHOT));
        SCREENSHOTMAP.put(LogType.SKIP, SKIP.andThen(TAKESCREENSHOT));
        SCREENSHOTMAP.put(LogType.INFO, INFO);
        SCREENSHOTMAP.put(LogType.CONSOLE, CONSOLE);
        SCREENSHOTMAP.put(LogType.EXTENTANDCONSOLE, EXTENTANDCONSOLE.andThen(TAKESCREENSHOT));
    }

    public static void log(LogType status, String message) {
        
            
        if(!PropertyUtils.readPropertiesFiles("passedstepsscreenshots").equalsIgnoreCase("yes")) {
                MAP.getOrDefault(status,EXTENTANDCONSOLE).accept(message);
        }
        else{
            SCREENSHOTMAP.getOrDefault(status,EXTENTANDCONSOLE).accept(message);
        }

    }
}
*/
