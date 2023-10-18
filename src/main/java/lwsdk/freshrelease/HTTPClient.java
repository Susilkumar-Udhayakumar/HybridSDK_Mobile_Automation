package lwsdk.freshrelease;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lwsdk.app.logger.Log;

public class HTTPClient {
	private RequestSpecification spec;

	public HTTPClient(RequestSpecification spec) {
		// TODO Auto-generated constructor stub
		Log.message("Instantiating HTTPClient with Client Request Specification");
		this.spec = spec;
	}

	/*
	 * Returns response of POST API method execution
	 * 
	 * @param url - end point
	 * 
	 * @param body - request body as JSON String
	 * 
	 * @return - response of POST API method execution
	 */
	public Response POST(String url, String body) throws URISyntaxException {
		Response response;

		Log.message("Sending POST Request... URL: ' " + url + " '. Body '" + body + "'");
		response = RestAssured.given().spec(this.spec).body(body).post(new URI(url));
		Log.message("POST Request sent. Status Code: " + response.getStatusCode() + ". Time taken to get response: "
				+ response.getTimeIn(TimeUnit.SECONDS) + " second(s)");
		Log.message("Response: " + response.asString());
		return response;
	}

	/*
	 * Returns response of PUT API method execution
	 * 
	 * @param url - end point
	 * 
	 * @param body - request body as JSON String
	 * 
	 * @return - response of PUT API method execution
	 */
	public Response PUT(String url, String body) throws URISyntaxException {
		Response response;

		Log.message("Sending PUT Request... URL: ' " + url + " '. Body '" + body + "'");
		response = RestAssured.given().spec(this.spec).body(body).put(new URI(url));
		Log.message("PUT Request sent. Status Code: " + response.getStatusCode() + ". Time taken to get response: "
				+ response.getTimeIn(TimeUnit.SECONDS) + " second(s)");
		Log.message("Response: " + response.asString());
		return response;
	}

	/*
	 * Returns response of PUT API method execution
	 * 
	 * @param url - end point
	 * 
	 * @return - response of PUT API method execution
	 */
	public Response PUT(String url) throws URISyntaxException {
		Response response;

		Log.message("Sending PUT Request... URL: ' " + url);
		response = RestAssured.given().spec(this.spec).put(new URI(url));
		Log.message("PUT Request sent. Status Code: " + response.getStatusCode() + ". Time taken to get response: "
				+ response.getTimeIn(TimeUnit.SECONDS) + " second(s)");
		Log.message("Response: " + response.asString());
		return response;
	}

	/*
	 * Returns response of PATCH API method execution
	 * 
	 * @param url - end point
	 * 
	 * @param body - request body as JSON String
	 * 
	 * @return - response of PATCH API method execution
	 */
	public Response PATCH(String url, String body) throws URISyntaxException {
		Response response;

		Log.message("Sending PATCH Request... URL: ' " + url + " '. Body '" + body + "'");
		response = RestAssured.given().spec(this.spec).body(body).patch(new URI(url));
		Log.message("PATCH Request sent. Status Code: " + response.getStatusCode() + ". Time taken to get response: "
				+ response.getTimeIn(TimeUnit.SECONDS) + " second(s)");
		Log.message("Response: " + response.asString());
		return response;
	}

	/*
	 * Returns response of GET API method execution
	 * 
	 * @param url - end point
	 * 
	 * @return - response of GET API method execution
	 */
	public Response GET(String url) throws URISyntaxException {
		Response response;

		Log.message("Sending GET Request... URL: ' " + url + " '");
		response = RestAssured.given().spec(this.spec).get(new URI(url));
		Log.message("GET Request sent. Status Code: " + response.getStatusCode() + ". Time taken to get response: "
				+ response.getTimeIn(TimeUnit.SECONDS) + " second(s)");
		Log.message("Response: " + response.asString());
		return response;
	}

	/*
	 * Returns response of GET API method execution
	 * 
	 * @param url - end point
	 * 
	 * @param query string - query parameters
	 * 
	 * @return - response of GET API method execution
	 */
	public Response GET(String url, Map<String, String> queryString) {
		Response response;

		Log.message("Sending GET Request... URL: ' " + url + " '");
		response = RestAssured.given().spec(this.spec).queryParams(queryString).get(url);
		Log.message("GET Request sent. Status Code: " + response.getStatusCode() + ". Time taken to get response: "
				+ response.getTimeIn(TimeUnit.SECONDS) + " second(s)");
		Log.message("Response: " + response.asString());
		return response;
	}

	/*
	 * Returns response of DELETE API method execution
	 * 
	 * @param url - end point
	 * 
	 * @param body - request body as JSON String
	 * 
	 * @return - response of DELETE API method execution
	 */
	public Response DELETE(String url) throws URISyntaxException {
		Response response;

		Log.message("Sending DELETE Request... URL: ' " + url + " '");
		response = RestAssured.given().spec(this.spec).delete(new URI(url));
		Log.message("DELETE Request sent. Status Code: " + response.getStatusCode()
				+ ". Time taken to get response: " + response.getTimeIn(TimeUnit.SECONDS) + " second(s)");
		return response;
	}
}
