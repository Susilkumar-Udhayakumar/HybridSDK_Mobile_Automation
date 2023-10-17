package lwsdk.app.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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
    
    
	public void clickConversationButton() {
		Log.message("Clicked Conversation Button");
		Assert.assertTrue(base.click(conversationButton, driver));
	}

}
