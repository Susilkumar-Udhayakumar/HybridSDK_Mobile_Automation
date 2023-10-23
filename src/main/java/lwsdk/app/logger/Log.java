package lwsdk.app.logger;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.appium.java_client.AppiumDriver;
import lwsdk.app.reports.AllureReport;
import lwsdk.app.reports.ExtentReport;

public class Log {
	
	 private static final Logger logger = LogManager.getLogger(Thread.currentThread().getName());
	
	/**
	 * message print the test case custom message in the log (level=info) depends on
	 * message print the test case custom message in the log
	 *
	 * @param description test case
	 */
	public static void message(String description) {
		ExtentReport.info(description);
		logger.info(description);
		AllureReport.printTextLog(description);
	}
	
	/**
	 * message print the test case custom message in the log (level=info)
	 *
	 * @param description test case
	 */
	public static void info(String description) {
		logger.info(description);
	}
	
	/**
	 * message print the test case custom message in the log (level=debug) depends on
	 * message print the test case custom message in the log
	 *
	 * @param description test case
	 */
	public static void debug(String description) {
		Thread.currentThread().getName();
		ExtentReport.debug(description);
		logger.debug(description);
	}
	
	/**
	 * pass print test case status as Pass with custom message (level=info)
	 *
	 * @param description custom message in the test case
	 */
	public static void pass(ITestResult result) {
		Thread.currentThread().getName();
		String logText = "Test Method "+ result.getMethod().getMethodName() + " ran Successfully";
		Markup m = MarkupHelper.createLabel("<b><i>"+logText+"</i></b>", ExtentColor.GREEN);
		ExtentReport.pass(m);
		logger.info(logText);
		AllureReport.printTextLog(logText);
	}
	
	/**
	 * pass print test case status as Pass with custom message (level=info)
	 *
	 * @param description custom message in the test case
	 */
	public static void pass(String message) {
		Thread.currentThread().getName();
		Markup m = MarkupHelper.createLabel("<b><i>"+message+"</i></b>", ExtentColor.GREEN);
		ExtentReport.pass(m);
		logger.info(message);
		AllureReport.printTextLog(message);
	}
	
	/**
	 * pass print test case status as Skip with custom message (level=info)
	 *
	 * @param description custom message in the test case
	 */
	public static void skip(ITestResult result) {
		
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		String skipExceptionMessage = "Exception Occured, click to see details:";
		
		Markup m = MarkupHelper.createLabel("<details><summary><b><font color=orange>\" + \n"
				+ "				\"Exception Occured, click to see details:\" + \"</font></b></summary>\" + \n"
				+ "				exceptionMessage.replaceAll(\",\", \"<br>\") + \"</details> \\n", ExtentColor.YELLOW);
		
		ExtentReport.skip(m);
		logger.info(skipExceptionMessage+" "+exceptionMessage);
		AllureReport.printSkippedTextLog(skipExceptionMessage+" "+exceptionMessage);
		
		String logText = "Test Method "+ result.getMethod().getMethodName() + " SKIP";
		m = MarkupHelper.createLabel("<b>"+logText+"</b>", ExtentColor.YELLOW);
		ExtentReport.skip(m);
		
		logger.info(logText);
		AllureReport.printSkippedTextLog(logText);
	}
	
	/**
	 * fail print test case status as Fail with custom message (level=error) 
	 *
	 * @param description custom message in the test case
	 */
	public static void fail(String message, AppiumDriver driver) {
		
		Markup m = MarkupHelper.createLabel("<b><i>"+message+"</i></b>", ExtentColor.RED);
		
		ExtentReport.fail(m, driver);
		
		AllureReport.printFailedLogWithScreenShot(message, driver);
		logger.error(message);
	}
	
