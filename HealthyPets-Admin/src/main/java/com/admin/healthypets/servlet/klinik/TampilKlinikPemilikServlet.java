package com.admin.healthypets.servlet.klinik;

import java.io.IOException;
import java.util.ArrayList; 
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.healthypets.pojo.Klinik;
import com.admin.healthypets.servlet.KonfigurasiJSP;
import com.admin.healthypets.servlet.RestService;
import com.admin.healthypets.servlet.auth.AuthenticatedServlet;
import com.admin.healthypets.servlet.auth.PeriksaLogin;
import com.google.api.server.spi.response.NotFoundException; 

@SuppressWarnings("serial")
public class TampilKlinikPemilikServlet extends AuthenticatedServlet {
//	Long id = null;
	@Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
		  super.doGet(request, response);

		  PeriksaLogin periksaLogin = new PeriksaLogin();

			if (!periksaLogin.sudahLogin(request)) {
				response.sendRedirect("/");
			}
			
			List<Klinik> klinik = new ArrayList<>();
			  try {
				klinik = new RestService().getListKlinik(); 
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
			  if (!klinik.isEmpty()) {
				  request.setAttribute("klinik", klinik);
			  }
			 
	 
			
//			List<Klinik> daftarKlinik = new ArrayList<>();
//			try {
//				daftarKlinik = new RestService().getListKlinik();
//			} catch (NotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			if (!daftarKlinik.isEmpty()) {
//				// ambil fakultas yang pertama saja
//				request.setAttribute("daftarKlinik", daftarKlinik);
//			}
//			
//			List<Klinik> daftarPemilik = new ArrayList<>();
//			daftarPemilik = new RestService().getDaftarKlinik(daftarPemilik.get(0).getId());
//			if (!daftarPemilik.isEmpty()) {
//				request.setAttribute("daftarPemilik", daftarPemilik);
//			}
			  
			response.setContentType("text/html");
			RequestDispatcher jsp = request
					.getRequestDispatcher(KonfigurasiJSP.TAMPIL_KLINIK_PEMILIK_PATH);  
			jsp.forward(request, response);
		}		
}
