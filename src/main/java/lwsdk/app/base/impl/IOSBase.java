package lwsdk.app.base.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import lwsdk.app.driver.DriverManager;
import lwsdk.app.logger.Log;

public class IOSBase extends IOSMobileWrapperImpl {
	
	public static String platform;
	public DriverManager driverManager;
	
	
	/**
	 * Method to be triggered before test suite
	 */
	@BeforeSuite(groups = {"smoke"})
	public void beforeSuite(){
		try {
			Log.info("Before Suite");
			platform = "iOS";
			prop.load(new FileInputStream(new File(System.getProperty("user.dir") + "/src/test/resources/iOS.properties")));
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
	@Parameters({"deviceName","url","exec","appName"})
	@BeforeMethod(groups = {"smoke"})
	public void beforeMethod(String deviceName, String url,String exec, String appName){
		
		try {
			Log.info("Before Method");
			driverManager = new DriverManager();
			if (exec.equals("local")) {
				driver = driverManager.setiOSDriverLocal(deviceName, url, appName);
			} else if (exec.equals("saucelabs")) {
				driver = driverManager.setiOSDriverSauceLabs(deviceName,url, appName);
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
	@AfterMethod(groups = {"smoke"})
	public void afterMethod() {
		try {
			Log.info("After Method");
			driver.quit();
		}
		catch(Exception e) {
			Log.exception(e);
		}
	}

}
