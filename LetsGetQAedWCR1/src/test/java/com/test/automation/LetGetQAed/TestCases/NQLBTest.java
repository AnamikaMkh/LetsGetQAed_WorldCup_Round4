package com.test.automation.LetGetQAed.TestCases;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.test.automation.LetGetQAed.GenericUtilities.ExcelWrite;
import com.test.automation.LetGetQAed.TestBase.TestBase;
import com.test.automation.LetGetQAed.UIActions.NQLBObjects;
import com.test.automation.LetGetQAed.UIActions.TestObjects;

public class NQLBTest extends TestBase {

	public static final Logger log = Logger.getLogger(NQLBTest.class.getName());

	NQLBObjects nqlbObjects;
	TestObjects testObjects;
	
	
	SoftAssert softAssert = new SoftAssert();

	@Parameters({ "browser" })

	@BeforeClass
	public void setup(String browser) throws IOException, InterruptedException {
		init(browser);
		
		nqlbObjects = new NQLBObjects(driver);
		testObjects = new TestObjects(driver);
	}
	

	@Test
	public void NQLBTest() throws Exception {
		System.out.println("The thread ID for chrome is "+ Thread.currentThread().getId());
		log.info("---Test Logs---");
		
		Thread.sleep(60000);
		waitForLoad(driver);
		System.out.println("one");
				nqlbObjects.searchNQLBCommunity();
			waitForLoad(driver);
			nqlbObjects.memberCount.click();
			waitForLoad(driver);
			List<String > list =nqlbObjects.getFirst20NameEmailList();
			ExcelWrite.writeData("Yammer.xlsx", "NQLB", list);
			
		//Assert.assertTrue(testObjects.AmazonLogo.isDisplayed(), "Logo not displayed");
	}

	
}