	/**
	 * fail print test case status as Fail with custom message (level=error) 
	 *
	 * @param description custom message in the test case
	 */
	public static void fail(ITestResult result) {
		
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		String skipExceptionMessage = "Exception Occured, click to see details:";
		
		Markup m = MarkupHelper.createLabel("<details><summary><b><font color=orange>\" + \n"
				+ "				\"Exception Occured, click to see details:\" + \"</font></b></summary>\" + \n"
				+ "				exceptionMessage.replaceAll(\",\", \"<br>\") + \"</details> \\n", ExtentColor.RED);
		
		ExtentReport.fail(m);
		
		AllureReport.printFailedTextLog(skipExceptionMessage+" "+exceptionMessage);
		logger.info(skipExceptionMessage+" "+exceptionMessage);
		
		String logText = "Test Method "+ result.getMethod().getMethodName() + " Failed";
		m = MarkupHelper.createLabel("<b>"+logText+"</b>", ExtentColor.RED);
		ExtentReport.fail(m);
		
		AllureReport.printFailedTextLog(logText);
		logger.error(logText);
	}
	
	/**
	 * exception prints the exception message as fail/skip in the log (level=fatal)
	 *
	 * @param e exception message
	 * @throws Exception
	 */
	public static void exception(Exception e) {
		
		logger.error(e.getMessage());
		if (e instanceof SkipException) {
			ExtentReport.skip(e.getMessage());
			ExtentReport.logStackTrace(e);
			AllureReport.printSkippedTextLog(e.getMessage());
		} else {
			ExtentReport.fail(e.getMessage());
			ExtentReport.logStackTrace(e);
			AllureReport.printFailedTextLog(e.getMessage());
		}
	}
	
	/**
	 * exception prints the exception message as fail/skip in the log (level=fatal)
	 *
	 * @param e exception message
	 * @throws Exception
	 */
	public static void exception(Exception e, AppiumDriver driver) {
		
		logger.error(e.getMessage());
		if (e instanceof SkipException) {
			ExtentReport.skip(e.getMessage());
			ExtentReport.logStackTrace(e);
			AllureReport.printFailedLogWithScreenShot(e.getMessage(), driver);
		} else {
			ExtentReport.fail(e.getMessage());
			ExtentReport.logStackTrace(e);
			AllureReport.printFailedLogWithScreenShot(e.getMessage(), driver);
		}
	}
	
	public static void logAssertTrue(boolean condition, String message, AppiumDriver driver) {
		if(condition) {
			Assert.assertTrue(condition, message);
			pass(message);
		}else {
			Assert.assertTrue(condition, message);
			fail(message, driver);
		}
		
	}
	
	public static void logAssertFalse(boolean condition, String message, AppiumDriver driver) {
		if(!condition) {
			Assert.assertFalse(condition, message);
			pass(message);
		}else {
			Assert.assertFalse(condition, message);
			fail(message, driver);
		}
		
	}
	
	public static void logAssertEqual(boolean actual, boolean expected, String message, String failMessage, AppiumDriver driver) {
		if(Boolean.valueOf(actual).equals(Boolean.valueOf(expected))) {
			Assert.assertEquals(actual, expected, message);
			pass(message);
		}else {
			Assert.assertTrue(false, message);
			fail(message, driver);
		}
		
	}
	
	/**
	 * message print the test case started
	 * message print the test case custom message in the log
	 *
	 * @param testResult
	 */
	public static void testStart(ITestResult result) {
		System.out.println(result.getMethod().getMethodName());
		ExtentReport.extentTestStart(result);
		AllureReport.intialMethod(result);
		logger.info("****             " + "-Test--Case--Started--" + "             *****");
		logger.info("Test Method Name :"+result.getMethod().getMethodName());
		logger.info("Test Description :"+result.getMethod().getDescription());
	}
	
	/**
	 * message print the test case end
	 * message print the test case custom message in the log
	 *
	 * @param testResult
	 */
	public static void testEnd() {
		Thread.currentThread().getName();
		ExtentReport.flushReports();
	}

}
