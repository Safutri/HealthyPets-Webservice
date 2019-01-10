package com.mobile.healthypets.admin;

import com.googlecode.objectify.annotation.Subclass;
import com.mobile.healthypets.model.pegawai.User;

@SuppressWarnings("serial")
@Subclass (index = true)
public class Admin extends User {
 
	@SuppressWarnings("unused")
	private Admin() {}
	
	public Admin(String nama, String username, String email, byte[] salt, byte[] password) {
		// panggil super untuk buat pemakai
		super(nama, username, email, salt, password);
 
	}
}
