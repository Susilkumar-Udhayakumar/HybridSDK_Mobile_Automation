package lwsdk.ios.module.testcases;

import org.testng.annotations.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Issue;
import lwsdk.app.base.impl.IOSBase;
import lwsdk.app.pages.ConversationListPage;
import lwsdk.app.pages.HomePage;
import lwsdk.freshrelease.annotation.TestData;

@Epic("Home Page")
public class SampleTestCaseiOS extends IOSBase  {

	@Test(description = "To check description display", groups = {"smoke"})
	@TestData(testId = 3334)
	public void test1() {
		new HomePage(driver)
		.clickAllowButton()
		.clickConversationButton();
		new ConversationListPage(driver)
		.clickConversationButton();
	}
	
	@Issue("MCX-7745")
	@Test(description = "To check description display 2", groups = {"smoke"})
	@TestData(testId = 4054)
	public void testAndroidMethod2() {
		System.out.println("Syso testAndroidMethod2");
		new HomePage(driver)
		.clickConversationButton();
		new ConversationListPage(driver)
		.clickConversationButton();
		
	}
}
