package com.freshchat.lwsdk.app;

public class V3AuthTokenBody {
	public String redirect_uri;
	public String code_verifier;
	public String code;

	public String getRedirect_uri() {
		return redirect_uri;
	}

	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}

	public String getCode_verifier() {
		return code_verifier;
	}

	public void setCode_verifier(String code_verifier) {
		this.code_verifier = code_verifier;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public DeviceDescription getDevice_description() {
		return device_description;
	}

	public void setDevice_description(DeviceDescription device_description) {
		this.device_description = device_description;
	}

	public DeviceDescription device_description;

	public class DeviceDescription {
		public String ipAddress;

		public String getIpAddress() {
			return ipAddress;
		}

		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}

	}

}
