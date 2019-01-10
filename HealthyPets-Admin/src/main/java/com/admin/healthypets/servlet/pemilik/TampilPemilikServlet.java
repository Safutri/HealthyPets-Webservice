package com.admin.healthypets.servlet.pemilik;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.healthypets.pojo.Pemilik;
import com.admin.healthypets.servlet.KonfigurasiJSP;
import com.admin.healthypets.servlet.RestService;
import com.admin.healthypets.servlet.auth.AuthenticatedServlet;
import com.admin.healthypets.servlet.auth.PeriksaLogin;
import com.google.api.server.spi.response.NotFoundException;
 

@SuppressWarnings("serial")
public class TampilPemilikServlet extends AuthenticatedServlet {
	
	  @Override
		  public void doGet(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException, ServletException {
			  super.doGet(request, response);
 
			PeriksaLogin periksaLogin = new PeriksaLogin();

			if (!periksaLogin.sudahLogin(request)) {
				response.sendRedirect("/");
			} 
			
			List<Pemilik> daftarPemilik = new ArrayList<>();
			  try {
				daftarPemilik = new RestService().getListPemilik();
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  if (!daftarPemilik.isEmpty()) {
				  request.setAttribute("daftarPemilik", daftarPemilik);
			  }
			
			response.setContentType("text/html");
			RequestDispatcher jsp = request
					.getRequestDispatcher(KonfigurasiJSP.TAMPIL_PEMILIK_PATH);  
			jsp.forward(request, response);
		}
}
