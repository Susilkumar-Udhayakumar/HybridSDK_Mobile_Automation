package lwsdk.driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobilePlatform;

public class DriverManager {
	
	public static AppiumDriver<MobileElement> driver=null;
	public static DesiredCapabilities capabilities = new DesiredCapabilities();
	public static DesiredCapabilities sauceOptions = new DesiredCapabilities();
	public static String deviceName, version, URL, app_id, buildName, sauce_UserName, sauce_Password;
	public static int count=1; 
	
	public static void setDriver(String OS, String deviceName, String version, String URL, String appPath, String testSuiteName,String buildName, String sauce_UserName,String sauce_Password)
			throws MalformedURLException {

		if(System.getProperty("OS")!=null) {
			OS = System.getProperty("OS");
		}
		
		if(System.getProperty("CLOUD_DEVICE_NAME")!=null) {
			deviceName = System.getProperty("CLOUD_DEVICE_NAME");
		}
		
		if(System.getProperty("PLATFORM_VERSION")!=null) {
			version = System.getProperty("PLATFORM_VERSION");
		}
		
		if(System.getProperty("DEVICE_LOCATION")!=null) {
			if(System.getProperty("DEVICE_LOCATION").equals("EU")) {
				URL = "appium.testobject.com";
			}
		}
		
		if(System.getProperty("APP_ID")!=null) {
			app_id= "storage:filename=" + System.getProperty("APP_ID"); 
		}
		
		if(System.getProperty("SAUCE_USERNAME")!=null) {
			sauce_UserName= System.getProperty("SAUCE_USERNAME"); 
		}
		
		if(System.getProperty("SAUCE_ACCESS_KEY")!=null) {
			sauce_Password= System.getProperty("SAUCE_ACCESS_KEY"); 
		}
			
		if(System.getProperty("TEST_SUITE_NAME")!=null) {
		   testSuiteName = System.getProperty("TEST_SUITE_NAME");
		   testSuiteName=testSuiteName+"_set_"+count;
		   count++;
		}
		else
		{ 
			testSuiteName = OS+" FreshChat LWSDK Test Suite";
		}
		
		if(System.getProperty("SAUCELABS_BUILD_NAME")!=null) {
			buildName= System.getProperty("SAUCELABS_BUILD_NAME");
		}else {
			buildName= "Freshchat_LWSDK_Automation_Test_"+LocalDate.now();
		}
		
		if(driver==null) {
			if (OS.equalsIgnoreCase("iosCloud")) {
				iosSauceLabs(deviceName, version, URL, appPath,testSuiteName,buildName,sauce_UserName,sauce_Password);
			} else if (OS.equalsIgnoreCase("androidCloud")) {
				androidSauceLabs(deviceName, version, URL, appPath,testSuiteName,buildName,sauce_UserName,sauce_Password);
			}
		}
	}
	
	public static void androidSauceLabs(String deviceName, String version, String URL, String appPath,String testSuiteName,String buildName,String sauce_UserName, String sauce_Password)
			throws MalformedURLException {
		
        
        capabilities.setCapability("platformName", MobilePlatform.ANDROID);
        capabilities.setCapability("appium:platformVersion",  version);
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("appium:deviceName", deviceName);
        capabilities.setCapability("appium:automationName", AutomationName.ANDROID_UIAUTOMATOR2);
        capabilities.setCapability("appium:app", app_id);
        capabilities.setCapability("appium:noReset", true);
        capabilities.setCapability("appium:noSign", true);
        capabilities.setCapability("appium:autoGrantPermissions", true);
        capabilities.setCapability("appium:autoAcceptAlerts", true);
        capabilities.setCapability("name", testSuiteName);
        capabilities.setCapability("build",buildName);
        sauceOptions.setCapability("username", sauce_UserName); 
        sauceOptions.setCapability("accessKey", sauce_Password);
        capabilities.setCapability("sauce:options", sauceOptions);
        
        try {
    		driver = new AndroidDriver<MobileElement>(new URL("https://" +URL+"/wd/hub"), capabilities);
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	
	public static void iosSauceLabs(String deviceName, String version, String URL, String appPath,String testSuiteName,String buildName,String sauce_UserName, String sauce_Password)
			throws MalformedURLException {
		
        
        capabilities.setCapability("platformName", MobilePlatform.IOS);
        capabilities.setCapability("appium:platformVersion",  version);
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("appium:deviceName", deviceName);
        capabilities.setCapability("appium:automationName", AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability("appium:app", app_id);
        capabilities.setCapability("appium:noReset", true);
        capabilities.setCapability("appium:noSign", true);
        capabilities.setCapability("appium:autoGrantPermissions", true);
        capabilities.setCapability("appium:autoAcceptAlerts", true);
        capabilities.setCapability("name", testSuiteName);
        capabilities.setCapability("build",buildName);
        sauceOptions.setCapability("sauceLabsNetworkCaptureEnabled", true);
        sauceOptions.setCapability("username", sauce_UserName); 
        sauceOptions.setCapability("accessKey", sauce_Password);
        capabilities.setCapability("sauce:options", sauceOptions);
        
        try {
    		driver = new AndroidDriver<MobileElement>(new URL("https://" +URL+"/wd/hub"), capabilities);
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	public static AppiumDriver<MobileElement> getDriver() {
		return driver;
	}

}
