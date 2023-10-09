package lwsdk.base.impl;

import lwsdk.base.AndroidMobileWrapperInterface;

public class AndroidMobileWrapperImpl extends MobileWrapperImpl implements AndroidMobileWrapperInterface{

	public void closeAndroid() {
		System.out.println("Close from Android");	
	}

}
