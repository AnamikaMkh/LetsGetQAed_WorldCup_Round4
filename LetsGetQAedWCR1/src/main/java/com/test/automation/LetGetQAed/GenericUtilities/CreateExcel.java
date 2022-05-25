package com.test.automation.LetGetQAed.GenericUtilities;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateExcel {
	public static void main(String args[]) throws IOException
	 
	{
	 
	//To create a new WorkBook with xlsx extension
	 
	Workbook wb = new XSSFWorkbook();
	String excelName= "Yammer";
	String path = System.getProperty("user.dir") + "/TestOutputResults/" + excelName;
	 
	FileOutputStream fileOut = new FileOutputStream(path);
	 
	wb.write(fileOut);
	 
	fileOut.close();
	 
	
	
	 
	}

}
