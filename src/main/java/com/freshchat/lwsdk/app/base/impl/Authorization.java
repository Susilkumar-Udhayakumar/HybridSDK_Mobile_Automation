package com.freshchat.lwsdk.app.base.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.testng.Assert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freshchat.lwsdk.app.V3AuthTokenBody;
import com.freshchat.lwsdk.freshrelease.endpoints.EndPoints;

import io.restassured.RestAssured;
import io.restassured.config.RedirectConfig;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Authorization extends MobileWrapperImplementation {

	String baseURL;
	String userName;

	String codeVerifier = generateCodeVerifier();
	String codeChallenge = generateCodeChallenge(codeVerifier);
	RestAssuredBase baseRestAssured = new RestAssuredBase();

	public Authorization() {

	}

	public Authorization(String baseURL, String userName) {
		this.baseURL = "https://" + baseURL + "/";
		this.userName = userName;
	}

	public static String generateCodeVerifier() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] codeVerifier = new byte[32];
		secureRandom.nextBytes(codeVerifier);

		return java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(codeVerifier);
	}

	public static String generateCodeChallenge(String codeVerifier) {
		byte[] bytes = null;
		MessageDigest messageDigest = null;
		try {
			bytes = codeVerifier.getBytes("US-ASCII");
			messageDigest = MessageDigest.getInstance("SHA-256");
		} catch (Exception e) {
			e.printStackTrace();
		}

		messageDigest.update(bytes, 0, bytes.length);
		byte[] digest = messageDigest.digest();

		return java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
	}

	public void updateOrgDomainAndMobileClientId(String propertyPath, String env)
			throws FileNotFoundException, ConfigurationException, IOException {
		prop.load(new FileInputStream(new File(System.getProperty("user.dir") + "/src/test/resources/properties/"
				+ propertyPath + "/" + env + ".properties")));
		RestAssured.baseURI = "https://" + prop.getProperty("agentServer") + "/";
		RestAssured.basePath = prop.getProperty("agentV3Resources");
		Response response = RestAssured.given()
				.config(RestAssured.config().redirect(RedirectConfig.redirectConfig().followRedirects(false))).log()
				.all().queryParam("domain", prop.getProperty("agentServer")).queryParam("platform", "android")
				.queryParam("deeplink", "com.freshchat.agent.android").get("/login/mobile/org");
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);
		JsonPath jsonPath = response.jsonPath();
		String loginURl = jsonPath.getString("domainPack.loginUrl");
		String orgDomain = jsonPath.getString("domainPack.orgDomain");
		String[] splitDomain = loginURl.split("client_id=")[1].split("&");
		updatePropertyFile("clientId", splitDomain[0], propertyPath, env);
		if (prop.getProperty("agentServer").contains("freshchat")) {
			orgDomain = orgDomain + ".myfreshworks.com";
		} else if (prop.getProperty("agentServer").contains("freshpori")) {
			orgDomain = orgDomain + ".myfreshworks.dev";
		}
		updatePropertyFile("orgDomainServer", orgDomain, propertyPath, env);
	}

	public void updateWebClientId(String propertyPath, String env)
			throws FileNotFoundException, ConfigurationException, IOException {

		RestAssured.baseURI = "https://" + prop.getProperty("agentServer") + "/";
		RestAssured.basePath = prop.getProperty("agentV3Resources");
		Response response = RestAssured.given()
				.config(RestAssured.config().redirect(RedirectConfig.redirectConfig().followRedirects(false))).log()
				.all().get("/login");
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 401);
		JsonPath jsonPath = response.jsonPath();
		String loginURl = jsonPath.getString("url");
		String[] splitDomain = loginURl.split("client_id=")[1].split("&");
		updatePropertyFile("loginCliendId", splitDomain[0], propertyPath, env);
	}

	public void updateMobileAuthToken(String propertyPath, String env) {
		Map<String, String> queryParams = new HashMap<>();

		RestAssured.baseURI = "https://" + prop.getProperty("agentServer");

		RestAssured.basePath = "/" + prop.getProperty("agentV3Resources") + "/";

		queryParams.put("appId", prop.getProperty("agentAppid"));
		RestAssured.given().log().all();
		String code = "";
		try {
			code = getMobileAuthCode(codeChallenge);
		} catch (Exception e) {
			e.printStackTrace();
		}
		V3AuthTokenBody v3AuthTokenBody = new V3AuthTokenBody();
		V3AuthTokenBody.DeviceDescription deviceDescription = v3AuthTokenBody.new DeviceDescription();
		deviceDescription.setIpAddress("");
		v3AuthTokenBody.setCode_verifier(codeVerifier);
		v3AuthTokenBody.setCode(code);
		v3AuthTokenBody.setRedirect_uri("com.freshchat.ios://login");
		v3AuthTokenBody.setDevice_description(deviceDescription);

		String v3AuthTokenBodyAsString = "";
		ObjectMapper objMap = new ObjectMapper();
		try {
			v3AuthTokenBodyAsString = objMap.writerWithDefaultPrettyPrinter().writeValueAsString(v3AuthTokenBody);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		String endPoint = EndPoints.V3.AUTHTOKEN;

		Response response = baseRestAssured.postWithBody(v3AuthTokenBodyAsString, endPoint);
		JsonPath jsonPath = response.jsonPath();
		Map<String, Object> auth = jsonPath.get();
		String token = (String) auth.get("authToken");
		try {
			updatePropertyFile("X-HL-Auth-Token", token, propertyPath, env);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateWebAuthToken(String propertyPath, String env) {
		String _hp2_id = "%7B%22userId%22%3A%225666621568402406%22%2C%22pageviewId%22%3A%222965438969882136%22%2C%22sessionId%22%3A%224902683480660433%22%2C%22identity%22%3Anull%2C%22trackerVersion%22%3A%224.0%22%7D	";
		String _hp2_ses_props = "%7B%22r%22%3A%22https%3A%2F%2F" + prop.getProperty("agentServer")
				+ "%2F%22%2C%22ts%22%3A1656510619501%2C%22d%22%3A%22"
				+ prop.getProperty("agentServer").replace("freshchat", "myfreshworks").replace("freshpori.com",
						"myfreshworks.dev")
				+ "%22%2C%22h%22%3A%22%2Flogin%22%2C%22q%22%3A%22%3Fresponse_type%3Dcode%26redirect_uri%3Dhttps%3A%2F%2F"
				+ prop.getProperty("agentServer") + "%2Fapp%2Fv3%2Flogin%2Funity_login%2Fredirect%26hd%3D"
				+ prop.getProperty("agentServer") + "%26prompt%3Dconsent%26client_id%3D"
				+ prop.getProperty("loginCliendId") + "%22%7D	";

		String xsrfToken = authorize("", "", "");
		xsrfToken = appInitPayload(xsrfToken, "", "", "", "");
		xsrfToken = current(xsrfToken, "", "", "", "");
		xsrfToken = image(xsrfToken, "", "", "", "", "ORG_LOGO");
		xsrfToken = image(xsrfToken, "", "", "", "", "ORG_FAVICON");
		Map<String, String> cookies = login(xsrfToken, "", _hp2_id, _hp2_ses_props);
		String redirectUrl = authorizeWeb(cookies, _hp2_id, _hp2_ses_props);
		String setInfoRedirectUrl = getLocationFromSetInfo(redirectUrl);
		Map<String, String> allTokens = getAllTokensWeb(setInfoRedirectUrl);
		StringBuilder cookie = new StringBuilder();
		for (Map.Entry<String, String> entry : allTokens.entrySet())
			cookie.append(entry.getKey() + "=" + entry.getValue() + "; ");
		try {
			updatePropertyFile("cookie", cookie.toString(), propertyPath, env);
			updatePropertyFile("xsrfToken", allTokens.get("FC-XSRF-TOKEN"), propertyPath, env);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String authorizeWeb(Map<String, String> cookies, String _hp2_id, String _hp2_ses_props) {

		RestAssured.baseURI = baseURL;
		RestAssured.basePath = "oauth";
		Response response = RestAssured.given()
				.config(RestAssured.config().redirect(RedirectConfig.redirectConfig().followRedirects(false))).log()
				.all()
				.cookies("_hp2_id.870388005", _hp2_id, "_hp2_ses_props.870388005", _hp2_ses_props, "XSRF-TOKEN",
						cookies.get("xsrfToken"), "_d", cookies.get("_d"))
				.queryParam("redirect_uri",
						"https://" + prop.getProperty("agentServer") + "/app/v3/login/unity_login/redirect")
				.queryParam("client_id", prop.getProperty("loginCliendId")).queryParam("response_type", "code").when()
				.get("authorize");
		response.prettyPrint();
		String redirect = response.getHeader("location");
		return redirect;
	}

	public String getLocationFromSetInfo(String redirectUrl) {
		String setInfoRedirectUrl = RestAssured.given()
				.config(RestAssured.config().redirect(RedirectConfig.redirectConfig().followRedirects(false))).log()
				.all().when().get(redirectUrl).getHeader("location");
		return setInfoRedirectUrl;
	}

	public Map<String, String> getAllTokensWeb(String redirectUrl) {
		RestAssured.baseURI = redirectUrl.substring(0, redirectUrl.indexOf("app"));
		RestAssured.basePath = redirectUrl.substring(redirectUrl.indexOf("app"), redirectUrl.indexOf("redirect"));
		Response response = RestAssured.given()
				.config(RestAssured.config().redirect(RedirectConfig.redirectConfig().followRedirects(false))).log()
				.all()
				.queryParam("code",
						redirectUrl.substring(redirectUrl.indexOf("code") + 5, redirectUrl.indexOf("code") + 23))
				.queryParam("session_state",
						redirectUrl.substring(redirectUrl.indexOf("session_state") + 14,
								redirectUrl.indexOf("code") + 145))
				.queryParam("session_token", redirectUrl.substring(redirectUrl.indexOf("session_token") + 14)).when()
				.get("redirect");
		System.out.println(response.getCookie("FC-FCAUTH"));
		System.out.println(response.getCookie(prop.getProperty("fcAppCookie")));
		System.out.println(response.getCookie("FC-XSRF-TOKEN"));
		response.prettyPrint();
		Map<String, String> auth = new HashMap<String, String>();
		auth.put("FC-FCAUTH", response.getCookie("FC-FCAUTH"));
		auth.put(prop.getProperty("fcAppCookie"), response.getCookie(prop.getProperty("fcAppCookie")));
		auth.put("FC-XSRF-TOKEN", response.getCookie("FC-XSRF-TOKEN"));
		return auth;
	}

	public String getMobileAuthCode(String codeChallenge)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {

		String _hp2_id = "%7B%22userId%22%3A%227544185180296042%22%2C%22pageviewId%22%3A%224508398770798967%22%2C%22sessionId%22%3A%223848594877207557%22%2C%22identity%22%3A%22320199496911182502%22%2C%22trackerVersion%22%3A%224.0%22%2C%22identityField%22%3Anull%2C%22isIdentified%22%3A1%2C%22oldIdentity%22%3Anull%7D";
		String _hp2_ses_props = "%7B%22ts%22%3A1656522501427%2C%22d%22%3A%22" + prop.getProperty("agentServer")
				+ "%22%2C%22h%22%3A%22%2Flogin%22%2C%22q%22%3A%22%3Fresponse_type%3Dcode%26redirect_uri%3Dcom.freshchat.ios%3A%2F%2Flogin%26code_challenge_method%3DS256%26prompt%3Dlogin%26client_id%3D"
				+ prop.get("clientId") + "%26code_challenge%3D" + codeChallenge + "%22%7D";

		String xsfrToken = authorize(codeChallenge, _hp2_id, _hp2_ses_props);
		xsfrToken = appInitPayload(xsfrToken, "", codeChallenge, _hp2_id, _hp2_ses_props);
		xsfrToken = current(xsfrToken, "", codeChallenge, _hp2_id, _hp2_ses_props);
		xsfrToken = image(xsfrToken, "", codeChallenge, _hp2_id, _hp2_ses_props, "ORG_LOGO");
		Map<String, String> cookies = login(xsfrToken, codeChallenge, _hp2_id, _hp2_ses_props);
		Map<String, String> values = authorizeMobileForLocation(cookies.get("xsrfToken"), cookies.get("_d"),
				codeChallenge, _hp2_id, _hp2_ses_props);
		xsfrToken = appInitPayload(values.get("xsrfToken"), cookies.get("_d"), codeChallenge, _hp2_id, _hp2_ses_props);
		xsfrToken = current(xsfrToken, cookies.get("_d"), codeChallenge, _hp2_id, _hp2_ses_props);
		xsfrToken = image(xsfrToken, cookies.get("_d"), codeChallenge, _hp2_id, _hp2_ses_props, "ORG_LOGO");
		String code = consent(xsfrToken, cookies.get("_d"), values.get("location"), codeChallenge, _hp2_id,
				_hp2_ses_props);
		return code;
	}

	public String authorize(String codeChallenge, String _hp2_id, String _hp2_ses_props) {

		RestAssured.baseURI = baseURL;
		RestAssured.basePath = "oauth";
		Response response = RestAssured.given()
				.config(RestAssured.config().redirect(RedirectConfig.redirectConfig().followRedirects(false))).log()
				.all()
				.cookies("_hp2_id.870388005", _hp2_id, "XSRF-TOKEN",
						"4d9b43c2-54b7-4924-89b6-39588c719d85.TTdzu+UsG4ER9YmB8tSbOdAQ2VN/3pottE3hiSLAF0Y=",
						"_hp2_ses_props.870388005", _hp2_ses_props)
				.queryParam("redirect_uri", "com.freshchat.ios://login").queryParam("client_id", prop.get("clientId"))
				.queryParam("response_type", "code").queryParam("code_challenge", codeChallenge)
				.queryParam("code_challenge_method", "S256").queryParam("prompt", "login").when().get("authorize");
		response.prettyPrint();
		String xsrfToken = response.getCookie("XSRF-TOKEN");
		return xsrfToken;
	}

	public String appInitPayload(String xsrfToken, String _d, String codeChallenge, String _hp2_id,
			String _hp2_ses_props) {

		RestAssured.baseURI = baseURL;
		RestAssured.basePath = "api/v2/";
		Response response = RestAssured
				.given().log().all().cookies("_hp2_id.870388005", _hp2_id, "XSRF-TOKEN", xsrfToken,
						"_hp2_ses_props.870388005", _hp2_ses_props, "_d", _d)
				.when().get("organisations/-/appInitPayload");
		response.prettyPrint();
		String token = response.getCookie("XSRF-TOKEN");
		return token;
	}

	public String current(String xsrfToken, String _d, String codeChallenge, String _hp2_id, String _hp2_ses_props) {

		RestAssured.baseURI = baseURL;
		RestAssured.basePath = "api/v2/users/";
		Response response = RestAssured
				.given().log().all().cookies("_hp2_id.870388005", _hp2_id, "XSRF-TOKEN", xsrfToken,
						"_hp2_ses_props.870388005", _hp2_ses_props, "_d", _d)
				.queryParam("include", "products").when().get("current");
		response.prettyPrint();
		String token = response.getCookie("XSRF-TOKEN");
		return token;
	}

	public String image(String xsrfToken, String _d, String codeChallenge, String _hp2_id, String _hp2_ses_props,
			String type) {

		RestAssured.baseURI = baseURL;
		RestAssured.basePath = "api/v2/organisation/-/";
		Response response = RestAssured.given()
				.config(RestAssured.config().redirect(RedirectConfig.redirectConfig().followRedirects(false))).log()
				.all()
				.cookies("_hp2_id.870388005", _hp2_id, "XSRF-TOKEN", xsrfToken, "_hp2_ses_props.870388005",
						_hp2_ses_props, "_d", _d)
				.queryParam("entity_type", type).queryParam("variant", "ORIGINAL").when().get("image");
		response.prettyPrint();
		String token = response.getCookie("XSRF-TOKEN");
		return token;
	}

	public String consent(String xsrfToken, String _d, String requestId, String codeChallenge, String _hp2_id,
			String _hp2_ses_props) {

		RestAssured.baseURI = baseURL;
		RestAssured.basePath = "oauth";
		Response response = RestAssured.given()
				.config(RestAssured.config().redirect(RedirectConfig.redirectConfig().followRedirects(false))).log()
				.all()
				.cookies("_hp2_id.870388005", _hp2_id, "XSRF-TOKEN", xsrfToken, "_hp2_ses_props.870388005",
						_hp2_ses_props, "_d", _d)
				.queryParam("request_id", requestId).queryParam("consent", "true").when().get("consent");
		response.prettyPrint();
		String location = response.getHeader("location");

		return location.substring(location.indexOf("code") + 5, location.indexOf("code") + 23);
	}

	public Map<String, String> authorizeMobileForLocation(String xsrfToken, String _d, String codeChallenge,
			String _hp2_id, String _hp2_ses_props) {

		RestAssured.baseURI = baseURL;
		RestAssured.basePath = "oauth";
		Response response = RestAssured.given()
				.config(RestAssured.config().redirect(RedirectConfig.redirectConfig().followRedirects(false))).log()
				.all()
				.cookies("_hp2_id.870388005", _hp2_id, "XSRF-TOKEN", xsrfToken, "_hp2_ses_props.870388005",
						_hp2_ses_props, "_d", _d)
				.queryParam("redirect_uri", "com.freshchat.ios://login").queryParam("client_id", prop.get("clientId"))
				.queryParam("response_type", "code").queryParam("code_challenge", codeChallenge)
				.queryParam("code_challenge_method", "S256").queryParam("prompt", "login").when().get("authorize");
		response.prettyPrint();
		String token = response.getCookie("XSRF-TOKEN");
		String location = response.getHeader("location");
		location = location.substring(location.length() - 18, location.length());
		Map<String, String> values = new HashMap<>();
		values.put("xsrfToken", token);
		values.put("location", location);
		return values;
	}

	public Map<String, String> login(String xsrfToken, String codeChallenge, String _hp2_id, String _hp2_ses_props) {

		RestAssured.baseURI = baseURL;
		RestAssured.basePath = "api/v2/";
		Response response = RestAssured.given().log().all()
				.cookies("_hp2_id.870388005", _hp2_id, "XSRF-TOKEN", xsrfToken, "_hp2_ses_props.870388005",
						_hp2_ses_props)
				.headers("X-XSRF-TOKEN", xsrfToken)
				.body("{\"username\":\"" + userName + "\",\"password\":\"" + prop.getProperty("password") + "\"}")
				.when().post("login");
		response.prettyPrint();
		Map<String, String> cookies = new HashMap<>();
		cookies.put("xsrfToken", response.getCookie("XSRF-TOKEN"));
		cookies.put("_d", response.getCookie("_d"));
		return cookies;
	}

	public static void updatePropertyFile(String key, String value, String propertyPath, String env)
			throws ConfigurationException, FileNotFoundException, IOException {
		Parameters params = new Parameters();
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(
				PropertiesConfiguration.class)
				.configure(params.properties().setFileName(System.getProperty("user.dir")
						+ "/src/test/resources/properties/" + propertyPath + "/" + env + ".properties"));
		Configuration config = builder.getConfiguration();
		config.setProperty(key, value);
		builder.save();
		prop.load(new FileInputStream(new File(System.getProperty("user.dir") + "/src/test/resources/properties/"
				+ propertyPath + "/" + env + ".properties")));
	}
	public void updatePropertyFileWithV3Info(String propertyPath,String env) throws FileNotFoundException, ConfigurationException, IOException {
		RestAssured.baseURI = "https://" + prop.getProperty("agentServer");
		RestAssured.basePath = "/" + prop.getProperty("publicresources") + "/";
		Header header1 = new Header("X-HL-Auth-Token", prop.getProperty("X-HL-Auth-Token"));
		List<Header> headers = new ArrayList<Header>();
		headers.add(header1);
		baseRestAssured.setLogs();
		baseRestAssured.setHeader(headers);
		Response response = baseRestAssured.get(EndPoints.Public.USERINFOV3);
		Assert.assertEquals(response.getStatusCode(), 200);
		JsonPath jsonPath = response.jsonPath();
		updatePropertyFile("agentAppId",jsonPath.getString("userInfoList[0].appIdReal"),propertyPath, env);
		updatePropertyFile("agentFirstName",jsonPath.getString("userInfoList[0].firstName"),propertyPath, env);
		updatePropertyFile("appid",jsonPath.getString("userInfoList[0].appId"),propertyPath, env);
		updatePropertyFile("appkey",jsonPath.getString("userInfoList[0].appKey"),propertyPath, env);
		updatePropertyFile("fcAppCookie","FC-APP-"+jsonPath.getString("userInfoList[0].appIdReal"),propertyPath, env);
		updatePropertyFile("agentFullName",jsonPath.getString("userInfoList[0].firstName")+" "+jsonPath.getString("userInfoList[0].lastName"),propertyPath, env);
		updatePropertyFile("accountName",jsonPath.getString("userInfoList[0].currentAppName"),propertyPath, env);
		updatePropertyFile("oneCxAccount",jsonPath.getString("userInfoList[0].oneCxAccount"),propertyPath, env);
		if(prop.getProperty("agentServer").contains("freshpori")){
			updatePropertyFile("host",prop.getProperty("agentServer"),propertyPath, env);	
			updatePropertyFile("server",prop.getProperty("agentServer"),propertyPath, env);	
			updatePropertyFile("configServer","config-"+prop.getProperty("agentServer"),propertyPath, env);
		} else {
				String rtsEndPoint = jsonPath.getString("userInfoList[0].rtsEndpoint");
				if(!rtsEndPoint.equals("")) {
					System.out.println("rts end point "+ rtsEndPoint);
					String region="";
					if (rtsEndPoint.contains("rts-us")) {
						updatePropertyFile("server","msdk.freshchat.com",propertyPath, env);
						updatePropertyFile("configServer","config-msdk.freshchat.com",propertyPath, env);
					}else {
						if(rtsEndPoint.contains("rts-in")) {
							region="in";
						} else if (rtsEndPoint.contains("rts-euc")) {
							region="eu";
						}else if (rtsEndPoint.contains("rts-aus")) {
							region="au";
						} 
						updatePropertyFile("server","msdk."+region+".freshchat.com",propertyPath, env);
						updatePropertyFile("configServer","config-msdk."+region+".freshchat.com",propertyPath, env);
					}
				}
		}
	}

}
