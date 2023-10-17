package lwsdk.app.reports;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import lwsdk.app.base.impl.MobileWrapperImpl;

public class AllureReport extends MobileWrapperImpl{
	
	public static void printTextLog(String message) {
		Allure.step(message);
	}
	
	public static void printFailedLogWithScreenShot(String message) {
		Allure.step(message, Status.FAILED);
		AllureReport report = new AllureReport();
	    Allure.addAttachment("Refer Attachment", report.getScreenShot());
	}
	
	public static void printSkippedTextLog(String message) {
		Allure.step(message, Status.SKIPPED);
	}
	
	public static void printFailedTextLog(String message) {
		Allure.step(message, Status.FAILED);
	}
	
	public static void intialMethod(ITestResult result) {
		Allure.feature(result.getClass().getName());
		Allure.story(result.getMethod().getMethodName());
		Allure.description(result.getMethod().getDescription());
	}
	
	private ByteArrayInputStream getScreenShot() {
		return new ByteArrayInputStream(((TakesScreenshot)  driver).getScreenshotAs(OutputType.BYTES));
	}
}
