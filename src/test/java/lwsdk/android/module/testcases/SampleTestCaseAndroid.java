package lwsdk.android.module.testcases;

import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.SupportsContextSwitching;
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
		.clickConversationButton();
		// Get the available context handles
		Set<String> contextHandles = ((AndroidDriver)driver).getContextHandles();

        // Loop through the available contexts to find the WebView context you want
        for (String context : contextHandles) {
            if (context.contains("WEBVIEW")) {
                // Switch to the WebView context
                ((SupportsContextSwitching) driver).context(context);
                break;
            }
        }
        driver.switchTo().frame(driver.findElement(By.name("fc_widget")));
        System.out.println(driver.getPageSource());
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
