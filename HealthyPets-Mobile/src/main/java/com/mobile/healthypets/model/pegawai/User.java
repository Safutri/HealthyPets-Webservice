package com.mobile.healthypets.model.pegawai;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id private Long id;
	
	@Index 
	private String nama;
	private String username;
		
	@Index
	private String email;
	private byte[]password;
	private byte[] salt;  
	
	public Long getId() {
		return id;
	}
	
	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	
	
public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

protected User () {}
	
	public User(String nama, String username, String email, byte[] salt, byte[] password) {
		this.nama = nama;
		this.username = username; 
		this.email = email;
		this.salt = salt;
		this.password = password;

	}
}
