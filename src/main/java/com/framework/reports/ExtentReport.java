
 //ExtentLogger, ExtentManager,ExtentReport, FrameworkLogger classes are taken 
 //from PIM Automation
 
package com.framework.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.framework.enums.CategoryType;
import com.framework.enums.Modules;

public class ExtentReport {

    private ExtentReport() {
    }

    private static ExtentReports extent;

    public static void initReports() {
        if (Objects.isNull(extent)) {
            extent = new ExtentReports();
            String path = System.getProperty("user.dir")+"\\reports\\index.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(path);
            extent.attachReporter(spark);
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("TutorialsNinja Test Report");
            spark.config().setReportName("TutorialsNinja Test Automation");

        }
    }

    public static void flushReports () {
        if (Objects.nonNull(extent)) {
            extent.flush();
        }
        ExtentManager.unload();
        try {
        	String extentReportFolderPath=System.getProperty("user.dir")+"\\reports";
            Desktop.getDesktop().browse(new File(extentReportFolderPath).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createTest(String testcasename) {
        ExtentManager.setExtentTest(extent.createTest(testcasename));
    }

    public static void addAuthors(Modules module) {
            ExtentManager.getExtentTest().assignAuthor(module.name());
    }

    public static void addCategories(CategoryType[] categories) {
        for(CategoryType temp:categories) {
            ExtentManager.getExtentTest().assignCategory(temp.toString());
        }
    }

        }



