package com.admin.healthypets.servlet.klinik;

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
public class PerbaruiKlinikServlet extends AuthenticatedServlet {
	 
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
				.getRequestDispatcher(KonfigurasiJSP.UBAH_KLINIK_PATH);  
		jsp.forward(request, response);
	}
	
		
		@Override
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Long id = Long.parseLong(request.getParameter("id")); 
			String nama = request.getParameter("nama"); 
			String alamat = request.getParameter("alamat");
			String identitas = request.getParameter("identitas");
			String email = request.getParameter("email");
			String telp = request.getParameter("telp");

			new RestService().UpdateKlinik(id, nama, alamat, identitas, email, telp);
		 			
			// redirect ke url ini kembali
			response.sendRedirect("/home");
		} 	
}
