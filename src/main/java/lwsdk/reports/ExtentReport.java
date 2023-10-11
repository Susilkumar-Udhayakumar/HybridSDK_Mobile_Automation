package lwsdk.reports;

import java.io.File;
import java.io.IOException;
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

import lwsdk.driver.DriverManager;

public class ExtentReport {
	
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
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

	public static void flushReports() {

		if (Objects.nonNull(extent)) {
			extent.flush();
		}

		extentTest.remove();
	}

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
	 * To log the given message to the reporter at DEBUG level
	 *
	 * @param event
	 */
	public static void debug(String event) {
		extentTest.get().log(Status.WARNING, event);
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
	public static void fail(Markup m,ITestResult result) {
		takeScreenShot(result);
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
	public static void skip(Markup m,ITestResult result) {
		takeScreenShot(result);
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

	public static void extentTestStart(ITestResult result) {
		initReports();
		ExtentTest test = extent.createTest(result.getTestClass().getName() + " :: " + 
				result.getMethod().getMethodName());
		extentTest.set(test);
		
	}
	
	private static void takeScreenShot(ITestResult result) {
		try {
		TakesScreenshot ts = (TakesScreenshot) DriverManager.getDriver();
		File source = ts.getScreenshotAs(OutputType.FILE);
		String filepath = System.getProperty("user.dir") + "/TestReport/FailuresScreens/" + result.getTestClass()
				+ "/" + result.getName() + ".png";
		
		FileUtils.copyFile(source, new File(filepath));
		extentTest.get().addScreenCaptureFromPath(filepath);
		} catch (IOException e) {
			Log.exception(e);
		}
	}

}
