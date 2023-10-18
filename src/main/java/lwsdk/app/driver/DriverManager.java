package lwsdk.app.driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.MutableCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;



public class DriverManager {
	
	public AppiumDriver setAndriodDriverLocal(String udid, String url, String appName) throws MalformedURLException {
		System.out.println("setAndriodDriverLocal");
		UiAutomator2Options options = new UiAutomator2Options()
			    .setUdid(udid)
			    .setApp("/Users/sakrishnan/eclipse-workspace/LightWeightSDKSkeleton/build/" + appName);
		AppiumDriver driver = new AndroidDriver(
			    new URL(url), options
			);
		return driver;
	}
	
	public AppiumDriver setiOSDriverLocal(String udid, String url, String appName) throws MalformedURLException {
		XCUITestOptions options = new XCUITestOptions()
			    .setUdid(udid)
			    .setApp("/Users/sakrishnan/eclipse-workspace/LightWeightSDKSkeleton/build/" + appName);
		IOSDriver driver = new IOSDriver(
			    new URL(url), options
			);
		return driver;
	}
	
	public AppiumDriver setAndroidDriverSauceLabs(String deviceName, String url, String appName) throws MalformedURLException {
		MutableCapabilities capabilities = new MutableCapabilities();

		capabilities.setCapability("browserName", "chrome");
		capabilities.setCapability("platformName", "android");
		// W3C Protocol is mandatory for Appium 2
//		capabilities.setCapability("appium:platformVersion", "12");
		capabilities.setCapability("appium:deviceName", deviceName);
		// Mandatory for Appium 2
		capabilities.setCapability("appium:automationName", "uiautomator2");
		//App path
		capabilities.setCapability("appium:app", "storage:filename=" + appName);

		HashMap<String, Object> sauceOptions = new HashMap<String, Object>();
		// appiumVersion is mandatory to use Appium 2 on Sauce Labs
		sauceOptions.put("appiumVersion", "2.0.0");
		capabilities.setCapability("sauce:options", sauceOptions);
		System.out.println("SAUCE_USERNAME"+ System.getProperty("SAUCE_USERNAME"));
		System.out.println("SAUCE_ACCESS_KEY"+ System.getProperty("SAUCE_ACCESS_KEY"));
		AppiumDriver driver = new AndroidDriver(
				new URL("https://"+ System.getProperty("SAUCE_USERNAME") + ":"
		                + System.getProperty("SAUCE_ACCESS_KEY")+url), capabilities
			);
		return driver;
	}
	
	public AppiumDriver setiOSDriverSauceLabs(String deviceName, String url, String appName) throws MalformedURLException {
		
		MutableCapabilities capabilities = new MutableCapabilities();

		capabilities.setCapability("browserName", "safari");
		capabilities.setCapability("platformName", "ios");
		// W3C Protocol is mandatory for Appium 2

		capabilities.setCapability("appium:deviceName", deviceName);
		// Mandatory for Appium 2
		capabilities.setCapability("appium:automationName", "xcuitest");
		//App path
		capabilities.setCapability("appium:app", "storage:filename="+appName);
		
		HashMap<String, Object> sauceOptions = new HashMap<String, Object>();
		// appiumVersion is mandatory to use Appium 2 on Sauce Labs
		sauceOptions.put("appiumVersion", "2.0.0");
		capabilities.setCapability("sauce:options", sauceOptions);

		AppiumDriver driver = new AndroidDriver(
			    new URL("https://"+ System.getProperty("SAUCE_USERNAME") + ":"
		                + System.getProperty("SAUCE_ACCESS_KEY")+url), capabilities
			);
		return driver;
	}


}
