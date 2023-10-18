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

public class AndroidBase extends AndroidMobileWrapperImpl {
	
	
	public static String platform;
	public DriverManager driverManager;
	
	
	@BeforeSuite(groups = {"smoke"})
	public void beforeSuite() throws FileNotFoundException, IOException{
		System.out.println("Before Suite");
		platform = "Android";
		//Properties Loading
		prop.load(new FileInputStream(new File(System.getProperty("user.dir") + "/src/test/resources/android.properties")));
		
	}
	@Parameters({"deviceName","url","exec","appName"})
	@BeforeMethod(groups = {"smoke"})
	public void beforeMethod(String deviceName, String url,String exec, String appName) throws MalformedURLException {
		System.out.println("Before Method");
		//Driver Initialization
		driverManager = new DriverManager();
		if(exec.equals("local")) {
			driver = driverManager.setAndriodDriverLocal(deviceName,url,appName);
		}else if(exec.equals("saucelabs")) {
			driver = driverManager.setAndroidDriverSauceLabs(deviceName,url,appName);
		}
	}
	
	@AfterMethod(groups = {"smoke"})
	public void afterMethod() {
		System.out.println("After Method");
		driver.quit();
	}

}
