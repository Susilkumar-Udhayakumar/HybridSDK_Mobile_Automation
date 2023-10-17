package lwsdk.app.base.impl;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import lwsdk.app.base.MobileWrapperInterface;

public class MobileWrapperImpl implements MobileWrapperInterface {
	
	public AppiumDriver driver;
	
	public boolean click(WebElement ele,AppiumDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public void scroll() {
		System.out.println("scroll");
		
	}

}
