package com.test.automation.LetGetQAed.TestBase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.test.automation.LetGetQAed.GenericUtilities.ExtentManager;
import com.test.automation.LetGetQAed.GenericUtilities.Read_Config;

public class TestBase {

	public static final Logger log = Logger.getLogger(TestBase.class.getName());

	public WebDriver driver;
	WebDriverWait wait;
	public Properties OR;

	private static String directory = "./TestResult/Reports/";
	private static String oldReportsDirectory = "./oldreports/";
	private static String subDirectory = "screenshots/";
	private static String filePath = "";
	private static String filePathExtent = "";
	private static String qualifiedMethodName = "";
	private static String className = "";
	
	public static ExtentReports extent;
    public static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    
	
	
	 public static final String USERNAME = "shagunjain1";
	  public static final String AUTOMATE_KEY = "P2PknHr5b1zjCSYWvyP2";
	  public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	
	
		@BeforeSuite
		public void beforeSuite() {
	
			createDirectory();
			extent = ExtentManager.createInstance(directory+"extent.html");
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
			extent.attachReporter(htmlReporter);
			//extent.setSystemInfo("Browser", Read_Config.readConfig("browser"));
			//extent.setSystemInfo("Environment URL", Read_Config.readConfig("url"));
		}

		@Parameters({"browser"})
		@BeforeTest
		public void beforeTestSystemInfo(String browser)
		{
			extent.setSystemInfo("Browser",browser); 
		}
		
	    @BeforeMethod
	    public synchronized void beforeMethod(Method method, ITestResult result) {
	    	 ExtentTest child =  parentTest.get().createNode(method.getName());
	    	 qualifiedMethodName =  result.getMethod().getMethodName();
	        test.set(child);
  
	        log.info("in test base------------");
	    }
	    
