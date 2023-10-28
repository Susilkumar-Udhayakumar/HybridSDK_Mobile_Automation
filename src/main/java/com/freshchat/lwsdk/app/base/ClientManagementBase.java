package com.freshchat.lwsdk.app.base;

import io.restassured.specification.RequestSpecification;

public interface ClientManagementBase {
	
	RequestSpecification generateClientSpecification();

	/**
	 * Method to set request specification 
	 * @param spec
	 */
	void setSpec(RequestSpecification spec);

	/**
	 * Method to get request specification
	 * @return
	 */
	RequestSpecification getSpec();

}
