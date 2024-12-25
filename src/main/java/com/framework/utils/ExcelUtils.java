package com.framework.utils;


import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {
    public static Map<String, Map<String, String>> getTestDataFromExcel(String sheetName) {
        File excelFile = new File(System.getProperty("user.dir") + "\\resources\\Excel\\FrameWorkTest.xlsx");
        XSSFWorkbook workbook = null;
        Map<String, Map<String, String>> testDataMap = new HashMap<>();
        try {
            FileInputStream fisExcel = new FileInputStream(excelFile);
            workbook = new XSSFWorkbook(fisExcel);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("User Defined Message: Exception Associated With Excel File Caught");
        }

        XSSFSheet sheet = workbook.getSheet(sheetName);

        // Highlighting: Added check for sheet existence
        if (sheet == null) {
            throw new RuntimeException("Sheet with name '" + sheetName + "' not found in the Excel file");
        }

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        for (int i = 0; i < rows; i++) {
            XSSFRow row = sheet.getRow(i + 1);
            String testCaseName = row.getCell(0).getStringCellValue();
            Map<String, String> rowData = new HashMap<>();

            for (int j = 1; j < cols; j++) {
                XSSFCell cell = row.getCell(j);
                String columnName = sheet.getRow(0).getCell(j).getStringCellValue();
                cell.setCellType(CellType.STRING); // Set cell type to string explicitly
                String cellValue = cell.getStringCellValue();
                rowData.put(columnName, cellValue);
            }
            testDataMap.put(testCaseName, rowData);
        }

        return testDataMap;
    }
}





/*Below code is working fine for only two parameters. If third added 
 * giving Null Pointer Exception
 
public class ExcelUtils {
    public static Map<String, Map<String, String>> getTestDataFromExcel(String sheetName) {
        File excelFile = new File(System.getProperty("user.dir") + "\\resources\\Excel\\FrameWorkTest.xlsx");
        XSSFWorkbook workbook = null;
        Map<String, Map<String, String>> testDataMap = new HashMap<>();
        try {
            FileInputStream fisExcel = new FileInputStream(excelFile);
            workbook = new XSSFWorkbook(fisExcel);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("User Defined Message: Exception Associated With Excel File Caught");
        }

        XSSFSheet sheet = workbook.getSheet(sheetName);
        
        // Highlighting: Added check for sheet existence
        if (sheet == null) {
            throw new RuntimeException("Sheet with name '" + sheetName + "' not found in the Excel file");
        }

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        for (int i = 0; i < rows; i++) {
            XSSFRow row = sheet.getRow(i + 1);
            String testCaseName = row.getCell(0).getStringCellValue();
            Map<String, String> rowData = new HashMap<>();

            for (int j = 1; j < cols; j++) {
                XSSFCell cell = row.getCell(j);
                CellType cellType = cell.getCellType();
               switch (cellType) {
                 case STRING:
                        rowData.put(sheet.getRow(0).getCell(j).getStringCellValue(), cell.getStringCellValue());
                       break;
                    case NUMERIC:
                        rowData.put(sheet.getRow(0).getCell(j).getStringCellValue(),
                                Integer.toString((int) cell.getNumericCellValue()));
                        break;
                }
            }
            testDataMap.put(testCaseName, rowData);
       }
        return testDataMap;
                
    }
}
*/
    
             



/* Below code is working fine, i.e. it is reading data as per sheet Name
 * but Exception is NOT handled if sheet name which we passing is not available in Excelfile
package com.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;



public class ExcelUtils {
	public static Map<String, Map<String, String>> getTestDataFromExcel(String sheetName) {
	    File excelFile = new File(System.getProperty("user.dir") + "\\resources\\Excel\\FrameWorkTest.xlsx");
	    XSSFWorkbook workbook = null;
	    Map<String, Map<String, String>> testDataMap = new HashMap<>();
	    try {
	        FileInputStream fisExcel = new FileInputStream(excelFile);
	        workbook = new XSSFWorkbook(fisExcel);
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("User Defined Message: Exception Associated With Excel File Caught");
	    }

	    XSSFSheet sheet = workbook.getSheet(sheetName);
	    int rows = sheet.getLastRowNum();
	    int cols = sheet.getRow(0).getLastCellNum();

	    for (int i = 0; i < rows; i++) {
	        XSSFRow row = sheet.getRow(i + 1);
	        String testCaseName = row.getCell(0).getStringCellValue();
	        Map<String, String> rowData = new HashMap<>();

	        if (!testDataMap.containsKey(testCaseName)) {
	            for (int j = 1; j < cols; j++) {
	                XSSFCell cell = row.getCell(j);
	                CellType cellType = cell.getCellType();
	                switch (cellType) {
	                    case STRING:
	                        rowData.put(sheet.getRow(0).getCell(j).getStringCellValue(), cell.getStringCellValue());
	                        break;
	                    case NUMERIC:
	                        rowData.put(sheet.getRow(0).getCell(j).getStringCellValue(),
	                                Integer.toString((int) cell.getNumericCellValue()));
	                        break;
	                }
	            }
	            testDataMap.put(testCaseName, rowData);
	        }
	    }
	    return testDataMap;
	}

	*/
	
	
    /*
     * Below Code is working for Single TC: Common Reference FWP 001
     * public static Map<String, Map<String, String>> getTestDataFromExcel(String sheetName) {
     */
        /*File excelFile = new File(System.getProperty("user.dir") + "\\resources\\Excel\\FrameWorkTest.xlsx");
        XSSFWorkbook workbook = null;
        Map<String, Map<String, String>> testDataMap = new HashMap<>();
        try {
            FileInputStream fisExcel = new FileInputStream(excelFile);
            workbook = new XSSFWorkbook(fisExcel);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("User Defined Message: Exception Associated With Excel File Caught");
        }

        XSSFSheet sheet = workbook.getSheet(sheetName);
        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        for (int i = 0; i < rows; i++) {
            XSSFRow row = sheet.getRow(i + 1);
            String testCaseName = row.getCell(0).getStringCellValue();
            Map<String, String> rowData = new HashMap<>();

            for (int j = 1; j < cols; j++) {
                XSSFCell cell = row.getCell(j);
                CellType cellType = cell.getCellType();
                switch (cellType) {
                    case STRING:
                        rowData.put(sheet.getRow(0).getCell(j).getStringCellValue(), cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        rowData.put(sheet.getRow(0).getCell(j).getStringCellValue(),
                                Integer.toString((int) cell.getNumericCellValue()));
                        break;
                }
            }
            testDataMap.put(testCaseName, rowData);
        }
        return testDataMap;
    }
    
}*/
