package com.freshchat.lwsdk.android.module.testcases;

import org.testng.annotations.Test;

import com.freshchat.lwsdk.app.base.impl.AndroidBase;
import com.freshchat.lwsdk.app.pages.ConversationListPage;
import com.freshchat.lwsdk.app.pages.HomePage;
import com.freshchat.lwsdk.freshrelease.annotation.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("SDK Capabilities")
@Story("Conversation List Page")
public class ConversationListTest extends AndroidBase{
	
	@Test(description = "To check whether click on show conversation lands us in topic list page", groups = {"smoke"})
	@TestData(testId = 3334)
	public void testConversationAppearsInList() {
		HomePage home = new HomePage(driver);
		home.clickConversationButton()
		.switchToWebViewWithFrame();
		
		new ConversationListPage(driver)
		.checkConversationAppearinConversationList();		
	}
	
	@Test(description = "To check whether clicking on the close button on the top right takes you to the page from where Hybrid SDk is initiated", groups = {"smoke"})
	@TestData(testId = 3334)
	public void testCloseButtonInList() {
		new HomePage(driver)
		.clickConversationButton()
		.switchToWebViewWithFrame();
		
		new ConversationListPage(driver)
		.clickCloseButtonInCoversationList();
		
		new HomePage(driver).checkConversationButtonInHomePage();		
	}
	
	@Test(description = "To check whether \"Powered by Freshchat\" is displayed at the button of the home page", groups = {"smoke"})
	@TestData(testId = 3334)
	public void testFreshChatLabelAppearsInList() {
		HomePage home = new HomePage(driver);
		home.clickConversationButton()
		.switchToWebViewWithFrame();
		
		new ConversationListPage(driver)
		.checkFreshChatLabelAppearInCoversationList();
		
	}
	
	@Test(description = "To check whether click on faq seach button takes us to faq page", groups = {"smoke"})
	@TestData(testId = 3334)
	public void testFAQPageAppearsAfterClickingSearchIcon() {
		HomePage home = new HomePage(driver);
		home.clickConversationButton()
		.switchToWebViewWithFrame();
		
		new ConversationListPage(driver)
		.clickFAQSearchIconInCoversationList()
		.checkFAQPageAppearing();
		
	}
	
	@Test(description = "To check whether faq's categories are displayed under FAQ section", groups = {"smoke"})
	@TestData(testId = 3334)
	public void testFAQCategoryAppearUnderFAQSection() {
		HomePage home = new HomePage(driver);
		home.clickConversationButton()
		.switchToWebViewWithFrame();
		
		new ConversationListPage(driver)
		.checkFAQCategoryUnderFAQSection();
		
	}

}
