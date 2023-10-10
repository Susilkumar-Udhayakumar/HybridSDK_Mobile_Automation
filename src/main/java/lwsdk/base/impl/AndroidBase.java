package lwsdk.base.impl;

import java.net.MalformedURLException;

import org.testng.annotations.BeforeSuite;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lwsdk.driver.DriverManager;

public class AndroidBase extends AndroidMobileWrapperImpl {
	
	
	public static String platform;
	
	@BeforeSuite
	public void beforeSuite() throws MalformedURLException {
		DriverManager dm = new DriverManager();
//		driver = dm.setAndriodDriverLocal();
		driver = dm.SetAndroidDriverSauceLabs();
		platform = "Android";
	}

}
