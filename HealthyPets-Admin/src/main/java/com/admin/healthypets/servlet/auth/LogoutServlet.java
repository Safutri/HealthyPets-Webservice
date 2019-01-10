package com.admin.healthypets.servlet.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(
//		name= "logout",
//		urlPatterns = {"/"}
//		)
@SuppressWarnings("serial")
public class LogoutServlet extends AuthenticatedServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		super.doGet(request, response); 
		response.sendRedirect("/");
	}
}