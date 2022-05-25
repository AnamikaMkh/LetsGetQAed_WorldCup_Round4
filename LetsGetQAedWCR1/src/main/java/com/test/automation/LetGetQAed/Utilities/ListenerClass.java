package com.test.automation.LetGetQAed.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.test.automation.LetGetQAed.GenericUtilities.Read_Config;
import com.test.automation.LetGetQAed.TestBase.TestBase;

//import com.test.automation.VerizonDigitalSignage.TestBase.TestBase;

public class ListenerClass extends TestBase implements ITestListener {
	
	/*public ListenerClass(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}*/
	
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		System.out.println(result.getName()+" test case started");	
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		 System.out.println("The name of the testcase passed is :"+result.getName());	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		 System.out.println("The name of the testcase failed is :"+result.getName());
		 if(!(driver.equals(null)))
		 {
				 
			 System.out.println("I am in fail method");
			 		driver.get(Read_Config.readConfig("url"));
			 		waitForLoad(driver);
				 }
		 else{
			 System.out.println("check this:"+driver);
		 }
		 
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		 System.out.println("The name of the testcase Skipped is :"+result.getName());	
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
