package com.admin.healthypets.servlet.pemilik;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.healthypets.pojo.Klinik;
import com.admin.healthypets.pojo.Pemilik;
import com.admin.healthypets.servlet.KonfigurasiJSP;
import com.admin.healthypets.servlet.RestService;
import com.admin.healthypets.servlet.auth.AuthenticatedServlet;
import com.admin.healthypets.servlet.auth.PeriksaLogin; 

@SuppressWarnings("serial")
public class PemilikKlinikServlet extends AuthenticatedServlet {
 
	@Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
		  super.doGet(request, response);

		PeriksaLogin periksaLogin = new PeriksaLogin();

		if (!periksaLogin.sudahLogin(request)) {
			response.sendRedirect("/");
		}
		 		
//		getPemilik by id
		String idP = request.getParameter("id");
		Long idPemilik = Long.parseLong(idP);
		 
		Pemilik pemilik = null; 
		pemilik = new RestService().getPemilikById(idPemilik);
				
		if (pemilik != null) {
			request.setAttribute("pemilik", pemilik);	
		}		 
		
//		getAllKlinik by id 
		List<Klinik> daftarKlinik = new ArrayList<>();
		daftarKlinik = new RestService().getDaftarKlinik(pemilik.getId());
		if (!daftarKlinik.isEmpty()) {
			request.setAttribute("daftarKlinik", daftarKlinik);
		}
		
		response.setContentType("text/html");
		RequestDispatcher jsp = request
				.getRequestDispatcher(KonfigurasiJSP.TAMPIL_KLINIK_PATH);  
		jsp.forward(request, response);
	}
}
