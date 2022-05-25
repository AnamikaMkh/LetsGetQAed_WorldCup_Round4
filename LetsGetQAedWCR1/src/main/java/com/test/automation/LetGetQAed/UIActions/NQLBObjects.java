package com.test.automation.LetGetQAed.UIActions;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.automation.LetGetQAed.TestBase.TestBase;

public class NQLBObjects  extends TestBase {
	
	public static final Logger log = Logger.getLogger(NQLBObjects.class.getName());
	
	@FindBy(xpath="//input[@id=\"ms-searchux-input-3\"]")
	public WebElement SearchBox;

	@FindBy(xpath= "//div[text()='NQLB - No QA Left Behind']")
	public WebElement NQLBCommunity;
	
	@FindBy(xpath="//span[text()=\"Members\"]/parent::h2/following-sibling::span//button")
	public WebElement memberCount;

	
	
	
	
	
	public NQLBObjects(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void searchNQLBCommunity() throws InterruptedException{
		waitForLoad(driver);
		NQLBCommunity.click();

		}
	
	public List<String> getFirst20NameEmailList() {
		List<WebElement> el = driver.findElements(By.xpath("//ul[contains(@class,'SidebarList layoutList')]//li[contains(@class,'layoutListItem')]//span[@class='undefined lpc-hoverTarget']/span"));
		List<WebElement> elEmail = driver.findElements(By.xpath("//ul[contains(@class,'SidebarList layoutList')]//li[contains(@class,'layoutListItem')]//div[contains(@class,'mediaObjectMetadata')]/div"));
		List<String> list = new ArrayList<String>();
		for(int i =0; i<20;i++) {
		String valueName = el.get(i).getText();
		String valueEmail="";
		try {
		valueEmail = elEmail.get(i).getText().replace("\n", "");
		}catch(Exception e){

		}
		String value = valueName+""+valueEmail;
		list.add(value);

		}
		return list;
		}
		
	
		
		

	
	
	
	
}
