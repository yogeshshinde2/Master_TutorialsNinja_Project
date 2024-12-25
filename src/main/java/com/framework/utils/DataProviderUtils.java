package com.framework.utils;

import java.lang.reflect.Method;
import java.util.Map;
import org.testng.annotations.DataProvider;

import com.framework.annotations.TestDataSheet;




public class DataProviderUtils {
    
    @DataProvider(name = "validCredentialSupplier")
    public Object[][] supplyTestData(Method method) {
        TestDataSheet testDataSheetAnnotation = method.getAnnotation(TestDataSheet.class);
        String sheetName = testDataSheetAnnotation.sheetName();
        Map<String, Map<String, String>> testDataMap = ExcelUtils.getTestDataFromExcel(sheetName);
        String testCaseName = method.getName();
        Map<String, String> testData = testDataMap.get(testCaseName);

        if (testData != null) {
            // Convert Map to Object[][] format
            Object[][] testDataArray = new Object[1][1];
            testDataArray[0][0] = testData;
            return testDataArray;
        } else {
            System.out.println("Test data not found for test case: " + testCaseName);
            return new Object[0][0]; // Return empty array if test data is not found
        }
    }
}


//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
/* Below code works for fetching two parameters from Excel.
 *  For Third it is giving Null Pointer Exception
 
public class DataProviderUtils {
    @DataProvider(name = "validCredentialSupplier")
    public Object[][] supplyTestData(Method method) {
        TestDataSheet testDataSheetAnnotation = method.getAnnotation(TestDataSheet.class);
        String sheetName = testDataSheetAnnotation.sheetName();
        Map<String, Map<String, String>> testDataMap = ExcelUtils.getTestDataFromExcel(sheetName);
        String testCaseName = method.getName();
        Map<String, String> testData = testDataMap.get(testCaseName);

        if (testData != null) {
            return new Object[][]{{testData}};
        } else {
            System.out.println("Test data not found for test case: " + testCaseName);
            return new Object[0][0]; // Return empty array if test data is not found
        }
    }
}
*/

