package lwsdk.freshrelease;

import java.util.ArrayList;
import java.util.List;

public enum TestManagementClient {
	FRESHRELEASE;

	/**
	 * Method to get supported clients
	 * 
	 * @return list of clients
	 */
	public static List<String> getSupportedClients() {
		List<String> clients;

		clients = new ArrayList<String>();
		for (TestManagementClient client : TestManagementClient.values()) {
			clients.add(client.toString());
		}
		return clients;
	}

}
