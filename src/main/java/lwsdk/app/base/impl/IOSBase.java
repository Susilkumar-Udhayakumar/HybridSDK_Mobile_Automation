package lwsdk.app.base.impl;

import java.net.MalformedURLException;

import org.testng.annotations.BeforeSuite;

import lwsdk.app.driver.DriverManager;

public class IOSBase extends IOSMobileWrapperImpl {
	
public static String platform;
	
	@BeforeSuite
	public void beforeSuite() throws MalformedURLException {
		DriverManager dm = new DriverManager();
		driver = dm.setiOSDriverLocal();
//		driver = dm.SetiOSDriverSauceLabs();
		platform = "iOS";
	}
	
	
}
