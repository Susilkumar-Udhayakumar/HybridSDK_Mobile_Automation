package lwsdk.app.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TestManagementBase  extends ClientManagementBase {
	
	/**
	 * Method to update test case result in test run
	 * 
	 * @param testResult
	 */
	public void updateCaseResultInRun(Map<String, Object> testResult);

	/**
	 * Method to update bulk test case result
	 * 
	 * @param testResults
	 */
	public void bulkUpdateCaseResultInRun(List<Map<String, Object>> testResults);

	/**
	 * Method to list all test case
	 * 
	 * @return list of test case id with status
	 */
	public List<HashMap<String, ?>> listAllCasesInRun();

	/**
	 * Method all test case as untested
	 */
	public void markAllTestCasesAsUnTested();

	/**
	 * Method to list all status
	 * 
	 * @return list with status
	 */
	public List<Map<String, ?>> listAllStatus();

	/**
	 * Method to list all test result with status
	 * 
	 * @return list of test result with status
	 */
	public Map<String, Integer> listAllTestResultStatus();

	/**
	 * Method to set status
	 * 
	 * @param status
	 */
	public void setStatus(Map<String, Integer> status);
}
