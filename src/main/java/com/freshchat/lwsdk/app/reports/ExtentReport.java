package com.freshchat.lwsdk.app.reports;

import java.io.File;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.freshchat.lwsdk.app.base.impl.MobileWrapperImplementation;
import com.freshchat.lwsdk.app.logger.Log;
import java.lang.reflect.Field;

import io.appium.java_client.AppiumDriver;

public class ExtentReport extends MobileWrapperImplementation{
	
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	/**
	 * Method to set initial setup for extent report
	 */
	public static void initReports() {
		if (Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/ExtentReports/AutomationReport.html");
			extent.attachReporter(spark);
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle("Freshchat LWSDK -TestRun");
			spark.config().setReportName("Freshchat LWSDK -TestRun");
			spark.config().setEncoding("utf-8");
		}
	}

	/**
	 * Method to publish extent report
	 */
	public static void flushReports() {

		if (Objects.nonNull(extent)) {
			extent.flush();
		}
		extentTest.remove();
	}

	/**
	 * Method to create test report for extent 
	 * 
	 * @param testCaseName
	 */
	public static void createTest(String testCaseName) {
		extentTest.set(extent.createTest(testCaseName));
	}

	/**
	 * To log the given message to the reporter at INFO level
	 *
	 * @param message
	 */
	public static void info(String message) {
		extentTest.get().log(Status.INFO, message);
	}

	/**
	 * To log the given message to the reporter at PASS level
	 *
	 * @param m
	 */
	public static void pass(Markup m) {
		extentTest.get().log(Status.PASS, m);
	}

	/**
	 * To log the given message to the reporter at FAIL level
	 *
	 * @param failMessage
	 */
	public static void fail(Markup m) {
		extentTest.get().log(Status.FAIL, m);
	}
	
	/**
	 * To log the given message to the reporter at FAIL level
	 *
	 * @param failMessage
	 */
	public static void fail(String m) {
		extentTest.get().log(Status.FAIL, m);
	}
	
	/**
	 * To log the given message to the reporter at FAIL level
	 *
	 * @param failMessage
	 */
	public static void fail(Markup m, ITestResult result) {
		ExtentReport report = new ExtentReport();
		report.takeScreenShot(result);
		extentTest.get().log(Status.FAIL, m);
	}

	/**
	 * To log the given message to the reporter at SKIP level
	 *
	 * @param message
	 */
	public static void skip(Markup m) {
		extentTest.get().log(Status.SKIP, m);
	}
	
	/**
	 * To log the given message to the reporter at SKIP level
	 *
	 * @param message
	 */
	public static void skip(Markup m,ITestResult result, AppiumDriver drivers) {
		ExtentReport report = new ExtentReport();
		report.takeScreenShot(result);
		extentTest.get().log(Status.SKIP, m);
	}
	
	/**
	 * To log the given message to the reporter at SKIP level
	 *
	 * @param message
	 */
	public static void skip(String m) {
		extentTest.get().log(Status.SKIP, m);
	}

	/**
	 * To print the stack trace of the given error/exception
	 *
	 * @param t
	 */
	public static void logStackTrace(Throwable t) {
		if (t instanceof SkipException) {
			extentTest.get().log(Status.SKIP, "<div class=\"stacktrace\">" + t.getLocalizedMessage() + "</div>");
		} else {
			extentTest.get().log(Status.FAIL, "<div class=\"stacktrace\">" + t.getLocalizedMessage() + "</div>");
		}
	}

	/**
	 * Method to start capturing extent report 
	 * 
	 * @param result
	 */
	public static void extentTestStart(ITestResult result) {
		initReports();
		ExtentTest test = extent.createTest(result.getTestClass().getName() + " :: " + 
				result.getMethod().getMethodName());
		extentTest.set(test);
		
	}
	
	/**
	 * Method to capture screenshot with result method name
	 * 
	 * @param result
	 * @param drivers
	 */
	private void takeScreenShot(ITestResult result) {
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
			TakesScreenshot ts = (TakesScreenshot) driverAppium;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String filepath = System.getProperty("user.dir") + "/TestReport/FailuresScreens/" + result.getTestClass()
					+ "/" + result.getName() + ".png";
			
			FileUtils.copyFile(source, new File(filepath));
			extentTest.get().addScreenCaptureFromPath(filepath);
		
		} catch (Exception e) {
			Log.exception(e);
		}
	}
	
	

}
