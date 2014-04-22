package servlety.role.zamestnanec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.databaze.Prihlaseni;
import dao.model.Uzivatel;

import servlety.nastroje.WeboveSluzbyStag;
import servlety.role.AServlet;

public class Registrace extends AServlet{

	private static final long serialVersionUID = 932217799687560018L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  HttpSession session = request.getSession();
	  if(session.getAttribute("role") == null) super.doGet(request, response);
		if(!response.isCommitted()){
			registrace(session, response);
		}
	}

	private void registrace(HttpSession session, HttpServletResponse response) throws ServletException, IOException {
	  String ticket = (String) session.getAttribute("ticket");
	  String login = (String) session.getAttribute("login");
	  Uzivatel uzivatel = pripojeni.nacti(Uzivatel.class, "login", login);
	  if(uzivatel == null) uzivatel = new Uzivatel();
	  uzivatel.setLogin(login);
    WeboveSluzbyStag.nastavUdaje(uzivatel, ticket);
    pripojeni.vlozUprav(uzivatel, null);
    uzivatel = pripojeni.nacti(Uzivatel.class, "login", uzivatel.getLogin());
    session.setAttribute("id_uzivatel", uzivatel.getId());
    session.setAttribute("role", Prihlaseni.ziskejRoli(uzivatel.getId()));
    response.sendRedirect("uvodni");
	}
}
