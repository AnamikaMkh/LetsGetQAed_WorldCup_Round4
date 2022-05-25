package com.test.automation.LetGetQAed.TestCases;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.test.automation.LetGetQAed.GenericUtilities.ExcelWrite;
import com.test.automation.LetGetQAed.TestBase.TestBase;

import com.test.automation.LetGetQAed.UIActions.TestObjects;
import com.test.automation.LetGetQAed.UIActions.Yammer;

public class YammerTest extends TestBase {

	public static final Logger log = Logger.getLogger(YammerTest.class.getName());

	
	TestObjects testObjects;
	Yammer yammer;
	
	SoftAssert softAssert = new SoftAssert();

	@Parameters({ "browser" })

	@BeforeClass
	public void setup(String browser) throws IOException, InterruptedException {
		init(browser);
		
		yammer = new Yammer(driver);
		testObjects = new TestObjects(driver);
	}
	

	@Test
	public void oneTest() throws Exception {
		System.out.println("The thread ID for chrome is "+ Thread.currentThread().getId());
		log.info("---Test Logs---");
		test.get().info("---Test Logs----");
		Thread.sleep(60000);
		yammer.clickCommunities();
		yammer.clickAllCommunities();
		Thread.sleep(7000);
		Map<Integer,String> map= yammer.getGroupNameAndMemberCount();
		map=yammer.sortMap(map);
		List<String> list =ExcelWrite.covertToList(map);
		ExcelWrite.writeData("Yammer.xlsx", "Community Data", list);
	}

	
}
