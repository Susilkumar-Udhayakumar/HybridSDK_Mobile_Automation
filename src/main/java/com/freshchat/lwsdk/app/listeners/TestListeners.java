package com.freshchat.lwsdk.app.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.freshchat.lwsdk.app.logger.Log;
import com.freshchat.lwsdk.freshrelease.FreshReleaseBase;

public class TestListeners implements ITestListener, ISuiteListener {
	
	FreshReleaseBase frBase = new FreshReleaseBase();
	
	/**
	 *Method to executed while starting test suite run
	 */
	@Override
	public void onStart(ISuite suite) { 
		frBase.onFreshReleaseStart(suite);
		Log.info("Start ItestContest");
	}
	
	/**
	 *Method to executed while starting test case run
	 *
	 *@param test result
	 */
	@Override
	public void onTestStart(ITestResult result) {
		Log.testStart(result);		
	}
	
	/**
	 *Method to executed after test run success
	 *
	 *@param test result
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		Log.pass(result);
		frBase.testFreshReleaseSuccess(result);
	}
	
	/**
	 *Method to executed after test run skipped
	 *
	 *@param test result
	 */
	@Override
	public void onTestSkipped(ITestResult result) {
		Log.skip(result);
		frBase.testFreshReleaseSkipped(result);	
	}
	
	/**
	 *Method to executed after test run fail
	 *
	 *@param test result
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		Log.fail(result);
		frBase.testFreshReleaseFailure(result);	
	}
	
	
	/**
	 *Method to executed after test suit completes
	 *
	 *@param test result
	 */
	@Override
	public void onFinish(ITestContext context) { // Isuite 
		Log.testEnd();
		frBase.afterFreshReleaseClass();
	}

}
