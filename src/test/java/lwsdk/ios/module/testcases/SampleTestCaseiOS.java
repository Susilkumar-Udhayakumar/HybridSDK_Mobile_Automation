package lwsdk.ios.module.testcases;

import java.util.Set;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
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
		new HomePage(driver)
		.clickConversationButton();
		Set<String> contextHandles = ((IOSDriver)driver).getContextHandles();

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
	
}
