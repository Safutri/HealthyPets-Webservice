package com.admin.healthypets.servlet.resepsionis;

import java.io.IOException; 

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.healthypets.pojo.Klinik;
import com.admin.healthypets.servlet.KonfigurasiJSP;
import com.admin.healthypets.servlet.RestService;
import com.admin.healthypets.servlet.auth.AuthenticatedServlet;
import com.admin.healthypets.servlet.auth.PeriksaLogin; 

@SuppressWarnings("serial")
public class DaftarResepsionisServlet extends AuthenticatedServlet {
	 
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
		 		
		response.setContentType("text/html");
		RequestDispatcher jsp = request
				.getRequestDispatcher(KonfigurasiJSP.DAFTAR_RESEPSIONIS_PATH);  
		jsp.forward(request, response);
		}
	
		@Override
		 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String idK = request.getParameter("id");
			Long idKlinik = Long.parseLong(idK);
 
		String nama = request.getParameter("nama");  
		String alamat = request.getParameter("alamat");
		String email = request.getParameter("email");
		String telp = request.getParameter("telp"); 
 		String email_klinik = request.getParameter("email_klinik");
		String nama_klinik = request.getParameter("nama_klinik");
 	 
		new RestService().CreateResepsionis(nama, idKlinik, alamat, email, telp, nama_klinik, email_klinik);
		
				response.sendRedirect("/all-pemilik"); 
	}
}