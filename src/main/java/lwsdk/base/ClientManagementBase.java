package lwsdk.base;

import com.jayway.restassured.specification.RequestSpecification;

public interface ClientManagementBase {
	
	RequestSpecification generateClientSpecification();

	void setSpec(RequestSpecification spec);

	RequestSpecification getSpec();

}
