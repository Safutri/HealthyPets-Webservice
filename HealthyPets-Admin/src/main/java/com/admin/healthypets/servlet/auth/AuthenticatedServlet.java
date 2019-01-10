package com.admin.healthypets.servlet.auth;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException; 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.users.UserServiceFactory;


@SuppressWarnings("serial")
public class AuthenticatedServlet extends HttpServlet {
	
	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(AuthenticatedServlet.class.getName());

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// check login
		PeriksaLogin periksaLogin = new PeriksaLogin();
		// periks Admin yang login dengan akun biasa
/*		if (!periksaLogin.sudahLogin(request)) {
			response.sendRedirect("/login");
		}
*/
		if (periksaLogin.googleLogin()) {
			String googleLogoutURL = null;
			googleLogoutURL = UserServiceFactory.getUserService().createLogoutURL("/login");
			com.google.appengine.api.users.User user = UserServiceFactory.getUserService().getCurrentUser();
			request.setAttribute("googleLogoutURL", googleLogoutURL);
			if (user != null) {
				request.setAttribute("emailG", user.getEmail());
				request.setAttribute("username", user.getNickname());
			}
			return;
		} else {
			response.sendRedirect("/");
			return;
		}
	}
}