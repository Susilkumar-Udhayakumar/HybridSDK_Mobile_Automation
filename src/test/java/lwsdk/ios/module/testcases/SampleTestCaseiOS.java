package lwsdk.ios.module.testcases;

import org.testng.annotations.Test;

import lwsdk.base.impl.IOSBase;
import lwsdk.pages.HomePage;

public class SampleTestCaseiOS extends IOSBase  {

	@Test
	public void test1() {
		new HomePage(driver)
		.clickConversationButton();
	}
	
}
