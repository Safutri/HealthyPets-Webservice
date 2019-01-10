package com.admin.healthypets.servlet.auth;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.healthypets.pojo.Admin;
import com.admin.healthypets.servlet.KonfigurasiJSP;
import com.admin.healthypets.servlet.RestService;
import com.google.appengine.api.users.UserServiceFactory; 

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		PeriksaLogin periksaLogin = new PeriksaLogin();
		
		// periksa apakah sudah melakukan login
		if (periksaLogin.sudahLogin(request)) {
			response.sendRedirect("/home");
		}
		
		String googleLoginUrl = UserServiceFactory.getUserService().createLoginURL("/home");  //login admin 
		
		request.setAttribute("googleLoginURL", googleLoginUrl);  
		response.setContentType("text/html");
		RequestDispatcher jsp = request
				.getRequestDispatcher(KonfigurasiJSP.LOGIN_ADMIN_PATH);  //ambil halaman
		jsp.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");			//post entity login
		
		// periksa info login
		try {
			Boolean berhasilLogin = new LoginAuthenticator().berhasilLogin(email, password);
			
			HttpSession session = null;
			// jika login berhasil redirect ke url ini kembali
			if (berhasilLogin) {

				Admin admin = new RestService().getAdminByEmail(email);   //login dg email
				
				// true >> dibuat sesi baru
				// false >> diambil sesi yang sudah ada
				// req.getSession().invalidate() >> hapus sesi
				session = request.getSession(true);
				session.setAttribute("idAdmin", admin.getId());
				session.setAttribute("admin", admin);
				
				// Pastikan lama tidak aktif sebelum dipaksa logout
				session.setMaxInactiveInterval(60 * 60 * 8); // 8 jam dalam detik
				
				response.sendRedirect("/home");
				
			} else {
				response.sendRedirect("/");  // /login
			}
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
