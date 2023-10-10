package lwsdk.clients;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jayway.restassured.authentication.AuthenticationScheme;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.internal.http.HTTPBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import lwsdk.base.TestManagementBase;
import lwsdk.common.EndPoints;
import lwsdk.reports.Log;
import lwsdk.utils.FreshReleaseProperties;

public class FreshReleaseClient implements TestManagementBase {
	private enum FreshReleaseStatus implements TestManagementStatus {
		PASSED("Passed"), FAILED("Failed"), BLOCKED("Blocked"), UNTESTED("Untested"), IN_PROGRESS(
				"In Progress"), NOT_TESTED("Not Tested");

		private String label;

		private FreshReleaseStatus(String label) {
			this.label = label;
		}

		private static String getLabel(TestStatus consumerStatus) {
			for (FreshReleaseStatus status : FreshReleaseStatus.values()) {
				if (consumerStatus.name() == status.name()) {
					return status.label;
				}
			}
			return null;
		}
	}

	private final String casePrefix;
	private String baseURI, basePath, accessToken;
	private ContentType contentType, acceptContentType;
	private RequestSpecification spec;
	private HTTPClient apiClient;
	private Map<String, Integer> status;
	private int runId;

	private FreshReleaseClient(FreshReleaseBuilder builder) {
		// TODO Auto-generated constructor stub
		this.casePrefix = "TC-";
		this.baseURI = builder.getBaseURI();
		this.basePath = builder.getBasePath();
		this.accessToken = builder.getAccessToken();
		this.contentType = builder.getContentType();
		this.acceptContentType = builder.getAcceptContentType();
		this.runId = builder.getRunId();
		setSpec(generateClientSpecification());
		apiClient = new HTTPClient(getSpec());
	}

	public static class FreshReleaseBuilder {
		private String baseURI, basePath, accessToken;
		private ContentType contentType, acceptContentType;
		private int runId;

		public FreshReleaseBuilder() {
			// TODO Auto-generated constructor stub
			this.contentType = ContentType.JSON;
			this.acceptContentType = ContentType.JSON;
			this.runId = FreshReleaseProperties.runId;
		}

		public String getBaseURI() {
			return baseURI;
		}

		public FreshReleaseBuilder setBaseURI(String baseURI) {
			this.baseURI = baseURI;
			return this;
		}

		public String getBasePath() {
			return basePath;
		}

		public FreshReleaseBuilder setBasePath(String basePath) {
			this.basePath = basePath;
			return this;
		}

		public String getAccessToken() {
			return accessToken;
		}

		public FreshReleaseBuilder setAccessToken(String accessToken) {
			accessToken = "Token token=" + accessToken;
			this.accessToken = accessToken;
			return this;
		}

		public ContentType getContentType() {
			return contentType;
		}

		public FreshReleaseBuilder setContentType(ContentType contentType) {
			this.contentType = contentType;
			return this;
		}

		public ContentType getAcceptContentType() {
			return acceptContentType;
		}

		public FreshReleaseBuilder setAcceptContentType(ContentType acceptContentType) {
			this.acceptContentType = acceptContentType;
			return this;
		}

		public int getRunId() {
			return runId;
		}

		public FreshReleaseBuilder setRunId(int runId) {
			this.runId = runId;
			return this;
		}

		public FreshReleaseClient build() {
			return new FreshReleaseClient(this);
		}

	}

	private static class NoAuthScheme implements AuthenticationScheme {
		@Override
		public void authenticate(HTTPBuilder httpBuilder) {
		}
	}

	@Override
	public RequestSpecification generateClientSpecification() {
		RequestSpecification spec;

		spec = new RequestSpecBuilder().setBaseUri(this.baseURI).setBasePath("/" + this.basePath)
				.setAccept(this.acceptContentType).setContentType(this.contentType)
				.setAuthentication(new NoAuthScheme()).addHeader("Authorization", this.accessToken).build();
		return spec;
	}

	@Override
	public void setSpec(RequestSpecification spec) {
		// TODO Auto-generated method stub
		this.spec = spec;
	}

