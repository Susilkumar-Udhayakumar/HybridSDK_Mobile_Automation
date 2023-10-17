package lwsdk.app.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TestManagementBase  extends ClientManagementBase {
	public void updateCaseResultInRun(Map<String, Object> testResult);

	public void bulkUpdateCaseResultInRun(List<Map<String, Object>> testResults);

	public List<HashMap<String, ?>> listAllCasesInRun();

	public void markAllTestCasesAsUnTested();

	public List<Map<String, ?>> listAllStatus();

	public Map<String, Integer> listAllTestResultStatus();

	public void setStatus(Map<String, Integer> status);
}
