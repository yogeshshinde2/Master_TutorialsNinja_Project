package com.framework.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;



public class AnnotationTransformer implements IAnnotationTransformer {

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// annotation.setDataProvider("getData");
		// annotation.setDataProviderClass(DataProviderUtils.class);
		annotation.setRetryAnalyzer(RetryFailedTests.class);
		
															
	}

}

/*
 * This code works: Code REPORT_SUC 
 * import java.lang.reflect.Constructor; import
 * java.lang.reflect.Method;
 * 
 * import org.testng.IAnnotationTransformer; import
 * org.testng.annotations.ITestAnnotation; import com.framework.listeners.Retry;
 * public class TestRetryAnalyzerListener implements IAnnotationTransformer {
 * 
 * @Override public void transform(ITestAnnotation annotation, Class testClass,
 * Constructor testConstructor, Method testMethod){
 * 
 * //I need t tell this IAnnotationTransformer that I want to retry my test case
 * and my retry logic is // present in Retry.java Class
 * 
 * annotation.setRetryAnalyzer(Retry.class); }
 * 
 * }
 */
