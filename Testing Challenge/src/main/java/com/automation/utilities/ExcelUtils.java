package com.automation.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	static Logger log = Logger.getLogger(ExcelUtils.class.getName());
    private static XSSFWorkbook excelWBook;
    private static XSSFSheet excelWSheet;
    private static XSSFRow row;
    private static XSSFCell cell;

    // This method opens the excel
    public void openExcel(String WBookName) {

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream( "./testdata/" + WBookName + ".xlsx");
            excelWBook = new XSSFWorkbook(inputStream);
            
            log.info("Excel file: "+WBookName + " loaded successfully");
            
        } catch (IOException e) {
        	log.fatal("Unable to load excel file: "+WBookName);
            e.printStackTrace();
        }
    }
    
    //This method loads the specified sheet in the excel
    public void loadSheet(String sheetName) {
        excelWSheet = excelWBook.getSheet(sheetName);
        if (excelWSheet != null)
        	log.info("Sheet: " + sheetName + " loaded successfully");
        else
        	log.fatal("Failed to load sheet: " + sheetName);
    }
    
    public void closeWorkBook() {
    	try {
    		excelWBook.close();
    		
    	}catch(Exception e) {
    		log.fatal("Failed to close workbook", e);
    		e.printStackTrace();
    	}
    }
    
    public String getCellData(int rowNum, int colNum) {
    	try {
        	row = excelWSheet.getRow(rowNum);
        	cell = row.getCell(colNum);
       
        	if(cell.getCellType()==CellType.STRING) {
        		
        	  String val = cell.getStringCellValue();
        	  return val;
        	  
        	}else if(cell.getCellType()==CellType.NUMERIC){
        		
        		Double val = cell.getNumericCellValue();  
        		int newVal = (int)Math.round(val);
        		String celVal = String.valueOf(newVal);
        		return celVal;        		
        	}
        	return "";
    	}catch(Exception e) {
    		e.printStackTrace();
    		return "Row or Col does not exist in WorkSheet";
    	}
    }    
}    
    
	

