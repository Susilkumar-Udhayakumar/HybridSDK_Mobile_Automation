package com.freshchat.lwsdk.freshrelease.endpoints;

final public class EndPoints {

	public static class FreshRelease {
		public static final String TEST_RUNS = "/test_runs";
		public static final String TEST_CASES = "/test_cases";
		public static final String CASE_STATUS = "/test_case_statuses";
		public static final String GET_CASES_IN_RUN = "/test_run_cases";
		public static final String RESULT = "/result";
		public static final String RESULTS = "/results";
		public static final String RESET_RESULTS = "/reset_result";
	}
	
	public static class V3{
		public static final String DOMAIN = "login/mobile/org";
		public static final String AUTHTOKEN = "mobile/auth/token";
	}
	
	public static class Public {
		public static final String QUICK_ACCESS_FILES = "file/access/quick";
		public static final String FRESHDESKTICKETS = "freshdesk/tickets";
		public static final String CHANNELS = "channels";
		public static final String AUTORESOLVE = "settings/autoresolve/v2";
		public static final String USERINFOV3 = "user_info/v3";
		public static final String USERINFOV2 = "user_info/v2";
		public static final String LABELS = "labels";
		public static final String CANNED_RESPONSE_CATEGORY = "canned_response/category";
		public static final String USERMETA = "usermeta";
		public static final String FILEDOWNLOAD = "file/download";
		public static final String TICKETDETAILS = "freshdesk/tickets";
		public static final String FRESHDESKPROPERTIES = "mobile/freshdesk/properties";
		public static final String FRESHSALESPROPERTIES = "freshsales/properties";
		public static final String FRESHSALESVIEWLEAD = "fsleads";
		public static final String FRESHSALESCREATELEAD = "freshsales/createnewlead";
	}


}
