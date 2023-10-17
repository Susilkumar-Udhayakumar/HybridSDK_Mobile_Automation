package lwsdk.freshrelease;

import java.util.ArrayList;
import java.util.List;

public enum TestManagementClient {
	FRESHRELEASE;

	public static List<String> getSupportedClients() {
		List<String> clients;

		clients = new ArrayList<String>();
		for (TestManagementClient client : TestManagementClient.values()) {
			clients.add(client.toString());
		}
		return clients;
	}

}
