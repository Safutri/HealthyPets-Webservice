package com.admin.healthypets.servlet.drh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.admin.healthypets.pojo.DokterHewan; 
import com.admin.healthypets.pojo.Resepsionis;
import com.admin.healthypets.servlet.KonfigurasiJSP;
import com.admin.healthypets.servlet.RestService;
import com.admin.healthypets.servlet.auth.AuthenticatedServlet;
import com.admin.healthypets.servlet.auth.PeriksaLogin; 

@SuppressWarnings("serial")
public class DrhServlet extends AuthenticatedServlet {
	 
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
		
//		List dari drh by resepsionis 
		List<DokterHewan> drh = new ArrayList<>();
		drh = new RestService().getDaftarDokterHewan(resepsionis.getId());
		if (!drh.isEmpty()) {
			request.setAttribute("drh", drh);
		} 				 
		
		response.setContentType("text/html");
		RequestDispatcher jsp = request
				.getRequestDispatcher(KonfigurasiJSP.DRH_PATH);  
		jsp.forward(request, response);
		}

}
