package lwsdk.reports;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.SkipException;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Log {
	
	 private static final Logger logger = LogManager.getLogger(Thread.currentThread().getName());
	
	/**
	 * message print the test case custom message in the log (level=info) depends on
	 * message print the test case custom message in the log
	 *
	 * @param description test case
	 */
	public static void message(String description) {
		Thread.currentThread().getName();
		ExtentReport.info(description);
		logger.info(description);
		AllureReport.saveTextLog(description);
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
		String logText = "<b>Test Method "+ result.getMethod().getMethodName() + " Successfull</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		ExtentReport.pass(m);
		logger.info(m);
		AllureReport.saveTextLog("Test Method"+ result.getMethod().getMethodName() +" Successfull");
	}
	
	/**
	 * pass print test case status as Skip with custom message (level=info)
	 *
	 * @param description custom message in the test case
	 */
	public static void skip(ITestResult result) {
		
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		String skipExceptionMessage = "<details><summary><b><font color=orange>" + 
				"Exception Occured, click to see details:" + "</font></b></summary>" + 
				exceptionMessage.replaceAll(",", "<br>") + "</details> \n";
		
		Markup m = MarkupHelper.createLabel(skipExceptionMessage, ExtentColor.YELLOW);
		ExtentReport.skip(m,result);
		
		AllureReport.saveFailureScreenShot();
		AllureReport.saveTextLog(m.getMarkup());
		
		String logText = "<b>Test Method "+ result.getMethod().getMethodName() + " SKIP</b>";
		m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		ExtentReport.skip(m);
		logger.info(m);
		AllureReport.saveTextLog("Test Method"+ result.getMethod().getMethodName() +" SKIP");
	}
	
	/**
	 * fail print test case status as Fail with custom message (level=error) 
	 *
	 * @param description custom message in the test case
	 */
	public static void fail(ITestResult result) {
		
		String methodName = result.getMethod().getMethodName();
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		String exceptionMsg = "<details><summary><b><font color=red>" + 
				"Exception Occured, click to see details:" + "</font></b></summary>" + 
				exceptionMessage.replaceAll(",", "<br>") + "</details> \n";
		Markup m = MarkupHelper.createLabel(exceptionMsg, ExtentColor.RED);
		ExtentReport.fail(m, result);
		
		AllureReport.saveFailureScreenShot();
		AllureReport.saveTextLog(m.getMarkup());
		
		String logText = "<b>Test Method "+ methodName + " Failed</b>";
		m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		ExtentReport.fail(m);
		
		logger.error(m);
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
		} else {
			ExtentReport.fail(e.getMessage());
			ExtentReport.logStackTrace(e);
		}
	}
	
	/**
	 * message print the test case started
	 * message print the test case custom message in the log
	 *
	 * @param testResult
	 */
	public static void testStart(ITestResult result) {
		Thread.currentThread().getName();
		ExtentReport.extentTestStart(result);
		logger.info("****             " + "-Test--Case--Started--" + "             *****");
	}
	
	/**
	 * message print the test case started
	 * message print the test case custom message in the log
	 *
	 * @param testResult
	 */
	public static void testEnd() {
		Thread.currentThread().getName();
		ExtentReport.flushReports();
		logger.info("****             " + "-Test--Case--Ended--" + "             *****");
	}

}
