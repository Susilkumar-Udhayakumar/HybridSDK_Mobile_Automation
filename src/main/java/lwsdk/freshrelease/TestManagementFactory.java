package lwsdk.freshrelease;

import lwsdk.app.base.TestManagementBase;

public class TestManagementFactory {
	/**
	 * Test management base method to client details
	 * 
	 * @param client
	 * @return
	 */
	public static TestManagementBase getClient(TestManagementClient client) {
		switch (client) {
		case FRESHRELEASE:
			return new FreshReleaseClient.FreshReleaseBuilder().setBaseURI(FreshReleaseProperties.baseURI)
					.setBasePath(FreshReleaseProperties.projectKey).setAccessToken(FreshReleaseProperties.accessToken)
					.build();
		default:
			throw new RuntimeException("Unsupported Test Management client.");
		}
	}

}
