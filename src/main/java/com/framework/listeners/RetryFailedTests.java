package com.framework.listeners;

 import org.testng.IRetryAnalyzer; import org.testng.ITestResult;

import com.framework.enums.ConfigProperties;
import com.framework.utils.PropertyFileRead;
  
 public class RetryFailedTests implements IRetryAnalyzer{
  
	 private int count=0;
	    private int retries = 5;

	    @Override
	    public boolean retry(ITestResult result) {
	        boolean value =false;
	        if(PropertyFileRead.getPropValue(ConfigProperties.RETRYFAILEDTESTS).equalsIgnoreCase("yes")) {
	            value = count<retries ;
	            count++;
	        }
	        return value;
	    }
 }
	 
	 
/*int count = 0 ; int maxtry = 1;
  
 @Override public boolean retry(ITestResult result) {
 
 if (count<maxtry) {
 
  count++; return true;
  
  }
  
  return false; }
  
  }
  */
 
