package com.freshchat.lwsdk.app.reports;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;

import com.freshchat.lwsdk.app.logger.Log;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;

public class AllureReport {
	
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
	public static void printFailedLogWithScreenShot(String message, ITestResult result) {
		Allure.step(message, Status.FAILED);
		AllureReport report = new AllureReport();
	    Allure.addAttachment("Refer Attachment", report.getScreenShot(result));
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
	private ByteArrayInputStream getScreenShot(ITestResult result) {
		try {
			Class clazz = result.getTestClass().getRealClass();
			Field driverField = null;
			while (clazz != null && driverField == null) {
			    try {
			        driverField = clazz.getDeclaredField("localDriver");
			        driverField.setAccessible(true);
			    } catch (NoSuchFieldException e) {
			        // Field not found in this class, move up the class hierarchy
			        clazz = clazz.getSuperclass();
			    }
			}
			AppiumDriver driverAppium = (AppiumDriver) driverField.get(result.getInstance());
		    return new ByteArrayInputStream(((TakesScreenshot)  driverAppium).getScreenshotAs(OutputType.BYTES));
		
		} catch (Exception e) {
			Log.info(e.getLocalizedMessage());
			return null;
		}
	}
}
