package com.test.automation.LetGetQAed.TestCases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.test.automation.LetGetQAed.GenericUtilities.ExcelWrite;
import com.test.automation.LetGetQAed.TestBase.TestBase;
import com.test.automation.LetGetQAed.UIActions.NQLBObjects;
import com.test.automation.LetGetQAed.UIActions.TestAutoPracticeObjects;
import com.test.automation.LetGetQAed.UIActions.TestObjects;

import io.restassured.RestAssured;

public class TestAutomationPracticeTest extends TestBase {

	public static final Logger log = Logger.getLogger(TestAutomationPracticeTest.class.getName());

	NQLBObjects nqlbObjects;
	TestObjects testObjects;
	TestAutoPracticeObjects testAutoPracticeObjects;
	
	SoftAssert softAssert = new SoftAssert();

	@Parameters({ "browser" })

	@BeforeClass
	public void setup(String browser) throws IOException, InterruptedException {
		init(browser);
		
		nqlbObjects = new NQLBObjects(driver);
		testObjects = new TestObjects(driver);
		testAutoPracticeObjects=new TestAutoPracticeObjects(driver);
	}
	

	@Test
	public void NQLBTest() throws InterruptedException {
		System.out.println("The thread ID for chrome is "+ Thread.currentThread().getId());
		log.info("---Test Logs---");
		
		Thread.sleep(60000);
		waitForLoad(driver);
		System.out.println("one");
				testAutoPracticeObjects.searchTPACommunity();
			waitForLoad(driver);
			
			List<WebElement> likesList = driver.findElements(By.xpath("//div[contains(@class,'ms-TooltipHost')][contains(text(),'others')]"));
			for(WebElement likes : likesList){
			    System.out.println(likes.getText());
			}

			List<WebElement> commentedList = driver.findElements(By.xpath("//button/span[contains(text(),'previous comments')]"));
			for(WebElement commented : commentedList){
			    System.out.println(commented.getText());
			}
			//ExcelWrite.writeData2("Yammer.xlsx", "NQLB", likesList);
			
		
	}

	
}
