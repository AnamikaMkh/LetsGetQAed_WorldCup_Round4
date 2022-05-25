package com.test.automation.LetGetQAed.GenericUtilities;


	import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class ExcelWrite {
	 

	    public static void writeData(String fileName, String sheetName,List<String> listToWrite) throws Exception    {
	    	String filePath = System.getProperty("user.dir")+File.separator +"TestOutputResults";
	    	System.out.println(filePath);
	    	   File file = new File(filePath + File.separator + fileName);	           
	           FileInputStream inputStream = new FileInputStream(file);
	        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
	 
	        Sheet sheet = workbook.getSheet(sheetName);
	             	        
	       Row row;       
	  
	        int rowid =0;
	       
	          for(String s: listToWrite) {
	        	  row = sheet.createRow(rowid++);
	        	  int cellid = 0;
	        	  for (String toWrite :s.split(";;")){
	        		  Cell cell = row.createCell(cellid++);
		                cell.setCellValue(toWrite);
	        	  }
	        	  
	          }
	  
	       
	        FileOutputStream out = new FileOutputStream(
	            new File(filePath + File.separator + fileName));
	  
	        workbook.write(out);
	        out.close();
	    }

			
	    public static void writeData2(String fileName, String sheetName,List<String> listToWrite) throws Exception    {
	    	String filePath = System.getProperty("user.dir")+File.separator +"TestOutputResults";
	    	System.out.println(filePath);
	    	   File file = new File(filePath + File.separator + fileName);	           
	           FileInputStream inputStream = new FileInputStream(file);
	        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
	 
	        Sheet sheet = workbook.getSheet(sheetName);
	             	        
	       Row row;       
	  
	        int rowid =0;
	       
	          for(String s: listToWrite) {
	        	  row = sheet.createRow(rowid++);
	        	  int cellid = 0;
	        	  
	        		  Cell cell = row.createCell(cellid++);
		                cell.setCellValue(s);
	        	  
	        	  
	          }
	  
	       
	        FileOutputStream out = new FileOutputStream(
	            new File(filePath + File.separator + fileName));
	  
	        workbook.write(out);
	        out.close();
	    }

			
			public static List<String> covertToList(Map<Integer,String> map) {
				
			
				List<String> list = new ArrayList<String>();
				
				for(Map.Entry<Integer,String> mapEntry : map.entrySet()) {
					String key =String.valueOf(mapEntry.getKey());
					String value =mapEntry.getValue();
					String valueList=key+";;"+value;
					list.add(valueList);
				}
			
				return list;
			}
		 
		}
	

