package com.test.automation.LetGetQAed.UIActions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.LetGetQAed.TestBase.TestBase;

public class TestObjects  extends TestBase {
	
	public static final Logger log = Logger.getLogger(TestObjects.class.getName());
	
	
	@FindBy(xpath="//a[@id=\"nav-logo-sprites\"]")
	public WebElement AmazonLogo;
	
	
	public TestObjects(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}	
	
	
	
	
}
