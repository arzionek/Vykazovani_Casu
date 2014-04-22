package dao.databaze;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.model.Uzivatel;

public class Prihlaseni extends ADatabaze{

	public boolean prihlaseni(String login, String heslo, HttpServletRequest request) {
	  boolean prihlaseni = false;
	  Uzivatel uzivatel = (Uzivatel) nacti(Uzivatel.class, new Object[]{"login", "heslo"}, new Object[]{login, heslo}, true);
		if (uzivatel != null) prihlaseni = ulozeniDat(request, uzivatel);
		return prihlaseni;
	}
	
	public boolean prihlaseni(String login, HttpServletRequest request) {
	  boolean prihlaseni = false;
	  Uzivatel uzivatel = (Uzivatel) nacti(Uzivatel.class, "login", login);
		if (uzivatel != null) prihlaseni = ulozeniDat(request, uzivatel);
		else prihlaseni = novyUzivatel(request, login);
		return prihlaseni;
	}

	private boolean ulozeniDat(HttpServletRequest request, Uzivatel uzivatel){
		HttpSession session = request.getSession(true);
		session.setAttribute("loggedUser", uzivatel.getCeleJmeno());
		session.setAttribute("id_uzivatel", uzivatel.getId());
		session.setAttribute("role", ziskejRoli(uzivatel.getId()));
		session.setAttribute("login", uzivatel.getLogin());
		return true;
	}
	
	private boolean novyUzivatel(HttpServletRequest request, String login) {
		HttpSession session = request.getSession(true);
		session.setAttribute("loggedUser", login);
		session.setAttribute("role", ziskejRoli(-1));
		session.setAttribute("login", login);
		return true;
	}

	public static String ziskejRoli(long id) {
		String role = null;
		if(id != -1) role = "zamestnanec";
		else role = "zadna";
		return role;
	}

}
