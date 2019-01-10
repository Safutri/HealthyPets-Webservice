package com.admin.healthypets.servlet.home;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.healthypets.servlet.auth.AuthenticatedServlet;

@SuppressWarnings("serial")
public class LockScreenServlet extends AuthenticatedServlet {	
	
		@Override
		public void doGet(HttpServletRequest request, HttpServletResponse response) 
			      throws IOException, ServletException {
			  super.doGet(request, response);
 
		response.setContentType("text/html");
		RequestDispatcher jsp = request
				.getRequestDispatcher(com.admin.healthypets.servlet.KonfigurasiJSP.LOCK_PATH);  
		jsp.forward(request, response);
	}
	
}
