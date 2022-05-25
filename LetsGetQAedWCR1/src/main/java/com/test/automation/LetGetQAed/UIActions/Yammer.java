package com.test.automation.LetGetQAed.UIActions;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.automation.LetGetQAed.TestBase.TestBase;


public class Yammer extends TestBase {

	public static final Logger log = Logger.getLogger(Yammer.class.getName());
	
	
	@FindBy(xpath="//div[@id=\"flight_list_item_3_RKEY:57f36bfe-f754-4077-994c-c79f432cf8fc:30_0\"]//following-sibling::div[@class=\"priceSection\"]//p")
	public WebElement firstFlightPrice;
	
	@FindBy(xpath="//div[text()='Communities']")
	public WebElement communities_tab;
	
	public Yammer(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	public void clickCommunities() {
		log.info("Method call : clickCommunities");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(communities_tab));
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(communities_tab));
		communities_tab.click();
		waitForLoad(driver);
	}

			
			public void clickAllCommunities() {
				log.info("Method call : clickAllCommunities");
				WebElement allCommunities =driver.findElement(By.xpath("(//span[text()='All'])[1]"));
				new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(allCommunities));
				new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(allCommunities));
				allCommunities.click();
				waitForLoad(driver);
			}
			
			public Map<Integer, String> getGroupNameAndMemberCount() {
				log.info("Method call : clickAllCommunities");
				List <WebElement> allCommunities =driver.findElements(By.xpath("//ul[contains(@class,'gridList')]/li[contains(@class,'gridListItem')]"));
				List <WebElement> allCommunitiesGroupName =driver.findElements(By.xpath("//ul[contains(@class,'gridList')]/li[contains(@class,'gridListItem')]//span[contains(@class,'groupName')]"));
				List <WebElement> allCommunitiesMember =driver.findElements(By.xpath("//ul[contains(@class,'gridList')]/li[contains(@class,'gridListItem')]//span[contains(@class,'text-318')]"));
				waitForLoad(driver);
				//new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(allCommunitiesMember.get(0)));
				HashMap<Integer, String> map = new HashMap();
				int size =allCommunitiesGroupName.size();
				System.out.println(size);
				for(int i=0;i<size;i++) {
					
					String groupName =allCommunitiesGroupName.get(i).getText();
					String groupMembersText = driver.findElement(By.xpath("//ul[contains(@class,'gridList')]/li[contains(@class,'gridListItem')]//span[contains(text(),'"+groupName+"')]/ancestor::div[contains(@class,'body')]/div[2]//span")).getText();
							groupMembersText=groupMembersText.replace(",", "");
							int groupMembers = Integer.parseInt(groupMembersText);
							//Integer.parseInt(driver.findElement(By.xpath("//ul[contains(@class,'gridList')]/li[contains(@class,'gridListItem')]//span[contains(text(),'"+groupName+"')]/ancestor::div[contains(@class,'body')]/div[2]//span")).getText());
					System.out.println(groupName);
					map.put(groupMembers, groupName);
				}
				return map;
			}
			
			public Map<Integer, String> sortMap(Map<Integer, String> map){
				
				Map<Integer,String> sortedMap = new TreeMap<Integer,String>(map);
				System.out.println(sortedMap);
				return sortedMap;
			}
			
}
