package lwsdk.clients;

import lwsdk.base.TestManagementBase;
import lwsdk.utils.FreshReleaseProperties;

public class TestManagementFactory {
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
