package lwsdk.ios.module.testcases;

import org.testng.annotations.Test;

import io.qameta.allure.Epic;
import lwsdk.app.base.impl.IOSBase;
import lwsdk.app.pages.HomePage;
import lwsdk.freshrelease.annotation.TestData;


@Epic("Home Page")
public class SampleTestCaseiOS extends IOSBase  {

	@Test(description = "To check description display", groups = {"smoke"})
	@TestData(testId = 3334)
	public void test1() {
		new HomePage(driver)
		.clickConversationButton();
	}
	
}
