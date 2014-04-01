package servlety.role;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.entity.Uzivatel;

public abstract class AVlastniServlet extends AServlet{
	
	private static final long serialVersionUID = 4290993986585412083L;

	protected void presmerovani(HttpServletRequest request, HttpServletResponse response, String adresa) throws ServletException, IOException{
		RequestDispatcher dispatcher;
		
		dispatcher = request.getRequestDispatcher("/jsp" + adresa);
		dispatcher.forward(request, response);
	}
	
	protected void nastavUdajeOPrihlasenem(HttpServletRequest request, HttpSession session, String role) {
		int id = Integer.parseInt(session.getAttribute("id_uzivatel").toString());
		Uzivatel uzivatel = pripojeni.nactiUzivatele("" + id, "id_uzivatel", role);
		request.setAttribute("uzivatel", uzivatel);
		if(role.equals("zamestnanec")){
			request.setAttribute("zamestnanec", uzivatel);
		}
	}
	
	protected static ArrayList<String> vratVybrane(HttpServletRequest request, String jmeno, boolean vyjimka){
		ArrayList<String> vysledek = new ArrayList<String>();
		int i = 1;
		while(request.getParameter(jmeno + i) != null){
			if(request.getParameter(jmeno + i).length() > 0 || vyjimka) vysledek.add(request.getParameter(jmeno + i));
			i++;
		}
		return vysledek;
	}
	
	protected static int vratCislo(HttpServletRequest request, String jmeno){
		int cislo = 0;
		try{
			cislo = Integer.parseInt(request.getParameter(jmeno));
			if(cislo < 0){
				request.setAttribute("error4", true);
				cislo = 0;
			}
		}catch(Exception e){
			request.setAttribute("error4", true);
		}
		return cislo;
	}
	
	protected static int vratId(HttpServletRequest request, String jmeno){
		int cislo = 0;
		try{
			cislo = Integer.parseInt(request.getParameter(jmeno));
		}catch(Exception e){}
		return cislo;
	}
	
	protected static String kontrola(String nazev, HttpServletRequest request) {
		String parameter = request.getParameter(nazev);
		if(parameter == null) return parameter;
		if(parameter.length() < 2 || parameter.equals("%")) request.setAttribute("error2", true);
		return parameter;
	}
	
	protected static String[] getObjekty(HttpServletRequest request, String nazev) {
		String[] ids = request.getParameterValues(nazev);
		if(ids == null || ids.length < 1){
			request.setAttribute("error2", true);
			return null;
		}
		return ids;
	}
	
}