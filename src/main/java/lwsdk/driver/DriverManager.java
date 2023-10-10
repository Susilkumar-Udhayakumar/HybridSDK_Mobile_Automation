package lwsdk.driver;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;
import io.appium.java_client.android.options.UiAutomator2Options;

public class DriverManager {
//	public AppiumDriver driver1=null;
	public String deviceName, version, URL, app_id, buildName, sauce_UserName, sauce_Password;
	
	public AppiumDriver setAndriodDriverLocal() throws MalformedURLException {
		UiAutomator2Options options = new UiAutomator2Options()
			    .setUdid("emulator-5554")
			    .setApp("/Users/sakrishnan/eclipse-workspace/LightWeightSDKSkeleton/build/hybridsdk-android.apk");
		AppiumDriver driver = new AndroidDriver(
			    // The default URL in Appium 1 is http://127.0.0.1:4723/wd/hub
			    new URL("http://127.0.0.1:4723"), options
			);
//		driver.findElement(By.id("com.freshworks.lwsdk:id/btnShowConversations")).click();
		return driver;
	}


}
