package com.test.automation.LetGetQAed.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.test.automation.LetGetQAed.GenericUtilities.Excel_Reader;
import com.test.automation.LetGetQAed.GenericUtilities.SMSReader;
import com.test.automation.LetGetQAed.TestBase.TestBase;

public class GenericLibrary extends TestBase {
	public static final Logger log = Logger.getLogger(GenericLibrary.class.getName());

	static Excel_Reader excel;
	
	static SMSReader smsReader= new SMSReader();
	
	WebDriverWait wait;
	

	public GenericLibrary(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Code to read otp message from twilio 
	public static String OTPReader(){
		
		String OTPfinal =SMSReader.getMessage();
		return OTPfinal;
	}

	// Function to get Data from excel sheet , so we also need to make object of Excel here
	public static String[][] getData(String excelName, String sheetName) {
		// System.out.println("Excel entry");
		String path = System.getProperty("user.dir") + "/TestInput/" + excelName;
		// this path will be fixed as we have made a common TestData sheet but do not hard code
		// System.out.println("path located");
		excel = new Excel_Reader(path);// Reference of the excel object
		String[][] data = excel.getDataFromSheet(sheetName, excelName);
		//Returns object of a 2D array	
		return data;
	}
	
	public static String getCellDataFromExcel(String excelName, String sheetName1, String colName1, int rowNum1)
	{
		String path = System.getProperty("user.dir") + "/TestInput/" + excelName;
		// this path will be fixed as we have made a common TestData sheet but do not hard code
		excel = new Excel_Reader(path);
		
		String cellData= excel.getCellData(sheetName1, colName1, rowNum1);
				return cellData;
	}
	
	public static int getRowCountFromExcel(String excelName, String sheetName1)
	{
		String path = System.getProperty("user.dir") + "/TestInput/" + excelName;
		excel = new Excel_Reader(path);
		
		int countOfRows=excel.getRowCount(sheetName1);
		return countOfRows;
	}
	
	public void enterValueInInputbox(WebElement InputBox, String value)
	{
		waitForElement(30, InputBox);
		InputBox.sendKeys(value);
		log.info("Value entered : "+value + " to input box :"+ InputBox.toString());
		test.get().info("Value entered : "+value + " to input box :"+ InputBox.toString());
	}
	
	public void clickOn(WebElement OptionToClick)
	{
		waitForElement(30, OptionToClick);
		OptionToClick.click();
		log.info("Clicked on : "+OptionToClick);
	}
	
	


	public void getSpecificScreenShot(String name) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
					+ "/TestResult/Screenshots/";// dont miss to add / after screenshot, coz screenshots have to be saved inside the screenshot folder
			File destFile = new File(
					(String) reportDirectory + name + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link screenshot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath()
					+ "' height='100' width='100'/> </a>");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void renameFileInDirectory(String oldFilePath,String newFilePath)
	{
		File oldfile =new File(oldFilePath);
		log.info("Filepath = " + oldFilePath);
        File newfile =new File(newFilePath);
        log.info("NewFile Path = " +newFilePath);

        if(oldfile.renameTo(newfile)){
            System.out.println("File renamed " );
        }else{
            System.out.println("Sorry! the file can't be renamed");
        }
	}
	
	

	

	
}
