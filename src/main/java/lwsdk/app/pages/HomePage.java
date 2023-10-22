package lwsdk.app.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lwsdk.app.base.impl.AndroidBase;
import lwsdk.app.base.impl.AndroidMobileWrapperImpl;
import lwsdk.app.base.impl.IOSBase;
import lwsdk.app.base.impl.IOSMobileWrapperImpl;
import lwsdk.app.base.impl.MobileWrapperImpl;
import lwsdk.app.logger.Log;

public class HomePage {
	
	public AppiumDriver driver;
	public MobileWrapperImpl base;
	
	/**
	 * Constructor to initialize driver
	 * @param driver
	 */
	public HomePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        if(AndroidBase.platform != null  && AndroidBase.platform.equals("Android")) {
        	base = new AndroidMobileWrapperImpl();
        }else if((IOSBase.platform != null  && IOSBase.platform.equals("iOS"))){
        	base = new IOSMobileWrapperImpl();
        }
    }
	
    @AndroidFindBy(id="com.freshworks.lwsdk:id/btnShowConversations")
    @iOSXCUITFindBy(xpath ="//XCUIElementTypeButton[@name='Show Conversations']")
    private WebElement conversationButton;
    
    @iOSXCUITFindBy(xpath ="//XCUIElementTypeButton[@name='Allow']")
    private WebElement allowButton;
    
    @FindBy(name = "fc_widget")
	private WebElement webViewFrame;
    
    /**
	 * Method to click allow button
	 */
    public HomePage clickAllowButton() {
    	base.click(allowButton, driver, "Clicked Allow Button");
		return this;
	}
    
    /**
	 * Method to click conversation button in home page
	 */
	public HomePage clickConversationButton() {
		Log.logAssertTrue(base.click(conversationButton, driver, "Clicked Conversation Method"), "Conversation button clicked successfully", driver);
		return this;
	}
	
	/**
	 * Method to switch iframe in web view
	 */
	public HomePage switchToWebViewWithFrame() {
		base.staticWait(15000);
		base.switchToWebView(driver);
		base.switchToFrame(driver, webViewFrame);
		return this;
	}

}
