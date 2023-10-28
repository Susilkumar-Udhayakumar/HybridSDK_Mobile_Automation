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
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ConversationListPage {

	public ThreadLocal<AppiumDriver> driver;
	public MobileWrapperImplementation base;

	/**
	 * Constructor to initialize driver
	 * @param driver
	 */
	public ConversationListPage(ThreadLocal<AppiumDriver> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver.get()), this);
		if (AndroidBase.platform != null && AndroidBase.platform.equals("Android")) {
			base = new AndroidMobileWrapperImplmentation();                 
		} else if ((IOSBase.platform != null && IOSBase.platform.equals("iOS"))) {
			base = new IOSMobileWrapperImplementation();
		}
	}

	@FindBy(xpath = "//div[contains(text(),'Robin')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Robin's Topic\"]")
	private WebElement channelWelcomeMsgTxt;

	/**
	 * Method to click channel name in conversation list page
	 */
	public void clickConversationButton() {
		base.isDisplay(channelWelcomeMsgTxt, driver.get(), "Channel welcome message text displayed");
		Log.logAssertTrue(base.click(channelWelcomeMsgTxt, driver.get(), "Clicked Channel Text"),
				"Channel name clicked successfully", "Issue in clicking channel name"); 
	}

}
