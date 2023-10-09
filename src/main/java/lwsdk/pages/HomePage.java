package lwsdk.pages;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import lwsdk.base.MobileWrapperInterface;
import lwsdk.base.impl.AndroidBase;
import lwsdk.base.impl.AndroidMobileWrapperImpl;
import lwsdk.base.impl.IOSBase;
import lwsdk.base.impl.MobileWrapperImpl;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {
	private AppiumDriver<MobileElement> driver;
    private AndroidBase androidBase;
    private IOSBase iosBase;
    private MobileWrapperImpl base;
	
    public HomePage(AppiumDriver<MobileElement> driver, String platform) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);

        if ("Android".equalsIgnoreCase(platform)) {
        	base = new AndroidMobileWrapperImpl();
        } else if ("iOS".equalsIgnoreCase(platform)) {
        	base = new IOSBase();
        } else {
            throw new IllegalArgumentException("Unsupported platform: " + platform);
        }
    }
    
	public void clickHome() {
		 base.closeAndroid();
	}

}
