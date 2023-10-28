package com.freshchat.lwsdk.android.module.testcases;

import org.testng.annotations.Test;

import com.freshchat.lwsdk.app.base.impl.AndroidBase;
import com.freshchat.lwsdk.app.pages.HomePage;
import com.freshchat.lwsdk.freshrelease.annotation.TestData;

import io.qameta.allure.Epic;


@Epic("Home Page")
public class SampleTestCaseAndroid2 extends AndroidBase  {

	@Test(description = "To check description display", groups = {"smoke"})
	@TestData(testId = 3334)
	public void testAndroidMethod() {
		System.out.println("Syso testAndroidMethod");
		new HomePage(driver)
		.clickConversationButton();
		
	}
	
	@Test(description = "To check description display 2", groups = {"smoke"})
	@TestData(testId = 3334)
	public void testAndroidMethod2() {
		System.out.println("Syso testAndroidMethod");
		new HomePage(driver)
		.clickConversationButton();
		
	}
	
}
