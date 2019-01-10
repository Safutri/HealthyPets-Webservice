package com.admin.healthypets.servlet.home;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.healthypets.servlet.KonfigurasiJSP;
import com.admin.healthypets.servlet.auth.AuthenticatedServlet;
import com.admin.healthypets.servlet.auth.PeriksaLogin;
 
 
@SuppressWarnings("serial") 
public class HomeServlet extends AuthenticatedServlet {	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException, ServletException {
		  super.doGet(request, response);
			
			PeriksaLogin periksaLogin = new PeriksaLogin();

			if (!periksaLogin.sudahLogin(request)) {
				response.sendRedirect("/"); // /login
			}		
			
			response.setContentType("text/html");
			RequestDispatcher jsp = request
					.getRequestDispatcher(KonfigurasiJSP.HOME_PATH);
			jsp.forward(request, response);
		}
}