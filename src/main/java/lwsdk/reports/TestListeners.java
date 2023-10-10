package lwsdk.reports;

import org.testng.IClassListener;
import org.testng.ITestClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import lwsdk.utils.FreshReleaseBase;

public class TestListeners implements ITestListener, IClassListener {
	
	FreshReleaseBase frBase = new FreshReleaseBase();
	
	@Override
	public void onStart(ITestContext context) {
		frBase.onFreshReleaseStart(context);
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		Log.testStart(result);		
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		Log.pass(result);
		frBase.testFreshReleaseSuccess(result);
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		Log.skip(result);
		frBase.testFreshReleaseSkipped(result);	
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		Log.fail(result);
		frBase.testFreshReleaseFailure(result);	
	}
	
	@Override
	public void onAfterClass(ITestClass testClass) {
		frBase.afterFreshReleaseClass();	
	}
	
	@Override
	public void onFinish(ITestContext context) {
		Log.testEnd();
	}

}
