package com.mobile.healthypets.admin;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import com.googlecode.objectify.NotFoundException;
import com.mobile.healthypets.konfigurasi.SecureHashWithSalt; 

public class AdminCtrl {

	public Admin create(String nama, String username, String email, String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
			
			//register
			// Pembangkitan password
			SecureHashWithSalt bangkitPassword = new SecureHashWithSalt();
			byte[] salt = bangkitPassword.buatSalt();
			byte[] passwordEnkripsi = bangkitPassword.enkripsi(password, salt);
			
			// buat entitas pemilik
			Admin admin = new Admin(nama, username, email, salt, passwordEnkripsi);
			// simpan semua perubahan
			ofy().save().entity(admin).now();

			return admin;
		}
	
	public Admin getByEmail(String email) {
		List<Admin> adminPemakai = null;
		Admin admin = null;
		
		try {
			adminPemakai = ofy().load().type(Admin.class).filter("email", email).list();
		} catch (NotFoundException e) {}
		
		if (adminPemakai.size() < 1) {
			
		} else {
			admin = adminPemakai.get(0);
		}
		
		return admin;	
	}
}
