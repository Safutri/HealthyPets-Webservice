package com.admin.healthypets.servlet.resepsionis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.healthypets.pojo.Klinik;
import com.admin.healthypets.pojo.Resepsionis;
import com.admin.healthypets.servlet.KonfigurasiJSP;
import com.admin.healthypets.servlet.RestService;
import com.admin.healthypets.servlet.auth.AuthenticatedServlet;
import com.admin.healthypets.servlet.auth.PeriksaLogin; 

@SuppressWarnings("serial")
public class ResepsionisServlet extends AuthenticatedServlet {
	 
		@Override
		  public void doGet(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException, ServletException {
			  super.doGet(request, response);
		
		PeriksaLogin periksaLogin = new PeriksaLogin();

		if (!periksaLogin.sudahLogin(request)) {
			response.sendRedirect("/");
		}
		
//		getKlinik by id
		String idK = request.getParameter("id");
		Long idKlinik = Long.parseLong(idK);
		 
		Klinik klinik = null; 
		klinik = new RestService().getKlinikById(idKlinik);
				
		if (klinik != null) {
			request.setAttribute("klinik", klinik);	
		}
		
//		List dari pemilik Resepsionis 
		List<Resepsionis> resepsionis = new ArrayList<>();
		resepsionis= new RestService().getDaftarResepsionis(klinik.getId());
		if (!resepsionis.isEmpty()) {
			request.setAttribute("resepsionis", resepsionis);
		} 
		
		response.setContentType("text/html");
		RequestDispatcher jsp = request
				.getRequestDispatcher(KonfigurasiJSP.RESEPSIONIS_PATH);  
		jsp.forward(request, response);
		}
}