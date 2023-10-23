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

	/**
	 * Method to setup local android driver
	 * 
	 * @param udid
	 * @param url
	 * @param appName
	 * @return
	 * @throws MalformedURLException
	 */
	public AppiumDriver setAndriodDriverLocal(String udid, String url, String appName) throws MalformedURLException {
		UiAutomator2Options options = new UiAutomator2Options()
				.setUdid(udid)
				.setApp(System.getProperty("user.dir") + "/build/" + appName)
				.chromedriverUseSystemExecutable();
		AppiumDriver driver = new AndroidDriver(new URL(url), options);
		return driver;
	}

	/**
	 * Method to setup local ios driver
	 * 
	 * @param udid
	 * @param url
	 * @param appName
	 * @return
	 * @throws MalformedURLException
	 */
	public AppiumDriver setiOSDriverLocal(String udid, String url, String appName) throws MalformedURLException {
		XCUITestOptions options = new XCUITestOptions()
				.setUdid(udid)
				.setApp(System.getProperty("user.dir") + "/build/" + appName);
		IOSDriver driver = new IOSDriver(new URL(url), options);
		return driver;
	}

	/**
	 *  Method to setup saucelab android
	 * 
	 * @param deviceName
	 * @param url
	 * @param appName
	 * @param platformVersion
	 * @return
	 * @throws MalformedURLException
	 */
	public AppiumDriver setAndroidDriverSauceLabs(String deviceName, String url, String appName)
			throws MalformedURLException {
		
		if(System.getProperty("CLOUD_DEVICE_NAME")!=null) {
			deviceName = System.getProperty("CLOUD_DEVICE_NAME");
		}
		
		MutableCapabilities capabilities = new MutableCapabilities();

		capabilities.setCapability("browserName", "chrome");
		capabilities.setCapability("platformName", "android");
		capabilities.setCapability("appium:platformVersion", System.getProperty("CLOUD_DEVICE_VERSION"));
		capabilities.setCapability("appium:deviceName", deviceName);
		capabilities.setCapability("appium:automationName", "uiautomator2");
		capabilities.setCapability("appium:app", "storage:filename=" + appName);

		HashMap<String, Object> sauceOptions = new HashMap<String, Object>();
		sauceOptions.put("appiumVersion", "2.0.0");
		capabilities.setCapability("sauce:options", sauceOptions);
		AppiumDriver driver = new AndroidDriver(new URL(
				"https://" + System.getProperty("SAUCE_USERNAME") + ":" + System.getProperty("SAUCE_ACCESS_KEY") + url), capabilities);
		return driver;
	}

	/**
	 *   Method to setup saucelab ios
	 * 
	 * @param deviceName
	 * @param url
	 * @param appName
	 * @param platformVersion
	 * @return
	 * @throws MalformedURLException
	 */
	public AppiumDriver setiOSDriverSauceLabs(String deviceName, String url, String appName)
			throws MalformedURLException {
		
		if(System.getProperty("CLOUD_DEVICE_NAME")!=null) {
			deviceName = System.getProperty("CLOUD_DEVICE_NAME");
		}

		MutableCapabilities capabilities = new MutableCapabilities();

		capabilities.setCapability("browserName", "safari");
		capabilities.setCapability("platformName", "ios");
		capabilities.setCapability("appium:platformVersion", System.getProperty("CLOUD_DEVICE_VERSION"));
		capabilities.setCapability("appium:deviceName", deviceName);
		capabilities.setCapability("appium:automationName", "xcuitest");
		capabilities.setCapability("appium:app", "storage:filename=" + appName);

		HashMap<String, Object> sauceOptions = new HashMap<String, Object>();
		sauceOptions.put("appiumVersion", "2.0.0");
		capabilities.setCapability("sauce:options", sauceOptions);

		AppiumDriver driver = new IOSDriver(new URL(
				"https://" + System.getProperty("SAUCE_USERNAME")+ ":" + System.getProperty("SAUCE_ACCESS_KEY") + url),
				capabilities);
		return driver;
	}

}
