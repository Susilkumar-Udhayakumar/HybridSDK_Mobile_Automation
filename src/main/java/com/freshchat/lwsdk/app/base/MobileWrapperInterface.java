package com.freshchat.lwsdk.app.base;

import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;

public interface MobileWrapperInterface {

	/**
	 * Method to click web element 
	 * 
	 * @param ele 
	 * @param driver
	 * @param message 
	 * @return
	 */
	public boolean click(WebElement ele, AppiumDriver driver, String message);

	/**
	 * Method to pause execution for specific period of time
	 * 
	 * @param timeDelay - in long millisecond
	 */
	public void staticWait(long timeDelay);

	/**
	 * Method to switch to web view content
	 * 
	 * @param driver
	 */
	public void switchToWebView(AppiumDriver driver);

	/**
	 * Method to switch to frame
	 * 
	 * @param driver
	 * @param ele
	 */
	public void switchToFrame(AppiumDriver driver, WebElement ele);

	/**
	 * Method to check element is displayed
	 * 
	 * @param ele
	 * @param driver
	 * @param message
	 * @return
	 */
	boolean isDisplay(WebElement ele, AppiumDriver driver, String message);
	
	
}
