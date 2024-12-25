package com.framework.reports;

import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.framework.enums.ConfigProperties;
import com.framework.utils.PropertyFileRead;


public class ExtendReporterNG {
	
static ExtentReports extent;
	
	public static ExtentReports getReportObject() {
		
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Application URL", PropertyFileRead.getPropValue(ConfigProperties.URL));
		extent.setSystemInfo("Browser Name", PropertyFileRead.getPropValue(ConfigProperties.BROWSER));
		extent.setSystemInfo("User ID", PropertyFileRead.getPropValue(ConfigProperties.EmailID));
		extent.setSystemInfo("Password", PropertyFileRead.getPropValue(ConfigProperties.Password));
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		
		
		return extent;
	}

}
