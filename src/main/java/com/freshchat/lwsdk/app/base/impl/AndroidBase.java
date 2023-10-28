package com.freshchat.lwsdk.app.base.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.freshchat.lwsdk.app.driver.DriverManager;
import com.freshchat.lwsdk.app.logger.Log;

import io.appium.java_client.AppiumDriver;

public class AndroidBase extends AndroidMobileWrapperImplmentation {
	
	
	public static String platform;
	public DriverManager driverManager;
	public AppiumDriver localDriver;
	
	
	/**
	 * Method to be triggered before test suite
	 */
	@BeforeSuite(groups = {"smoke","regression","sanity"})
	public void beforeSuite(){
		try {
			Log.info("Before Suite");
			platform = "Android";
			prop.load(new FileInputStream(new File(System.getProperty("user.dir") + "/src/test/resources/android.properties")));
		}
		catch(FileNotFoundException e) {
			Log.info("File missing");
			Log.info(e.getLocalizedMessage());
		}
		catch(IOException e) {
			Log.info("Issue in input output operation");
			Log.info(e.getLocalizedMessage());
		}		
	}
	
	/**
	 * Method to be triggered before test method
	 * 
	 * @param deviceName - device id for local testing and device name for sauce lab
	 * @param url - local host url / sauce lab url
	 * @param exec - execution environment local / saucelab
	 * @param appName - app name 
	 */
	@Parameters({"deviceName","url","executingEnvironment","appName"})
	@BeforeMethod(groups = {"smoke","regression","sanity"})
	public void beforeMethod(String deviceName, String url, String executingEnvironment, String appName){
		
		try {
			Log.info("Before Method");
			driverManager = new DriverManager();
			if(executingEnvironment.equals("local")) {
				driverManager.startAppiumServer();
				localDriver=driverManager.setAndriodDriverLocal(deviceName,url,appName);
				driver.set(localDriver);
			}else if(executingEnvironment.equals("saucelabs")) {
				localDriver=driverManager.setAndroidDriverSauceLabs(deviceName,url,appName);
				driver.set(localDriver);
			}
		}
		catch(MalformedURLException e) {
			Log.message("Malformed URl exception occured");
			Log.exception(e);
		}
		catch(Exception e) {
			Log.exception(e);
		}
		
	}
	
	/**
	 * Method which triggered after test
	 */
	@AfterMethod(groups = {"smoke","regression","sanity"})
	public void afterMethod() {
		try {
			Log.info("After Method");
			driverManager.stopAppiumServer();
	        if (localDriver != null) {
	        	localDriver.quit();
	        	driver.remove();
	        }
		}
		catch(Exception e) {
			Log.exception(e);
		}
	}

}
