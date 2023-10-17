package lwsdk.app.base.impl;

import java.net.MalformedURLException;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import lwsdk.app.driver.DriverManager;

public class AndroidBase extends AndroidMobileWrapperImpl {
	
	
	public static String platform;
	
	@Parameters({"udid","url","exec"})
	@BeforeSuite(groups = {"smoke"})
	public void beforeSuite(String udid, String url,String exec) throws MalformedURLException {
		System.out.println("beforeSuite");
		DriverManager dm = new DriverManager();
		if(exec.equals("local")) {
			driver = dm.setAndriodDriverLocal(udid,url);
		}else if(exec.equals("cloud")) {
			driver = dm.SetAndroidDriverSauceLabs();
		}
		platform = "Android";
	}

}
