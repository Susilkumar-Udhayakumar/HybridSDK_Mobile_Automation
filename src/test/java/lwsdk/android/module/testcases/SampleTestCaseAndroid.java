package lwsdk.android.module.testcases;

import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Epic;
import lwsdk.app.base.impl.AndroidBase;
import lwsdk.app.pages.ConversationListPage;
import lwsdk.app.pages.HomePage;
import lwsdk.freshrelease.annotation.TestData;

@Listeners(lwsdk.app.listeners.TestListeners.class)
@Epic("Home Page")
public class SampleTestCaseAndroid extends AndroidBase  {

	@Test(description = "To check description display", groups = {"smoke"})
	@TestData(testId = 3334)
	public void testAndroidMethod() {
		new HomePage(driver)
		.clickConversationButton()
		.switchToWebViewWithFrame();
		
		new ConversationListPage(driver)
		.clickConversationButton();
		
	}
	
	@Test(description = "To check description display 2", groups = {"smoke"})
	@TestData(testId = 3334)
	public void testAndroidMethod2() {
		System.out.println("Syso testAndroidMethod2");
		new HomePage(driver)
		.clickConversationButton();
		
	}
	
}
