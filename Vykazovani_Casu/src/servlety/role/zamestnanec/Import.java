package servlety.role.zamestnanec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.beany.Cas;
import dao.databaze.Databaze;

public class Import extends AServletZamestnanec{

	private static final long serialVersionUID = -7726309060094642664L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		if(!response.isCommitted()){
			HttpSession session = request.getSession();
			String role = (String) session.getAttribute("role");
			nastavUdajeOPrihlasenem(request, session, role);
			System.out.println(new Cas().ziskejDatum() + " - _import " + role + ": " + session.getAttribute("login"));
			importGoogle(request, response, pripojeni);
		}
	}
	
	private void importGoogle(HttpServletRequest request, HttpServletResponse response, Databaze pripojeni) throws ServletException, IOException {
		Cas cas = new Cas();
		request.setAttribute("cas", cas);
		request.setAttribute("system", pripojeni.ziskejSystemoveInformace());
		presmerovani(request, response, adresa + "/import.jsp");
	}
}