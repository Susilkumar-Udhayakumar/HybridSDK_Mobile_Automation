package lwsdk.android.module.testcases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Epic;
import lwsdk.base.impl.AndroidBase;
import lwsdk.common.TestData;
import lwsdk.pages.HomePage;


@Listeners(lwsdk.reports.TestListeners.class)
@Epic("Home Page")
public class SampleTestCaseAndroid extends AndroidBase  {

	@Test(description = "To check description display")
	@TestData(testId = 3334)
	public void testAndroidMethod() {
		new HomePage(driver)
		.clickConversationButton();
		
	}
	
}
