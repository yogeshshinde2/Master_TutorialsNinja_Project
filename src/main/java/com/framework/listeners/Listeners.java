package com.framework.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.framework.reports.ExtendReporterNG;
import com.framework.utils.Utilities;

public class Listeners implements ITestListener {
	
	ExtentReports extent = ExtendReporterNG.getReportObject();
	
	ExtentTest extentTest;
	
	//ExtentTest test;
	
	ThreadLocal<ExtentTest> extentTest1 = new ThreadLocal<ExtentTest>();

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Execution of Project Tests Started");
		extent= ExtendReporterNG.getReportObject();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName + ": Started Execution");
		ITestListener.super.onTestStart(result);
		//test=extent.createTest(result.getMethod().getMethodName());
		extentTest =extent.createTest(testName);
		extentTest.log(Status.INFO, testName + " started executing");
		
		//extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName + ": Passed the Test");
		ITestListener.super.onTestStart(result);
		extentTest.log(Status.PASS,testName + " Test passed");
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName + ": Failed the Test");	
		ITestListener.super.onTestStart(result);
		extentTest.fail(result.getThrowable());
		System.out.println(result.getThrowable());
		//screenshot
		
		WebDriver driver=null;
		String testMethodName = result.getMethod().getMethodName();
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			
		}
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		try {
			
			extentTest.addScreenCaptureFromPath(Utilities.getscreenshotpath(testMethodName), result.getMethod().getMethodName());
			Utilities.getscreenshotpath(testMethodName);
			
		}
		
		catch(IOException e) {
			
			e.printStackTrace();
		}
	}
/*
	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
        System.out.println(testName + ": Failed the Test");
        ITestListener.super.onTestStart(result);
        extentTest.fail(result.getThrowable());
        System.out.println(result.getThrowable());

        // Obtain WebDriver instance from Utilities class
        WebDriver driver = Utilities.getDriver();
        
        String testMethodName = result.getMethod().getMethodName();

        try {
            extentTest.addScreenCaptureFromPath(Utilities.getscreenshotpath(testMethodName), result.getMethod().getMethodName());
            Utilities.getscreenshotpath(testMethodName); // This line doesn't seem necessary, you may remove it
        } catch(IOException e) {
            e.printStackTrace();
        }
    	}
*/
	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName + ": Test got skipped");	
		System.out.println(result.getThrowable());
		ITestListener.super.onTestStart(result);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName + "Test Skipped");
		
	}


	@Override
	public void onFinish(ITestContext context) {
		
		System.out.println("Execution of Project Tests Finished");
		extent.flush();
		String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\index.html";
		File extentreport=new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentreport.toURI());
		}
		
		catch (IOException e){
			
			e.printStackTrace();
		}
	}

}

