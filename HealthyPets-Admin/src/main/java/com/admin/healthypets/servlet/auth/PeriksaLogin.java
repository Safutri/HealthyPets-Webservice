package com.admin.healthypets.servlet.auth;

import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSession;

import com.admin.healthypets.pojo.Admin;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class PeriksaLogin {

	
	public boolean sudahLogin(HttpServletRequest req) {

		if (googleLogin()) {
			return true;
		}
		// Periksa apakah admin google 
		Admin pemakaiLogin = null;

		// ambil info sesi saat ini
		HttpSession session = req.getSession(false);
				if (session != null) { 
			pemakaiLogin = (Admin) session.getAttribute("admin");
			if (pemakaiLogin != null) { // Session object pemakai tidak ada berarti 
								   // belum login
				if (!pemakaiLogin.getId().toString().equals("")) { 
						return true;
				}
			}				
		}
				
		return false;
	}
	
	
	public boolean googleLogin() {
		// periksa apakah pemakai login menggunakan akun Google
		UserService userService = UserServiceFactory.getUserService();
		if (userService != null) {
			// pengguna sudah login
			if (userService.isUserLoggedIn()) {
				if (userService.isUserAdmin())
					return true;
			}
		}
		return false;		
	}
}
