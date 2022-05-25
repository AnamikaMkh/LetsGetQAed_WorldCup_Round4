package com.test.automation.LetGetQAed.GenericUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {
    static Sheet sheet;
    static Workbook workbook = null;

    public static void readExcel( String fileName, String sheetName) throws IOException, InterruptedException {
    	int rowCount;
    	String filePath = System.getProperty("user.dir")+ File.separator+"TestInput";
        File file = new File(filePath + File.separator + fileName);
        
        FileInputStream inputStream = new FileInputStream(file);
        String fileExtensionName = fileName.substring(fileName.indexOf("."));

    
        if (fileExtensionName.equals(".xlsx")) {
            System.out.println("in xlsx");
            workbook = new XSSFWorkbook(inputStream);

        }
        else if (fileExtensionName.equals(".xls")) {
          
            workbook = new HSSFWorkbook(inputStream);
        }

        sheet = workbook.getSheet(sheetName);
       
        List<String>list=new ArrayList();
        // Find number of rows in excel file
        rowCount = (sheet.getLastRowNum()) - (sheet.getFirstRowNum());
        
        for (int i = 0; i < rowCount; i++) {
            Thread.sleep(1000);
            Row row = sheet.getRow(i);
            String val="";
            for (int j = 0; j < row.getLastCellNum(); j++) {
            	if(j!=0) {
            		val=val +";;";
            	}
            	DataFormatter formatter = new DataFormatter();
            	val = val +formatter.formatCellValue(row.getCell(j));
                           
            }
            list.add(val);
            val="";
            System.out.println();
        }
        
        for(String s: list) {
        	System.out.println(s);
        }
    }



    public static void main(String[] args) throws IOException, InterruptedException {
    	readExcel("JavaBooks.xlsx","Sheet1");
	}
}