	@Override
	public RequestSpecification getSpec() {
		return this.spec;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap<String, ?>> listAllCasesInRun() {
		// TODO Auto-generated method stub
		Response testCasesResp;
		Map<String, ?> testMetaCases;
		Map<String, String> queryString;
		List<HashMap<String, ?>> cases;

		try {
			cases = new ArrayList<HashMap<String, ?>>();
			queryString = new LinkedHashMap<String, String>();
			for (int pageNumb = 1;; pageNumb++) {
				queryString.put("test_run_id", String.valueOf(this.runId));
				queryString.put("include", "test_case");
				queryString.put("page", String.valueOf(pageNumb));
				queryString.put("per_page", String.valueOf(250));
				testCasesResp = apiClient.GET(EndPoints.FreshRelease.GET_CASES_IN_RUN, queryString);
				testMetaCases = testCasesResp.path("");
				validateResponse(testCasesResp, 200);
				List<HashMap<String, ?>> testRunCasesList = (List<HashMap<String, ?>>) testMetaCases
						.get("test_run_cases");
				if (testRunCasesList.size() > 0) {
					List<HashMap<String, ?>> casesList = (List<HashMap<String, ?>>) testMetaCases.get("test_cases");
					cases.addAll(casesList);
				} else {
					break;
				}
			}
			return cases;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void markAllTestCasesAsUnTested() {
		// TODO Auto-generated method stub
		Response resetRsltResp;

		Log.message("Resetting TestCases result to UnTested in Run " + this.runId);
		try {
			resetRsltResp = apiClient
					.PUT(EndPoints.FreshRelease.TEST_RUNS + "/" + this.runId + EndPoints.FreshRelease.RESET_RESULTS);
			validateResponse(resetRsltResp, 202);
		} catch (Exception e) {
			Log.exception(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateCaseResultInRun(Map<String, Object> testResult) {
		// TODO Auto-generated method stub
		Response testRsltUpdResp;
		JSONObject caseRslt, caseInfo;

		caseInfo = new JSONObject();
		caseInfo.put("test_case_status_id",
				status.get(FreshReleaseStatus.getLabel(TestManagementStatus.TestStatus.valueOf(testResult.get("Status").toString()))));
		if (testResult.containsKey("Comment")) {
			caseInfo.put("comment", sanitiseTestResultComment(testResult.get("Comment").toString()));
		}
		caseRslt = new JSONObject();
		caseRslt.put("test_run_case_result", caseInfo);
		Log.message("Sending update Payload ---> " + caseRslt.toJSONString());
		try {
			testRsltUpdResp = apiClient.POST(
					EndPoints.FreshRelease.TEST_RUNS + "/" + this.runId + EndPoints.FreshRelease.TEST_CASES + "/"
							+ this.casePrefix + testResult.get("Case_ID") + EndPoints.FreshRelease.RESULT,
					caseRslt.toJSONString());
			validateResponse(testRsltUpdResp, 200);
		} catch (Exception e) {
			Log.exception(e);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public void bulkUpdateCaseResultInRun(List<Map<String, Object>> testResults) {
		JSONArray testResultsList;
		JSONObject testResultDetails;
		Response bulkUpdRsltResp;

		int resultSetSize = testResults.size();
		if (testResults.size() > 0) {
			int partitionSize = 250;

			for (int startIndex = 0, endIndex; startIndex < resultSetSize; startIndex += partitionSize) {
				testResultDetails = new JSONObject();
				testResultsList = new JSONArray();
				endIndex = Math.min(startIndex + partitionSize, resultSetSize);
				List<Map<String, Object>> testRsltPartion = testResults.subList(startIndex, endIndex);
				for (Map<String, Object> resultPartion : testRsltPartion) {
					JSONObject caseResultDetails = new JSONObject();

					caseResultDetails.put("test_case_key", this.casePrefix + resultPartion.get("Case_ID"));
					caseResultDetails.put("test_case_status_id", status.get(
							FreshReleaseStatus.getLabel(TestManagementStatus.TestStatus.valueOf(resultPartion.get("Status").toString()))));
					if (resultPartion.containsKey("Comment")) {
						caseResultDetails.put("comment", sanitiseTestResultComment(resultPartion.get("Comment").toString()));
					}
					testResultsList.add(caseResultDetails);
				}
				testResultDetails.put("test_run_case_results", testResultsList);
				try {
					Log.message("Sending bulk update Payload ---> " + testResultDetails.toJSONString());
					bulkUpdRsltResp = apiClient.POST(
							EndPoints.FreshRelease.TEST_RUNS + "/" + this.runId + EndPoints.FreshRelease.RESULTS,
							testResultDetails.toJSONString());
					validateResponse(bulkUpdRsltResp, 202);
				} catch (Exception e) {
					Log.exception(e);
				}
			}
		}
	}
	
	private static String sanitiseTestResultComment(String comment) {
		return comment.replaceAll("<", "[").replaceAll(">", "]").replaceAll("<", "[").replaceAll(">", "]");
	}
	
	@Override
	public Map<String, Integer> listAllTestResultStatus() {
		// TODO Auto-generated method stub
		List<Map<String, ?>> statuses = listAllStatus();
		Map<String, Integer> caseStatuses;

		caseStatuses = new LinkedHashMap<String, Integer>();
		for (Map<String, ?> status : statuses) {
			caseStatuses.put(status.get("label").toString(), Integer.parseInt(status.get("id").toString()));
		}
		return caseStatuses;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, ?>> listAllStatus() {
		// TODO Auto-generated method stub
		Response availStatusesResp;
		Map<String, ?> testMetaStatus;
		List<Map<String, ?>> statuses;

		try {
			availStatusesResp = apiClient.GET(EndPoints.FreshRelease.CASE_STATUS);
			validateResponse(availStatusesResp, 200);
			testMetaStatus = availStatusesResp.path("");
			statuses = (List<Map<String, ?>>) testMetaStatus.get("test_case_statuses");
			return statuses;
		} catch (Exception e) {
			Log.exception(e);
		}
		return null;
	}

	@Override
	public void setStatus(Map<String, Integer> status) {
		// TODO Auto-generated method stub
		this.status = status;
	}

	private void validateResponse(Response response, int expectedStatusCode) {
		if (response.getStatusCode() != expectedStatusCode) {
			throw new RuntimeException("Unexpected API Response ---> (Expected:" + expectedStatusCode
					+ ") <=> (Actual:" + response.getStatusCode() + ")" + System.lineSeparator()
					+ "Failed with Response message:" + System.lineSeparator() + "------------------------------"
					+ System.lineSeparator() + response.getBody().asString());
		}
	}

}
