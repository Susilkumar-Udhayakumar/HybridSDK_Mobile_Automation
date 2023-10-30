package com.freshchat.lwsdk.app.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import com.freshchat.lwsdk.app.base.impl.AndroidBase;
import com.freshchat.lwsdk.app.base.impl.AndroidMobileWrapperImplmentation;
import com.freshchat.lwsdk.app.base.impl.IOSBase;
import com.freshchat.lwsdk.app.base.impl.IOSMobileWrapperImplementation;
import com.freshchat.lwsdk.app.base.impl.MobileWrapperImplementation;
import com.freshchat.lwsdk.app.logger.Log;

public class ConversationListPage {

	public ThreadLocal<AppiumDriver> driver;
	public MobileWrapperImplementation mobileUtils;

	/**
	 * Constructor to initialize driver
	 * @param driver
	 */
	public ConversationListPage(ThreadLocal<AppiumDriver> driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver.get()), this);
		if (AndroidBase.platform != null && AndroidBase.platform.equals("Android")) {
			mobileUtils = new AndroidMobileWrapperImplmentation();                 
		} else if ((IOSBase.platform != null && IOSBase.platform.equals("iOS"))) {
			mobileUtils = new IOSMobileWrapperImplementation();
		}
	}

	@FindBy(xpath = "//div[contains(text(),'Robin')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Robin's Topic\"]")
	private WebElement channelWelcomeMsgTxt;
	
	@FindBy(xpath = "//div[contains(@class,'d_hotline minimize')]")
	private WebElement closeConversationListBtn;
	
	@FindBy(xpath = "//div[contains(@class,'footer-note animated slideInUp faster show-when-zoom')]")
	private WebElement freshChatLbl;
	
	@FindBy(xpath = "//div[text()='FAQs']")
	private WebElement faqSectionHeaderTxt;
	
	@FindBy(xpath = "//div[@class='search-category']")
	private WebElement faqSearchBtn;
	
	@FindBy(xpath = "//h1[text()='Categories']")
	private WebElement faqSectionCategoryHeaderTxt;
	
	@FindBy(xpath = "//div[@class='faq-title']")
	private List<WebElement> faqCategoryTitleList;

	/**
	 * Method to click channel name in conversation list page
	 */
	public ConversationListPage clickConversationButton() {
		mobileUtils.isDisplay(channelWelcomeMsgTxt, driver.get(), "Channel welcome message text displayed");
		Log.logAssertTrue(mobileUtils.click(channelWelcomeMsgTxt, driver.get(), "Clicked Channel Text"),
				"Channel name clicked successfully", "Issue in clicking channel name"); 
		return this;
	}
	
	/**
	 * Method to check channel name is displaying in conversation list page
	 */
	public ConversationListPage checkConversationAppearinConversationList() {
		Log.logAssertTrue(mobileUtils.isDisplay(channelWelcomeMsgTxt, driver.get(), "Channel welcome message text displayed"),
				"Conversation appears in conversation list screen", "Issue in getting conversation name appears in conversation list screen"); 
		return this;
	}
	
	/**
	 * Method to click close button in conversation list page
	 */
	public ConversationListPage clickCloseButtonInCoversationList() {
		mobileUtils.click(closeConversationListBtn, driver.get(), "Clicked close button in conversation list screen");
		return this;
	}
	
	/**
	 * Method to click close button in conversation list page
	 */
	public ConversationListPage checkFreshChatLabelAppearInCoversationList() {
		mobileUtils.isDisplay(freshChatLbl, driver.get(), "FreshChat label appear in conversation list screen");
		return this;
	}
	
	/**
	 * Method to check FAQ search icon in conversation list page
	 */
	public ConversationListPage clickFAQSearchIconInCoversationList() {
		mobileUtils.scrollIntoView(faqSectionHeaderTxt, driver.get());
		mobileUtils.click(faqSearchBtn, driver.get(), "FAQ search icon is clicked");
		return this;
	}
	
	/**
	 * Method to check FAQ Category is displaying
	 */
	public ConversationListPage checkFAQPageAppearing() {
		Log.logAssertTrue(mobileUtils.isDisplay(faqSectionCategoryHeaderTxt, driver.get(), "FAQ category is displayed"),
				"FAQ page is appearing successfully", "Issue in appearing FAQ category"); 
		return this;
	}
	
	/**
	 * Method to check FAQ Category is displaying
	 */
	public ConversationListPage checkFAQCategoryUnderFAQSection() {
		Log.logAssertTrue(mobileUtils.isDisplay(faqCategoryTitleList.get(0), driver.get(), "FAQ title is displayed"),
				"FAQ category is displayed under FAQ section", "Issue in displaying FAQ category under FAQ section"); 
		return this;
	}

}
