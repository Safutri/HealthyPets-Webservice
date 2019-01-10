package com.admin.healthypets.servlet.auth;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class LoginAuthenticator {
	/**
	 * Periksa status login
	 * @param email
	 * @param password
	 * @return false >> gagal login. true >> berhasil login
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 */
	public Boolean berhasilLogin (String mobile, String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		//Admin admin = null;
		
		//admin = new UserCtrl().getByMobile(mobile);
		
		
		// email tidak terdaftar
		/* if (admin == null) {
			return false;
		} else {
			// bandingkan password yang dimasukkan dengan password yang telah disimpan

			// Bandingkan password
			SecureHashWithSalt bangkitPassword = new SecureHashWithSalt();
			if (bangkitPassword.sama(password, admin.getKataKunci(), admin.getSalt())) {
				return true;
			}
		}*/
		
		return false;
	}
}
