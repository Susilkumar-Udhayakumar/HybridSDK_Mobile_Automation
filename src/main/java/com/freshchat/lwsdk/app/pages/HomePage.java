package com.freshchat.lwsdk.app.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.freshchat.lwsdk.app.base.impl.AndroidBase;
import com.freshchat.lwsdk.app.base.impl.AndroidMobileWrapperImplmentation;
import com.freshchat.lwsdk.app.base.impl.IOSBase;
import com.freshchat.lwsdk.app.base.impl.IOSMobileWrapperImplementation;
import com.freshchat.lwsdk.app.base.impl.MobileWrapperImplementation;
import com.freshchat.lwsdk.app.logger.Log;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomePage {

	public ThreadLocal<AppiumDriver> driver;
	public MobileWrapperImplementation mobileUtils;

	/**
	 * Constructor to initialize driver
	 * 
	 * @param driver2
	 */
	public HomePage(ThreadLocal<AppiumDriver> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver.get()), this);
		if (AndroidBase.platform != null && AndroidBase.platform.equals("Android")) {
			mobileUtils = new AndroidMobileWrapperImplmentation();
		} else if ((IOSBase.platform != null && IOSBase.platform.equals("iOS"))) {
			mobileUtils = new IOSMobileWrapperImplementation();
		}
	}

	@AndroidFindBy(id = "com.freshworks.lwsdk:id/btnShowConversations")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Show Conversations']")
	private WebElement conversationButton;

	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Allow']")
	private WebElement allowButton;

	@FindBy(name = "fc_widget")
	private WebElement webViewFrame;

	/**
	 * Method to click allow button
	 */
	public HomePage clickAllowButton() {
		mobileUtils.click(allowButton, driver.get(), "Clicked Allow Button");
		return this;
	}

	/**
	 * Method to click conversation button in home page
	 */
	public HomePage clickConversationButton() {
		Log.logAssertTrue(mobileUtils.click(conversationButton, driver.get(), "Clicked Conversation Method"),
				"Conversation button clicked successfully", "Issue occured while clicking show conversation button ");
		return this;
	}

	/**
	 * Method to switch iframe in web view
	 */
	public HomePage switchToWebViewWithFrame() {
		mobileUtils.staticWait(5000);
		mobileUtils.switchToWebView(driver.get(), "WEBVIEW");
		mobileUtils.switchToFrame(driver.get(), webViewFrame);
		return this;
	}
	
	/**
	 * Method to check conversation button in home page
	 */
	public HomePage checkConversationButtonInHomePage() {
		mobileUtils.staticWait(5000);
		mobileUtils.switchToWebView(driver.get(), "NATIVE_APP");
		Log.logAssertTrue(mobileUtils.isDisplay(conversationButton, driver.get(), "Conversation button appears"),
				"Homepage is displayed and conversation button appears in it", "Issue in displaying conversation button in home page");
		return this;
	}

}
