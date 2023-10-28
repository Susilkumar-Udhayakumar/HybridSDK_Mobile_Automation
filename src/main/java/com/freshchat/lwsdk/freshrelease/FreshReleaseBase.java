package com.freshchat.lwsdk.freshrelease;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.IClass;
import org.testng.ISuite;
import org.testng.ITestResult;

import com.freshchat.lwsdk.app.base.TestManagementBase;
import com.freshchat.lwsdk.app.logger.Log;
import com.freshchat.lwsdk.freshrelease.annotation.TestData;

public class FreshReleaseBase {

	private TestManagementBase client;
	private int runId = 0;
	private List<Map<String, Object>> caseResults;

	public void onFreshReleaseStart(ISuite context) {
		String testManagementClient = "FRESHRELEASE";

		if (System.getProperty("RUN_ID") != null) {
			runId = Integer.parseInt(System.getProperty("RUN_ID"));
		}

		if (runId != 0) {
			caseResults = new ArrayList<>();
			try {
				testManagementClient = testManagementClient.toUpperCase();
				switch (TestManagementClient.valueOf(testManagementClient)) {
				case FRESHRELEASE:
					FreshReleaseProperties.setRunId(runId);
					Log.message(testManagementClient);
					client = TestManagementFactory.getClient(TestManagementClient.FRESHRELEASE);
					break;
				default:
					throw new RuntimeException("Unsupported Test Management client.");
				}
			} catch (Exception e) {
				throw new RuntimeException(
						"Unsupported Test Management client. Currently we provide support for following Test Management clients alone. "
								+ TestManagementClient.getSupportedClients());
			}
			Log.debug("Setting up all applicable TestCase result statuses of client.");
			client.setStatus(client.listAllTestResultStatus());
			if (Boolean.parseBoolean(System.getProperty("RESET_TEST_STATUS"))) {
				Log.debug("Marking all Test Cases in Test Run " + runId + " as UnTested.");
				client.markAllTestCasesAsUnTested();
			}
		}

	}

	public void testFreshReleaseSuccess(ITestResult result) {
		Map<String, Object> caseResult;
		if (runId != 0) {
			IClass testClassName = result.getTestClass();
			Class<?> className = testClassName.getRealClass();
			try {
				Method testMethod = className.getMethod(result.getName());
				if (testMethod.isAnnotationPresent(TestData.class)) {
					TestData testCase = testMethod.getAnnotation(TestData.class);
					caseResult = new LinkedHashMap<String, Object>();
					caseResult.put("Case_ID", testCase.testId());
					caseResult.put("Status", TestManagementStatus.TestStatus.PASSED);
					caseResults.add(caseResult);
				}
			} catch (Exception e) {
				Log.exception(e);
			}
		}
	}

	public void testFreshReleaseSkipped(ITestResult result) {
		Map<String, Object> caseResult;
		if (runId != 0) {
			IClass testClassName = result.getTestClass();
			Class<?> className = testClassName.getRealClass();
			try {
				Method testMethod = className.getMethod(result.getName());
				if (testMethod.isAnnotationPresent(TestData.class)) {
					TestData testCase = testMethod.getAnnotation(TestData.class);
					caseResult = new LinkedHashMap<String, Object>();
					caseResult.put("Case_ID", testCase.testId());
					caseResult.put("Status", TestManagementStatus.TestStatus.FAILED);
					caseResult.put("Comment", ExceptionUtils.getStackTrace(result.getThrowable()));
					caseResults.add(caseResult);
				}
			} catch (Exception e) {
				Log.exception(e);
			}
		}

	}

	public void testFreshReleaseFailure(ITestResult result) {
		Map<String, Object> caseResult;
		if (runId != 0) {
			IClass testClassName = result.getTestClass();
			Class<?> className = testClassName.getRealClass();
			try {
				Method testMethod = className.getMethod(result.getName());
				if (testMethod.isAnnotationPresent(TestData.class)) {
					TestData testCase = testMethod.getAnnotation(TestData.class);
					caseResult = new LinkedHashMap<String, Object>();
					caseResult.put("Case_ID", testCase.testId());
					caseResult.put("Status", TestManagementStatus.TestStatus.FAILED);
					caseResult.put("Comment", ExceptionUtils.getStackTrace(result.getThrowable()));
					caseResults.add(caseResult);
				}
			} catch (Exception e) {
				Log.exception(e);
			}
		}
	}

	public void afterFreshReleaseClass() {
		if (runId != 0) {
			client.bulkUpdateCaseResultInRun(caseResults);
			caseResults = new ArrayList<>();
		}
	}

}
