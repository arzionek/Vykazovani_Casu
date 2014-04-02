package servlety.role.zamestnanec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.beany.Cas;
import dao.databaze.Databaze;

public class Vkladani extends AServletZamestnanec{

	private static final long serialVersionUID = -7726309060094642664L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		if(!response.isCommitted()){
			System.out.println(new Cas().ziskejDatum() + " - _nove_cinnosti: " + request.getSession().getAttribute("login"));
			noveCinnosti(request, response, pripojeni);
		}
	}
	
	private void noveCinnosti(HttpServletRequest request, HttpServletResponse response, Databaze pripojeni) throws ServletException, IOException {
		presmerovani(request, response, adresa + "/zadane_cinnosti_nove.jsp");
	}
}
