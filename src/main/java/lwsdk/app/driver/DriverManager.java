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
	
	public String deviceName, version, URL, app_id, buildName, sauce_UserName, sauce_Password;
	
	public AppiumDriver setAndriodDriverLocal(String udid, String url) throws MalformedURLException {
		System.out.println("setAndriodDriverLocal");
		UiAutomator2Options options = new UiAutomator2Options()
			    .setUdid(udid)
			    .setApp("/Users/sakrishnan/eclipse-workspace/LightWeightSDKSkeleton/build/hybridsdk-android.apk");
		AppiumDriver driver = new AndroidDriver(
			    new URL(url), options
			);
		return driver;
	}
	
	public AppiumDriver setiOSDriverLocal() throws MalformedURLException {
		XCUITestOptions options = new XCUITestOptions()
			    .setUdid("C42BD6FF-E522-4EE4-A551-32EEAE7EC5F9")
			    .setApp("/Users/sakrishnan/eclipse-workspace/LightWeightSDKSkeleton/build/hybridsdk-ios.app");
		IOSDriver driver = new IOSDriver(
			    new URL("http://127.0.0.1:4723"), options
			);
		return driver;
	}
	
	public AppiumDriver SetAndroidDriverSauceLabs() throws MalformedURLException {
		MutableCapabilities capabilities = new MutableCapabilities();

		capabilities.setCapability("browserName", "chrome");
		capabilities.setCapability("platformName", "android");
		// W3C Protocol is mandatory for Appium 2
		capabilities.setCapability("appium:platformVersion", "12");
		capabilities.setCapability("appium:deviceName", "Google_Pixel_6_Pro_real_us");
		// Mandatory for Appium 2
		capabilities.setCapability("appium:automationName", "uiautomator2");
		//App path
		capabilities.setCapability("appium:app", "storage:filename=app-debug.apk");

		HashMap<String, Object> sauceOptions = new HashMap<String, Object>();
		// appiumVersion is mandatory to use Appium 2 on Sauce Labs
		sauceOptions.put("appiumVersion", "2.0.0");
		capabilities.setCapability("sauce:options", sauceOptions);
		
		AppiumDriver driver = new AndroidDriver(
			    new URL("https://_vgp:f33bed67-f220-4475-931f-5e76cf506969@ondemand.us-west-1.saucelabs.com:443/wd/hub"), capabilities
			);
		return driver;
	}
	
	public AppiumDriver SetiOSDriverSauceLabs() throws MalformedURLException {
		
		MutableCapabilities capabilities = new MutableCapabilities();

		capabilities.setCapability("browserName", "safari");
		capabilities.setCapability("platformName", "ios");
		// W3C Protocol is mandatory for Appium 2
		capabilities.setCapability("appium:platformVersion", "16");
		capabilities.setCapability("appium:deviceName", "iPhone 14");
		// Mandatory for Appium 2
		capabilities.setCapability("appium:automationName", "xcuitest");
		//App path
		capabilities.setCapability("appium:app", "storage:filename=hybridsdk-ios.ipa");
		
		HashMap<String, Object> sauceOptions = new HashMap<String, Object>();
		// appiumVersion is mandatory to use Appium 2 on Sauce Labs
		sauceOptions.put("appiumVersion", "2.0.0");
		capabilities.setCapability("sauce:options", sauceOptions);
		
		AppiumDriver driver = new AndroidDriver(
			    new URL("https://_vgp:f33bed67-f220-4475-931f-5e76cf506969@ondemand.us-west-1.saucelabs.com:443/wd/hub"), capabilities
			);
		return driver;
	}


}
