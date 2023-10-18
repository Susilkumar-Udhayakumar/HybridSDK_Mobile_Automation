package lwsdk.app.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lwsdk.app.base.impl.AndroidBase;
import lwsdk.app.base.impl.AndroidMobileWrapperImpl;
import lwsdk.app.base.impl.IOSBase;
import lwsdk.app.base.impl.IOSMobileWrapperImpl;
import lwsdk.app.base.impl.MobileWrapperImpl;
import lwsdk.app.logger.Log;

public class ConversationListPage {
	
	public AppiumDriver driver;
	public MobileWrapperImpl base;
	
    public ConversationListPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        if(AndroidBase.platform != null  && AndroidBase.platform.equals("Android")) {
        	base = new AndroidMobileWrapperImpl();
        }else if((IOSBase.platform != null  && IOSBase.platform.equals("iOS"))){
        	base = new IOSMobileWrapperImpl();
        }
    }
	
   @FindBy(xpath ="//div[contains(text(),'Robin')]")
    private WebElement channelWelcomeMsgTxt;
    
	public void clickConversationButton() {
		base.isDisplay(channelWelcomeMsgTxt, driver, "Channel welcome message text displayed");
		Log.LogAssertTrue(base.click(channelWelcomeMsgTxt, driver, "Clicked Channel Text"), "Channel clicked successfully", driver);
	}

}
