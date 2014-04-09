package servlety.role;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.beany.Cas;
import dao.databaze.Prihlaseni;

public class LoginMimo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String ADRESA = "/jsp";
	private Prihlaseni pripojeni;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		HttpSession session = request.getSession();
		if(session == null || session.getAttribute("loggedUser") == null){
			RequestDispatcher rd = request.getRequestDispatcher(ADRESA + "/login.jsp");
			rd.forward(request, response);
		}else{
			response.sendRedirect("uvodni");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String login = request.getParameter("login");
		String heslo = request.getParameter("heslo");

		if(pripojeni.prihlaseni(login, heslo, request)){
			System.out.println(new Cas().ziskejDatum() + " - prihlaseni: " + request.getSession().getAttribute("loggedUser"));
			response.sendRedirect("uvodni");
		}else{
			request.setAttribute("error", true);
			RequestDispatcher rd = request.getRequestDispatcher(ADRESA + "/login.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		pripojeni = new Prihlaseni();
	}
	
}