	    @AfterMethod
	    public synchronized void afterMethod(ITestResult result) {
	        if (result.getStatus() == ITestResult.FAILURE)
	        {
	           test.get().fail(result.getThrowable());
	       	captureScreenShot();
			try {
				test.get().addScreenCaptureFromPath(filePathExtent);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//To Navigate back to dashboard screen in case of failure and user is on any other screen
			
			if(!(driver.equals(null)) && !(driver.getCurrentUrl().equals(Read_Config.readConfig("url"))))
			 {
					 	driver.get(Read_Config.readConfig("url"));
				 		waitForLoad(driver);
			 }
			
			
	        }
	        
	        
	        else if (result.getStatus() == ITestResult.SKIP)
	             test.get().skip(result.getThrowable());
	        else
	            test.get().pass("Test passed");

	        extent.flush();

	    }
	    
	    
	    public void captureScreenShot() {
			synchronized (this) {
				try {
					DateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy_hh_mm_ssaa");
					Date date = new Date();
					String dateName = dateFormat.format(date);
					filePathExtent = subDirectory + qualifiedMethodName + "_" + dateName + ".png";
					filePath = directory + filePathExtent;
					File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(f, new File(filePath));
					System.out.println("================== Took Screenshot through Selenium ==================");
				} catch (Exception e) {
					System.out.println("================== Some Exception occurred while getting screenshot ==================");
				}
			}
	    }
	    
	    public void createDirectory() {
			try {
				File file = new File(directory + subDirectory);
				FileUtils.forceMkdir(file);
				FileUtils.cleanDirectory(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
	    }
			
	    public static void copyReportToOld() {
			File oldDirectory = new File(directory);
			DateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy_hh_mm_ssaa");
			String dateName = dateFormat.format(new Date());
			File newDirectory = new File(oldReportsDirectory + dateName);
			try {
				FileUtils.copyDirectory(oldDirectory, newDirectory);
			} catch (IOException e) {
				e.printStackTrace();
			}	
	    }
	    
		public void init(String browser) throws IOException {
			//extent.setSystemInfo("Browser",browser); 
			String clatemp=getClass().getName().substring(40)+" | "+browser;
			//System.out.println("clatemp= "+ clatemp);
			ExtentTest parent = extent.createTest(clatemp);
	        parentTest.set(parent);
	        selectBrowser(browser);
			//selectBrowser(Read_Config.readConfig("browser"));
			//System.out.println("browser is : "+Read_Config.readConfig("browser"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			getURL(Read_Config.readConfig("url"));
			PropertyConfigurator.configure(System.getProperty("user.dir") + "/Properties/log4j.properties");
		}
 


	public void selectBrowser(String browser) throws MalformedURLException {
		// -----browser based condition
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/Drivers/geckodriver.exe");
			//System.setProperty("webdriver.gecko.driver","C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			log.info("creating object of " + browser);
			driver = new FirefoxDriver();
			driver.manage().deleteAllCookies();
		}

		else if (browser.equalsIgnoreCase("IE")) {
			System.out.println(System.getProperty("user.dir"));
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/Drivers/IEDriverServer.exe");
			log.info("creating object of " + browser);
			driver = new InternetExplorerDriver();
			driver.manage().deleteAllCookies();

		} else if (browser.equalsIgnoreCase("chrome")) {
			System.out.println(System.getProperty("user.dir"));
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
			log.info("creating object of " + browser);
			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();
		}
		
		
		else if(browser.equalsIgnoreCase("BS_safari"))
		{
			DesiredCapabilities caps = new DesiredCapabilities();
			  
			caps.setCapability("browser", "Safari");
		    caps.setCapability("browser_version", "11.0");
		    caps.setCapability("os", "OS X");
		    caps.setCapability("os_version", "High Sierra");
		    caps.setCapability("resolution", "1920x1080");
		  	caps.setCapability("browserstack.debug", true );
		  	caps.setCapability("browserstack.console", "errors");
		  	caps.setCapability("browserstack.networkLogs", true);
		  	
		  	URL browserStackURL=new URL(URL);
		  	
		  	driver= new RemoteWebDriver(browserStackURL, caps);
		}
		
		else if(browser.equalsIgnoreCase("BS_chrome"))
		{
			DesiredCapabilities caps = new DesiredCapabilities();
			
		    caps.setCapability("browser", "Chrome");
		    caps.setCapability("browser_version", "62.0");
		    caps.setCapability("os", "Windows");
		    caps.setCapability("os_version", "10");
		    caps.setCapability("resolution", "1920x1080");
		    caps.setCapability("browserstack.debug", true );
		  	caps.setCapability("browserstack.console", "errors");
		  	caps.setCapability("browserstack.networkLogs", true);
		  	
		  	URL browserStackURL=new URL(URL);
		  	
		  	driver= new RemoteWebDriver(browserStackURL, caps);
		    
		}
		
		else if(browser.equalsIgnoreCase("BS_firefox"))
		{
			DesiredCapabilities caps = new DesiredCapabilities();
			
			caps.setCapability("browser", "Firefox");
		    caps.setCapability("browser_version", "59.0");
		    caps.setCapability("os", "Windows");
		    caps.setCapability("os_version", "10");
		    caps.setCapability("resolution", "1920x1080");
		    caps.setCapability("browserstack.debug", true );
		  	caps.setCapability("browserstack.console", "errors");
		  	caps.setCapability("browserstack.networkLogs", true);
		  	
		  	URL browserStackURL=new URL(URL);
		  	
		  	driver= new RemoteWebDriver(browserStackURL, caps);
		    
		}
		
		else if(browser.equalsIgnoreCase("BS_IE"))
		{
			DesiredCapabilities caps = new DesiredCapabilities();
			
			caps.setCapability("browser", "IE");
		    caps.setCapability("browser_version", "11.0");
		    caps.setCapability("os", "Windows");
		    caps.setCapability("os_version", "10");
		    caps.setCapability("resolution", "1920x1080");
		    caps.setCapability("browserstack.debug", true );
		  	caps.setCapability("browserstack.console", "errors");
		  	caps.setCapability("browserstack.networkLogs", true);
		  	
		  	URL browserStackURL=new URL(URL);
		  	
		  	driver= new RemoteWebDriver(browserStackURL, caps);
		    
		}
		
		

	}

	public void getURL(String url) {
		log.info("Navigating to : " + url);
		driver.get(url);
	}

	// generic wait function so that it is available in all classes
	public void waitForElement(int timeOutInSeconds, WebElement element)
	{
		wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToBeClickable(int timeOutInSeconds, WebElement element)
	{
		wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}
	
	
	public void waitForLoad(WebDriver driver) 
	{
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(pageLoadCondition);
    }
	
	

	public void navigateBackToPreviousScreen() {
		driver.navigate().back();
		log.info("Navigated to previous screen");
	}
	


	// extent
	public void closeBrowser() {
		driver.quit();
		log.info("Browser closed");
	}
	
	//**********getting timestamp as a string
	public String currentTimestamp()
	{
	DateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy_hh_mm_ssaa");
	Date date = new Date();
	String dateName = dateFormat.format(date);
	return dateName;
	}
	
	public void selectDropdownValueByText(WebElement element, String value)
	{
		Select select = new Select(element);
		select.selectByVisibleText(value);
		log.info("Dropdown value selected is : "+value);
		test.get().info("Dropdown value selected is : "+value);
		
	}


	@AfterClass(alwaysRun = true)
	public void endTest() {
		System.out.println("I am in end test");
		closeBrowser();
	}
	@AfterSuite
	public void afterSuite()
	{
		copyReportToOld();
	}

}
