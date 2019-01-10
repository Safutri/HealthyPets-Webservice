package com.admin.healthypets.pojo;
 

public class Admin {
	
	private String id;	
	
	public String getId() {
		if (id == null) {
			return "";
		}
		return id;
	}
	
 	
	private String nama;
	public String getNama() {
		if (nama == null) {
			return "";
		}
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
	
	private String username;
	public String getUsername() {
		if (username == null) {
			return "";
		}
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	private String email;
	public String getemail() {
		if (email == null) {
			return "";
		}
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}
	  
	private String password;
	public String getPassword() {
		if (password == null) {
			return "";
		}
		return password;
	}

//	public void setPassword(byte[] password) {
//		this.password = password;
//	}

//	private byte[] salt;
//	public byte[] getSalt() {
//		if (salt == null) {
//			return salt;
//		}
//		return salt;
//	}
//
//	public void setSalt(byte[] salt) {
//		this.salt = salt;
//	}
	
	public Admin(String id, String nama, String username, String email, String password) {
		this.id =id;
		this.nama =nama;
		this.email=email;
		this.username=username;
//		this.salt=salt;
		this.password=password; 	

	} 
}
