package com.sundirect.crm.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionInfo {
	private String model;
	private String pk;
	private Fields fields;
	
	
	
	public String getModel() {
		return model;
	}



	public void setModel(String model) {
		this.model = model;
	}



	public String getPk() {
		return pk;
	}



	public void setPk(String pk) {
		this.pk = pk;
	}



	public Fields getFields() {
		return fields;
	}



	public void setFields(Fields fields) {
		this.fields = fields;
	}



	public static class Fields{
		String browser;
		@JsonProperty("client_key")
		String clientKey;
		@JsonProperty("created_on")
		String createdOn;
		String device;
		@JsonProperty("expires_at")
		String expiresAt;
		@JsonProperty("ip_address")
		String ipAddress;
		@JsonProperty("modified_on")
		String modifiedOn;
		String user;
		public String getBrowser() {
			return browser;
		}
		public void setBrowser(String browser) {
			this.browser = browser;
		}
		public String getClientKey() {
			return clientKey;
		}
		public void setClientKey(String clientKey) {
			this.clientKey = clientKey;
		}
		public String getCreatedOn() {
			return createdOn;
		}
		public void setCreatedOn(String createdOn) {
			this.createdOn = createdOn;
		}
		public String getDevice() {
			return device;
		}
		public void setDevice(String device) {
			this.device = device;
		}
		public String getExpiresAt() {
			return expiresAt;
		}
		public void setExpiresAt(String expiresAt) {
			this.expiresAt = expiresAt;
		}
		public String getIpAddress() {
			return ipAddress;
		}
		public void setIpAddress(String ipAddress) {
			this.ipAddress = ipAddress;
		}
		public String getModifiedOn() {
			return modifiedOn;
		}
		public void setModifiedOn(String modifiedOn) {
			this.modifiedOn = modifiedOn;
		}
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
		
		
		
		
		
		
		
		
	}
	
	
}
