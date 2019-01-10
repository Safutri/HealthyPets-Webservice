package com.mobile.healthypets.api.resepsionis;

public class JSONPesan {
	
	private String message;
	
	public String getMessage() {
		if (message== null) {
			return "";
		}
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public JSONPesan(String message) {
		this.message = message;
	}
	
}
