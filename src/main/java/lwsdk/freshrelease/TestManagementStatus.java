package lwsdk.freshrelease;

public interface TestManagementStatus {
	public enum TestStatus {
		PASSED, FAILED, BLOCKED, UNTESTED, IN_PROGRESS, NOT_TESTED;
	}
}
