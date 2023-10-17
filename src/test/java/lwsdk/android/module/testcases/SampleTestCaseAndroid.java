package lwsdk.android.module.testcases;

import org.testng.annotations.Test;

import io.qameta.allure.Epic;
import lwsdk.app.base.impl.AndroidBase;
import lwsdk.app.pages.HomePage;
import lwsdk.freshrelease.annotation.TestData;



//@Listeners(lwsdk.app.listeners.TestListeners.class)
@Epic("Home Page")
public class SampleTestCaseAndroid extends AndroidBase  {

	@Test(description = "To check description display", groups = {"smoke"})
	@TestData(testId = 3334)
	public void testAndroidMethod() {
		System.out.println("Syso testAndroidMethod");
		new HomePage(driver)
		.clickConversationButton();
		
	}
	
}
