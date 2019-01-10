package com.admin.healthypets.servlet.klinik; 
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.healthypets.pojo.Pemilik;
import com.admin.healthypets.servlet.KonfigurasiJSP;
import com.admin.healthypets.servlet.RestService;
import com.admin.healthypets.servlet.auth.AuthenticatedServlet;
import com.admin.healthypets.servlet.auth.PeriksaLogin;

 
@SuppressWarnings("serial")
public class KlinikServlet extends AuthenticatedServlet {
//	Long id = null;
 
	@Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {
		  super.doGet(request, response);

		PeriksaLogin periksaLogin = new PeriksaLogin();

		if (!periksaLogin.sudahLogin(request)) {
			response.sendRedirect("/");
		}		
		
		String idP = request.getParameter("id");
		Long idPemilik = Long.parseLong(idP);
		 System.out.print("id "+idPemilik);
		Pemilik pemilik = null; 
		pemilik = new RestService().getPemilikById(idPemilik);
				
		if (pemilik != null) {
			request.setAttribute("pemilik", pemilik);	
		} 	
		
		response.setContentType("text/html");
		RequestDispatcher jsp = request
				.getRequestDispatcher(KonfigurasiJSP.TAMBAH_KLINIK_PATH);  
		jsp.forward(request, response);
	}
	
	@Override
	 public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		Long id = Long.parseLong(request.getParameter("id"));
		String idP = request.getParameter("id");
		Long id= Long.parseLong(idP);		
	 	
		String nama = request.getParameter("nama");  
		String alamat = request.getParameter("alamat");
		String email = request.getParameter("email");
		String telp = request.getParameter("telp");
		String identitas  = request.getParameter("identitas");
		
		new RestService().CreateKlinik(id, nama, alamat, email, telp, identitas);
		
			response.sendRedirect("/home");  
	}

}
