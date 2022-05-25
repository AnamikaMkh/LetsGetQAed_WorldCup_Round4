package com.test.automation.LetGetQAed.UIActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.LetGetQAed.TestBase.TestBase;

public class TestAutoPracticeObjects  extends TestBase {
	
	public static final Logger log = Logger.getLogger(TestAutoPracticeObjects.class.getName());
	
	@FindBy(xpath="//input[@id=\"ms-searchux-input-3\"]")
	public WebElement SearchBox;

	@FindBy(xpath= "//div[text()='Test Automation Practice']")
	public WebElement TestAutoPractice;
	
	

	
	
	
	
	
	public TestAutoPracticeObjects(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void searchTPACommunity() throws InterruptedException{
		waitForLoad(driver);
		TestAutoPractice.click();

		}
		
	
		
		

	
	
	
	
}
