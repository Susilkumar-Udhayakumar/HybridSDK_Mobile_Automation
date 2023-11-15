package com.freshchat.lwsdk.app.base.impl;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.freshchat.lwsdk.app.logger.Log;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredBase {
	
	public RequestSpecification reqSpec;

	public void setLogs() {
		reqSpec = RestAssured.given().log().all();
	}

	public void setQueryParam(Map<String, String> queryParams) {
		reqSpec.queryParams(queryParams);
	}

	public void setFormParam(Map<String, String> formParams) {
		reqSpec.formParams(formParams);
	}

	public void addFile(String fileName, File file) {
		reqSpec.multiPart(fileName, file);
	}

	public Response postWithBody(String jsonBody, String endpoint) {
		
		Log.info("Sending POST Request... URL: ' " + endpoint + " '. Body '" + jsonBody + "'");
		Response response = reqSpec.body(jsonBody).when().post(endpoint);
		Log.info("POST Request sent. Status Code: " + response.getStatusCode() + ". Time taken to get response: "
				+ response.getTimeIn(TimeUnit.SECONDS) + " second(s)");
		Log.info("Response: " + response.asString());
		return response;
	}

	public Response putWithBody(String jsonBody, String endpoint) {
		Log.info("Sending PUT Request... URL: ' " + endpoint + " '. Body '" + jsonBody + "'");
		Response response = reqSpec.body(jsonBody).when().put(endpoint);
		Log.info("PUT Request sent. Status Code: " + response.getStatusCode() + ". Time taken to get response: "
				+ response.getTimeIn(TimeUnit.SECONDS) + " second(s)");
		Log.info("Response: " + response.asString());
		return response;
	}

	public Response put(String endpoint) {
		Log.info("Sending PUT Request... URL: ' " + endpoint + "'");
		Response response = reqSpec.when().put(endpoint);
		Log.info("PUT Request sent. Status Code: " + response.getStatusCode() + ". Time taken to get response: "
				+ response.getTimeIn(TimeUnit.SECONDS) + " second(s)");
		Log.info("Response: " + response.asString());
		return response;
	}

	public Response post(String endpoint) {
		Log.info("Sending POST Request... URL: ' " + endpoint + "'");
		Response response = reqSpec.when().post(endpoint);
		Log.info("POST Request sent. Status Code: " + response.getStatusCode() + ". Time taken to get response: "
				+ response.getTimeIn(TimeUnit.SECONDS) + " second(s)");
		Log.info("Response: " + response.asString());
		return response;
	}
	
	public Response delete(String endpoint) {
		Log.info("Sending DELETE Request... URL: ' " + endpoint + "'");
		Response response = reqSpec.when().delete(endpoint);
		Log.info("DELETE Request sent. Status Code: " + response.getStatusCode() + ". Time taken to get response: "
				+ response.getTimeIn(TimeUnit.SECONDS) + " second(s)");
		Log.info("Response: " + response.asString());
		return response;
	}

	public Response get(String endpoint) {
		Log.info("Sending GET Request... URL: ' " + endpoint + "'");
		Response response = reqSpec.when().get(endpoint);
		Log.info("GET Request sent. Status Code: " + response.getStatusCode() + ". Time taken to get response: "
				+ response.getTimeIn(TimeUnit.SECONDS) + " second(s)");
		Log.info("Response: " + response.asString());
		return response;
	}

	public void setHeader(List<Header> header) {
		Headers headers = new Headers(header);
		reqSpec.headers(headers);
	}

	public void setContentType(String contentType) {
		if (contentType == "JSON") {
			reqSpec.contentType(ContentType.JSON);
		} else if (contentType == "XML") {
			reqSpec.contentType(ContentType.XML);
		} else if (contentType == "MULTIPART") {
			reqSpec.contentType(ContentType.MULTIPART);
		}
	}

	public void setAccept(String contentType) {
		if (contentType == "JSON") {
			reqSpec.accept(ContentType.JSON);
		} else if (contentType == "XML") {
			reqSpec.accept(ContentType.XML);
		}
	}

}
