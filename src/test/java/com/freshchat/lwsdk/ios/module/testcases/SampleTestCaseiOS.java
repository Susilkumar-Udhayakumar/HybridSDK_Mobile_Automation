package com.freshchat.lwsdk.ios.module.testcases;

import org.testng.annotations.Test;

import com.freshchat.lwsdk.app.base.impl.IOSBase;
import com.freshchat.lwsdk.app.pages.ConversationListPage;
import com.freshchat.lwsdk.app.pages.HomePage;
import com.freshchat.lwsdk.freshrelease.annotation.TestData;

import io.qameta.allure.Epic;
import io.qameta.allure.Issue;

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
	@Test(description = "To check description display 2", groups = {"regression"})
	@TestData(testId = 4054)
	public void testAndroidMethod2() {
		new HomePage(driver)
		.clickConversationButton();
		new ConversationListPage(driver)
		.clickConversationButton();
		
	}
}
