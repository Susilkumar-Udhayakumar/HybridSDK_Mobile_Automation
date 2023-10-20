package lwsdk.ios.module.testcases;

import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
import io.qameta.allure.Epic;
import lwsdk.app.base.impl.IOSBase;
import lwsdk.app.pages.ConversationListPage;
import lwsdk.app.pages.HomePage;
import lwsdk.freshrelease.annotation.TestData;


@Epic("Home Page")
public class SampleTestCaseiOS extends IOSBase  {

	@Test(description = "To check description display", groups = {"smoke"})
	@TestData(testId = 3334)
	public void test1() {
		driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Allow']")).click();
		new HomePage(driver)
		.clickConversationButton();
		
		driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Robin's Topic\"]")).click();
        
        System.out.println(driver.getPageSource());
		new ConversationListPage(driver)
		.clickConversationButton();
	}
	
}
