package com.admin.healthypets.servlet.auth;

import java.io.IOException;
//import javax.servlet.RequestDispatcher;
//import java.security.NoSuchAlgorithmException;
//import java.security.spec.InvalidKeySpecException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.healthypets.servlet.KonfigurasiJSP;
import com.admin.healthypets.servlet.RestService;
import com.google.appengine.api.users.UserServiceFactory; 

@SuppressWarnings("serial")
//@WebServlet(
//		name = "RegisterServlet", 
//		urlPatterns = { "/register" }
//		)
public class RegisterServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	 		
		PeriksaLogin periksaLogin = new PeriksaLogin();

			// periksa apakah sudah melakukan login
			if (periksaLogin.sudahLogin(request)) {
				response.sendRedirect("/home");
			}

			String googleLoginUrl = UserServiceFactory.getUserService().createLoginURL("/");
			
			request.setAttribute("googleLoginURL", googleLoginUrl);
			
		response.setContentType("text/html");
		RequestDispatcher jsp = request
				.getRequestDispatcher(KonfigurasiJSP.REGISTER_ADMIN_PATH);
		jsp.forward(request, response); 
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nama = req.getParameter("nama");
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
	
		// tambahkan Admin ke Datastore
		new RestService().postAdmin(nama, username, email, password);		
		resp.sendRedirect("/");
	}


}
