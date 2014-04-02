package servlety.role.zamestnanec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.beany.Cas;
import dao.databaze.Databaze;

public class Nastaveni extends AServletZamestnanec{

	private static final long serialVersionUID = 4012199642961685377L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		if(!response.isCommitted()){
			System.out.println(new Cas().ziskejDatum() + " - _nastaveni: " + request.getSession().getAttribute("login"));
			nastaveni(request, response, pripojeni);
		}
	}

	private void nastaveni(HttpServletRequest request, HttpServletResponse response, Databaze pripojeni) throws ServletException, IOException {
		presmerovani(request, response, adresa + "/nastaveni.jsp");
	}
}
