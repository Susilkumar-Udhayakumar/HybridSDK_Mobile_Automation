package com.freshchat.lwsdk.app.base.impl;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.freshchat.lwsdk.app.base.AndroidMobileWrapperInterface;
import io.appium.java_client.AppiumDriver;

public class AndroidMobileWrapperImplmentation extends MobileWrapperImplementation implements AndroidMobileWrapperInterface{

	/**
	 *Method to scroll to the webElement
	 */
	@Override
	public void scrollIntoView(WebElement element, AppiumDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

}
