package lwsdk.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import lwsdk.base.impl.AndroidBase;
import lwsdk.base.impl.AndroidMobileWrapperImpl;
import lwsdk.base.impl.IOSMobileWrapperImpl;
import lwsdk.base.impl.MobileWrapperImpl;
import lwsdk.reports.Log;

public class HomePage {
	
	public AppiumDriver driver;
	public MobileWrapperImpl base;
	
    public HomePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        if(AndroidBase.platform.equals("Android")) {
        	base = new AndroidMobileWrapperImpl();
        }else {
        	base = new IOSMobileWrapperImpl();
        }
    }
	
    @AndroidFindBy(id="com.freshworks.lwsdk:id/btnShowConversations")
    @iOSXCUITFindBy(id="com.freshworks.lwsdk:id/btnShowConversations")
    private WebElement conversationButton;
    
    
	public void clickConversationButton() {
		Log.message("Clicked Conversation Button");
		Assert.assertTrue(base.click(conversationButton, driver));
	}

}
