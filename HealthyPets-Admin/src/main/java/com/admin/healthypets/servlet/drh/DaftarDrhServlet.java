package com.admin.healthypets.servlet.drh;

import java.io.IOException; 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import com.admin.healthypets.pojo.Resepsionis;
import com.admin.healthypets.servlet.KonfigurasiJSP;
import com.admin.healthypets.servlet.RestService;
import com.admin.healthypets.servlet.auth.AuthenticatedServlet;
import com.admin.healthypets.servlet.auth.PeriksaLogin; 

@SuppressWarnings("serial")
public class DaftarDrhServlet extends AuthenticatedServlet {
	 
		@Override
		  public void doGet(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException, ServletException {
			  super.doGet(request, response);
		
		PeriksaLogin periksaLogin = new PeriksaLogin();

		if (!periksaLogin.sudahLogin(request)) {
			response.sendRedirect("/");
		} 
		
//		getResepsionis by id
		String idR = request.getParameter("id");
		Long idResepsionis = Long.parseLong(idR);
		 
		Resepsionis resepsionis = null; 
		resepsionis = new RestService().getResepsionisById(idResepsionis);
				
		if (resepsionis != null) {
			request.setAttribute("resepsionis", resepsionis);	
		}
			
		response.setContentType("text/html");
		RequestDispatcher jsp = request
				.getRequestDispatcher(KonfigurasiJSP.DAFTAR_DRH_PATH);  
		jsp.forward(request, response);
		}
	
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String idR = request.getParameter("id");
			Long idResepsionis = Long.parseLong(idR);
 
		String email_klinik  = request.getParameter("email_klinik");
		String nama_klinik = request.getParameter("nama_klinik");
		String nama = request.getParameter("nama");  
		String alamat = request.getParameter("alamat");
		String email = request.getParameter("email");
		String telp = request.getParameter("telp");
		String no_praktik  = request.getParameter("no_praktik");
// 
		new RestService().CreateDokterHewan(nama, idResepsionis, alamat, email, no_praktik, telp, email_klinik, nama_klinik);
	 
		response.sendRedirect("/all-pemilik"); 
	}

}