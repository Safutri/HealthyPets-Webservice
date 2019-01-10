package com.admin.healthypets.servlet.resepsionis;
 
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
public class PerbaruiResepsionisServlet extends AuthenticatedServlet {
	Long idResepsionis = null;
	
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
		 System.out.println("idResepsionis "+idResepsionis);
		 System.out.println("idR "+idR);
		 
		Resepsionis resepsionis = null; 
		resepsionis = new RestService().getResepsionisById(idResepsionis);
				
		if (resepsionis!= null) {
			request.setAttribute("resepsionis", resepsionis);	
		} 

		
		response.setContentType("text/html");
		RequestDispatcher jsp = request
				.getRequestDispatcher(KonfigurasiJSP.PERBARUI_RESEPSIONIS_PATH);  
		jsp.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
 
//		Long id = Long.parseLong(request.getParameter("id")); 
		String id = request.getParameter("id"); 

		String nama = request.getParameter("nama"); 
		String alamat = request.getParameter("alamat");
		String telp = request.getParameter("telp");
//		String nama_klinik = request.getParameter("nama_klinik");
//		String email_klinik = request.getParameter("email_klinik");
		
		new RestService().UpdateResepsionis(id, nama, alamat, telp);
		
		// redirect ke url ini kembali
		response.sendRedirect("/home");
	}
}
