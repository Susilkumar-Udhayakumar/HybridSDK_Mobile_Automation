package lwsdk.app.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import lwsdk.app.logger.Log;
import lwsdk.freshrelease.FreshReleaseBase;

public class TestListeners implements ITestListener {
	
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
	public void onFinish(ITestContext context) {
		Log.testEnd();
		frBase.afterFreshReleaseClass();
	}

}
