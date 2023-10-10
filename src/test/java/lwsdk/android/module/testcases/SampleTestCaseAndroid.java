package lwsdk.android.module.testcases;

import org.testng.annotations.Test;

import lwsdk.base.impl.AndroidBase;
import lwsdk.pages.HomePage;

public class SampleTestCaseAndroid extends AndroidBase  {

	@Test
	public void test1() {
		new HomePage(driver)
		.clickConversationButton();
		
	}
	
}
