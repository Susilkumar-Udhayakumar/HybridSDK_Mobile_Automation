package lwsdk.app.base;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public interface MobileWrapperInterface{
	
	public boolean click(WebElement ele, AppiumDriver driver);
	
	public void scroll();
	
}
