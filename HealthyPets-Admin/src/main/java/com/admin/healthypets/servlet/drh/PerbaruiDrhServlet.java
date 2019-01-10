package com.admin.healthypets.servlet.drh;
 
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.healthypets.pojo.DokterHewan; 
import com.admin.healthypets.servlet.KonfigurasiJSP;
import com.admin.healthypets.servlet.RestService;
import com.admin.healthypets.servlet.auth.AuthenticatedServlet;
import com.admin.healthypets.servlet.auth.PeriksaLogin;
 

@SuppressWarnings("serial")
public class PerbaruiDrhServlet extends AuthenticatedServlet {
	 
		@Override
		  public void doGet(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException, ServletException {
			  super.doGet(request, response);

		PeriksaLogin periksaLogin = new PeriksaLogin();

		if (!periksaLogin.sudahLogin(request)) {
			response.sendRedirect("/");
		} 
		
//		getDrh by id
		String idDrh = request.getParameter("id");
		Long idDokterHewan = Long.parseLong(idDrh);
		 
		DokterHewan drh = null; 
		drh = new RestService().getDokterHewanById(idDokterHewan);
				
		if (drh!= null) {
			request.setAttribute("drh", drh);	
		} 
		
		response.setContentType("text/html");
		RequestDispatcher jsp = request
				.getRequestDispatcher(KonfigurasiJSP.UBAH_DRH_PATH);  
		jsp.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Long id = Long.parseLong(request.getParameter("id")); 
		
		String nama = request.getParameter("nama"); 
		String alamat = request.getParameter("alamat");
		String telp = request.getParameter("telp");
		String no_praktik = request.getParameter("no_praktik");
		String nama_klinik = request.getParameter("nama_klinik");
		String email_klinik = request.getParameter("email_klinik");
//		
		new RestService().UpdateDokterHewan(id, nama, alamat, telp, no_praktik, nama_klinik, email_klinik);
 	
		// redirect ke url ini kembali
		response.sendRedirect("/home");
	}
}
