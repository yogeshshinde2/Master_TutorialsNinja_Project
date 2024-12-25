/*
//ListenerClass,MethodInterceptor are taken from PIM Automation for Reporting
package com.framework.listeners;
 
import com.framework.annotations.FrameWorkPracticeReportAnnotation;
import com.framework.enums.LogType;
import com.framework.reports.ExtentReport;
import static com.framework.reports.FrameworkLogger.log;
import org.testng.*;
import java.util.Arrays;

public class ListenerClass implements ITestListener, ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
		ExtentReport.initReports();
	}

	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.flushReports();
	}

	@Override
	public void onTestStart(ITestResult result) {

		ExtentReport.createTest(result.getMethod().getDescription());
		ExtentReport.addAuthors(result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameWorkPracticeReportAnnotation.class).module());
		ExtentReport.addCategories(result.getMethod().getConstructorOrMethod().getMethod()
				.getAnnotation(FrameWorkPracticeReportAnnotation.class).category());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		log(LogType.PASS, result.getMethod().getMethodName() + " is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		log(LogType.FAIL, result.getMethod().getMethodName() + " is failed");
		log(LogType.FAIL, result.getThrowable().toString());
		log(LogType.FAIL, Arrays.toString(result.getThrowable().getStackTrace()));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		log(LogType.SKIP, result.getMethod().getMethodName() + " is skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		 //For now, we are not using this.
		 
	}

	@Override
	public void onStart(ITestContext context) {
		
		 //We are having just one test in our suite.
		 
	}

	@Override
	public void onFinish(ITestContext context) {
		
		 //We are having just one test in our suite.
		 

	}

}

*/

