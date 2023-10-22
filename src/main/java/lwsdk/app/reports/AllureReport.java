package lwsdk.app.reports;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import lwsdk.app.base.impl.MobileWrapperImpl;

public class AllureReport extends MobileWrapperImpl{
	
	/**
	 * Method to print text log in allure report
	 * 
	 * @param message
	 */
	public static void printTextLog(String message) {
		Allure.step(message);
	}
	
	/**
	 * Method to print text log in allure report along with screenshot and set status as Failure
	 * 
	 * @param message
	 * @param drivers
	 */
	public static void printFailedLogWithScreenShot(String message, AppiumDriver drivers) {
		Allure.step(message, Status.FAILED);
		AllureReport report = new AllureReport();
	    Allure.addAttachment("Refer Attachment", report.getScreenShot(drivers));
	}
	
	/**
	 * Method to print text log in allure report along with status set as skipped
	 * 
	 * @param message
	 */
	public static void printSkippedTextLog(String message) {
		Allure.step(message, Status.SKIPPED);
	}
	
	/**
	 * Method to print text log in allure report along with status set as Failure
	 * 
	 * @param message
	 */
	public static void printFailedTextLog(String message) {
		Allure.step(message, Status.FAILED);
	}
	
	/**
	 * Method to set initial setup for allure report
	 * 
	 * @param result
	 */
	public static void intialMethod(ITestResult result) {
		Allure.feature(result.getClass().getName());
		Allure.story(result.getMethod().getMethodName());
		Allure.description(result.getMethod().getDescription());
	}
	
	/**
	 * Method to capture screenshot
	 * 
	 * @param drivers
	 * @return screenshot as byteArray
	 */
	private ByteArrayInputStream getScreenShot(AppiumDriver drivers) {
		return new ByteArrayInputStream(((TakesScreenshot)  drivers).getScreenshotAs(OutputType.BYTES));
	}
}
