package com.freshchat.lwsdk.app.base.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.freshchat.lwsdk.app.base.MobileWrapperInterface;
import com.freshchat.lwsdk.app.logger.Log;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.SupportsContextSwitching;

public class MobileWrapperImplementation implements MobileWrapperInterface {

	public ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
	public String propertyPath = "messaging";
	Authorization auth = new Authorization();
	public static Properties prop = new Properties();

	/**
	 * Method to click web element
	 * 
	 * @param ele
	 * @param driver
	 * @param message
	 * @return
	 */
	public boolean click(WebElement ele, AppiumDriver driver, String message) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			Log.message(message);
		} catch (Exception e) {
			Log.exception(e);
			return false;
		}
		return true;
	}

	/**
	 * Method to check element is displayed
	 * 
	 * @param ele
	 * @param driver
	 * @param message
	 * @return
	 */
	@Override
	public boolean isDisplay(WebElement ele, AppiumDriver driver, String message) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.isDisplayed();
			Log.message(message);
		} catch (Exception e) {
			Log.exception(e);
			return false;
		}
		return true;
	}

	/**
	 * Method to switch to web view content
	 * 
	 * @param driver
	 */
	@Override
	public void switchToWebView(AppiumDriver driver, String contextName) {
		try {
			Set<String> contextHandles = ((AndroidDriver) driver).getContextHandles();
			Log.debug("List of handles available : " + contextHandles);
			for (String context : contextHandles) {
				if (context.contains(contextName)) {
					// Switch to the WebView context
					((SupportsContextSwitching) driver).context(context);
					Log.message("Switched to WebView");
					break;
				}
			}
		} catch (Exception e) {
			Log.exception(e);
		}
	}

	/**
	 * Method to pause execution for specific period of time
	 * 
	 * @param timeDelay - in long millisecond
	 */
	@Override
	public void staticWait(long timeDelay) {
		try {
			Thread.sleep(timeDelay);
			Log.debug("Constant delay applied");
		} catch (Exception e) {
			Log.exception(e);
		}

	}

	/**
	 * Method to switch to frame
	 * 
	 * @param driver
	 * @param ele
	 */
	@Override
	public void switchToFrame(AppiumDriver driver, WebElement element) {
		try {
			driver.switchTo().frame(element);
			Log.message("Switched to frame");
		} catch (Exception e) {
			Log.exception(e);
		}

	}

	/**
	 * Method to scroll to element and will be override by derived class
	 */
	public void scrollIntoView(WebElement element, AppiumDriver driver) {
		// will be override in Android and IOS implementation
	}

	public void auth(String env) {
		try {
			auth.updateOrgDomainAndMobileClientId(propertyPath, env);
			auth.updateWebClientId(propertyPath, env);
			new Authorization(prop.getProperty("orgDomainServer"), prop.getProperty("userName"))
					.updateMobileAuthToken(propertyPath, env);
			auth.updatePropertyFileWithV3Info(propertyPath, env);
			new Authorization(prop.getProperty("orgDomainServer"), prop.getProperty("userName"))
					.updateWebAuthToken(propertyPath, env);
		} catch (FileNotFoundException e) {
			Log.message(e.getLocalizedMessage());
		} catch (ConfigurationException e) {
			Log.message(e.getLocalizedMessage());
		} catch (IOException e) {
			Log.message(e.getLocalizedMessage());
		}
		

	}

}
