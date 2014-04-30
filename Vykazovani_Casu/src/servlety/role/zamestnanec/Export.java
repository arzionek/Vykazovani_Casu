package servlety.role.zamestnanec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.beany.Cas;
import dao.databaze.Databaze;

public class Export extends AServletZamestnanec{

	private static final long serialVersionUID = 4012199642961685377L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		if(!response.isCommitted()){
			System.out.println(new Cas().ziskejDatum() + " - _export: " + request.getSession().getAttribute("login"));
			export(request, response, pripojeni);
		}
	}

	private void export(HttpServletRequest request, HttpServletResponse response, Databaze pripojeni) throws ServletException, IOException {
	  request.setAttribute("datepickerFormat", "dd.mm.yy");
	  presmerovani(request, response, adresa + "/export.jsp");	
	}
}
