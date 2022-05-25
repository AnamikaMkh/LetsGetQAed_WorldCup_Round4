package com.test.automation.LetGetQAed.GenericUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class Read_Config {

	public static String readConfig(String key) {
		String value = null;
		try {
			File file = new File(System.getProperty("user.dir") + "/Properties/config.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String itrKey = (String) enuKeys.nextElement();
				if (itrKey.equalsIgnoreCase(key)) {
					value = properties.getProperty(key);
					System.out.println(key + ": " + value);
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	public String getBrowserName(String browser) {
		String browserName = readConfig(browser);
		System.out.println(browserName);
		return browserName;

	}

	public String getApplicationURL(String applicationurlKey) {
		String applicationurlValue = readConfig(applicationurlKey);
		System.out.println(applicationurlValue);
		return applicationurlValue;

	}
	/*public static String readConfig(String key) {
		String value = null;
		try {
			File file = new File(System.getProperty("user.dir") + "/Properties/config.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();

			Enumeration enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				String itrKey = (String) enuKeys.nextElement();
				if (itrKey.equalsIgnoreCase(key)) {
					value = properties.getProperty(key);
					System.out.println(key + ": " + value);
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
	

	public String getBrowserName(String browserKey) {
		String browserName = readConfig(browserKey);
		System.out.println(browserName);
		return browserName;

	}

	public String getApplicationURL(String applicationurlKey) {
		String applicationurlValue = readConfig(applicationurlKey);
		System.out.println(applicationurlValue);
		return applicationurlValue;

	}*/
}

