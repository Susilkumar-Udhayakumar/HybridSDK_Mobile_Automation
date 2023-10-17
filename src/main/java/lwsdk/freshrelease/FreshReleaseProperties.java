package lwsdk.freshrelease;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import lwsdk.app.logger.Log;


public class FreshReleaseProperties {
	
	public static final String configFileLoc = System.getProperty("user.dir") 
			+ File.separator + "/resources/freshreleaseConfig.properties";
	public static String baseURI, accessToken, projectKey;
	public static int runId;

	static {
		InputStream inputStream = null;
		Properties prop = new Properties();

		try {
			inputStream = new FileInputStream(configFileLoc);
			
			Log.debug("Loading FreshRelease config file from location " + configFileLoc);
			prop.load(inputStream);
			baseURI = prop.getProperty("freshrelease.uri");
			accessToken = prop.getProperty("freshrelease.access_token");
			projectKey = prop.getProperty("freshrelease.project_key");
			
		} catch (FileNotFoundException e) {
			Log.exception(e); 
		} catch (IOException e) {
			Log.exception(e);
		}
	}

	public static void setRunId(int runId) {
		FreshReleaseProperties.runId = runId;
	}

}
