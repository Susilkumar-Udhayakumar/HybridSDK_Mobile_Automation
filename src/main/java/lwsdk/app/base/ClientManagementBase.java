package lwsdk.app.base;

import io.restassured.specification.RequestSpecification;

public interface ClientManagementBase {
	
	RequestSpecification generateClientSpecification();

	void setSpec(RequestSpecification spec);

	RequestSpecification getSpec();

}
