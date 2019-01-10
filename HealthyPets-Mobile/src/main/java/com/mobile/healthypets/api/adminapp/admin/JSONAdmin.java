package com.mobile.healthypets.api.adminapp.admin;

import com.mobile.healthypets.admin.Admin;

public class JSONAdmin {
private Admin admin;
	
	public JSONAdmin(){};
	
	public String getNama() {
		if(admin == null) {
			return "";
		}
		return admin.getNama();
	}
 
	public String getEmail() {
		if(admin == null) {
			return "";
		}
		return admin.getEmail();
	}
	
	public String getUsername() {
		if(admin== null) {
			return "";
		}
		return admin.getUsername();
	}
	
	public Long getId() {
		if (admin == null) {
			return 0L;	
		}
		
		return admin.getId();
	}
	
	private byte[] password;
	public byte[] getPassword() {
		if (admin == null) {
			return password;
		}
		return admin.getPassword();
	}

//	public void setPassword(byte[] password) {
//		this.password = password;
//	}

	private byte[] salt;
	public byte[] getSalt() {
		if (admin == null) {
			return salt;
		}
		return admin.getSalt();
	}

//	public void setSalt(byte[] salt) {
//		this.salt = salt;
//	}
	
	public JSONAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
}
