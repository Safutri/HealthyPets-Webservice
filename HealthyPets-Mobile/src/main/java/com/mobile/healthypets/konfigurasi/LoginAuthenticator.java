package com.mobile.healthypets.konfigurasi;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.mobile.healthypets.admin.Admin;
import com.mobile.healthypets.admin.AdminCtrl; 

public class LoginAuthenticator {
	 
	public Boolean berhasilLogin (String email, String password) 
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		Admin admin = null;
		
		admin = new AdminCtrl().getByEmail(email);
		
		// email tidak terdaftar
		if (admin == null) {
			return false;
		} else {
			// bandingkan password yang dimasukkan dengan password yang telah disimpan

			// Bandingkan password
			SecureHashWithSalt bangkitPassword = new SecureHashWithSalt();
			if (bangkitPassword.sama(password, admin.getPassword(), admin.getSalt())) {
				return true;
			}
		}
		
		return false;
	}
}

