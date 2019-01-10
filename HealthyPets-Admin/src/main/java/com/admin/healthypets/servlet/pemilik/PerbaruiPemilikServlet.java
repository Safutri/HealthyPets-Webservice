package com.admin.healthypets.servlet.pemilik;
 
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
public class PerbaruiPemilikServlet extends AuthenticatedServlet {
	 
		@Override
		  public void doGet(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException, ServletException {
			  super.doGet(request, response);
 
			PeriksaLogin periksaLogin = new PeriksaLogin();

			if (!periksaLogin.sudahLogin(request)) {
				response.sendRedirect("/");
			} 
			
//			getPemilik by id
			String idP = request.getParameter("id");
			Long idPemilik = Long.parseLong(idP);
			 
			Pemilik pemilik = null; 
			pemilik = new RestService().getPemilikById(idPemilik);
					
			if (pemilik != null) {
				request.setAttribute("pemilik", pemilik);	
			} 
			
			response.setContentType("text/html");
			RequestDispatcher jsp = request
					.getRequestDispatcher(KonfigurasiJSP.UBAH_PEMILIK_PATH);  
			jsp.forward(request, response);
		}
		
		@Override
		public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			Long id = Long.parseLong(req.getParameter("id")); 

				String nama = req.getParameter("nama"); 
				String alamat = req.getParameter("alamat"); 
				String identitas = req.getParameter("identitas");
				String telp = req.getParameter("telp");

				new RestService().UpdatePemilik(id, nama, alamat, identitas, telp);
		 			
		 		resp.sendRedirect("/home");
		}
		
//		@Override
//		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
//				throws ServletException, IOException {
//			
//			String nama = request.getParameter("nama"); 
//			String alamat = request.getParameter("alamat");
//			String identitas = request.getParameter("identitas");
//			String telp = request.getParameter("telp");
//			
//			new PemilikCtrl().updatePemilik(id, nama, alamat, identitas, telp);
//			
//			// redirect ke url ini kembali
//			response.sendRedirect("/home");
//		}
}
