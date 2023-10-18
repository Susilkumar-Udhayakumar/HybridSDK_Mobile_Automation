package lwsdk.app.base.impl;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import lwsdk.app.base.MobileWrapperInterface;
import lwsdk.app.logger.Log;

public class MobileWrapperImpl implements MobileWrapperInterface {
	
	public AppiumDriver driver;
	
	public boolean click(WebElement ele, AppiumDriver driver, String message) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			Log.message(message);
		} catch (Exception e) {
			Log.exception(e, driver);
			return false;
		}

		return true;
	}

	public void scroll() {
		System.out.println("scroll");
		
	}

}
