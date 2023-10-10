package lwsdk.reports;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import lwsdk.driver.DriverManager;
import io.qameta.allure.Attachment;

public class AllureReport {
	
	@Attachment
	public static byte[] saveFailureScreenShot() {
		return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
	}
	
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

}
