package com.admin.healthypets.servlet.pemilik;
  
import java.io.IOException; 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.admin.healthypets.servlet.KonfigurasiJSP;
import com.admin.healthypets.servlet.RestService;
import com.admin.healthypets.servlet.auth.AuthenticatedServlet;
import com.admin.healthypets.servlet.auth.PeriksaLogin;  
 
@SuppressWarnings("serial") 
public class PemilikServlet extends AuthenticatedServlet {
 		
 		  @Override
 		  public void doGet(HttpServletRequest request, HttpServletResponse response) 
 		      throws IOException, ServletException {
 			  super.doGet(request, response);
 			  
			PeriksaLogin periksaLogin = new PeriksaLogin();

			if (!periksaLogin.sudahLogin(request)) {
				response.sendRedirect("/");
			} 
 
			response.setContentType("text/html");
			RequestDispatcher jsp = request
					.getRequestDispatcher(KonfigurasiJSP.TAMBAH_PEMILIK_PATH);  
			jsp.forward(request, response);
		} 
 		  
 		  
@Override
public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String nama = req.getParameter("nama"); 
		String alamat = req.getParameter("alamat");
		String email = req.getParameter("email");
		String identitas = req.getParameter("identitas");
		String telp = req.getParameter("telp");
 			// tambahkan dosen ke DB
 			new RestService().postPemilik(nama, alamat, email, identitas, telp);

 			 			resp.sendRedirect("/all-pemilik");  

 		}
}
