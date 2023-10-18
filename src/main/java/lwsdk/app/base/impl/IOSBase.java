package lwsdk.app.base.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import lwsdk.app.driver.DriverManager;

public class IOSBase extends IOSMobileWrapperImpl {

	public static String platform;

	@Parameters({"deviceName","url","exec","appName"})
	@BeforeSuite(groups = {"smoke"})
	public void beforeSuite(String deviceName, String url,String exec, String appName) throws FileNotFoundException, IOException {
		DriverManager dm = new DriverManager();
		// Driver Initialization
		if (exec.equals("local")) {
			driver = dm.setiOSDriverLocal(deviceName, url, appName);
		} else if (exec.equals("saucelabs")) {
			driver = dm.setiOSDriverSauceLabs(deviceName,url, appName);
		}
		platform = "iOS";

		// Properties Loading
		prop.load(new FileInputStream(
				new File(System.getProperty("user.dir") + "/src/test/resources/android.properties")));
	}

}
