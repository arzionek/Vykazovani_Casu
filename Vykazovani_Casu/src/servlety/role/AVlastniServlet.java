package servlety.role;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.beany.Cas;
import dao.model.Uzivatel;

public abstract class AVlastniServlet extends AServlet{
	
	private static final long serialVersionUID = 4290993986585412083L;

	protected void presmerovani(HttpServletRequest request, HttpServletResponse response, String adresa) throws ServletException, IOException{
		if(!response.isCommitted()){
  	  RequestDispatcher dispatcher;
  		dispatcher = request.getRequestDispatcher("/jsp" + adresa);
  		dispatcher.forward(request, response);
		}
	}
	
	protected void nastavUdajeOPrihlasenem(HttpServletRequest request, HttpSession session, String role) {
		long id = Integer.parseInt(session.getAttribute("id_uzivatel").toString());
		Uzivatel uzivatel = (Uzivatel) pripojeni.nacti(Uzivatel.class, id);
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
				request.setAttribute("error1", true);
				cislo = 0;
			}
		}catch(Exception e){
			request.setAttribute("error1", true);
		}
		return cislo;
	}
	
	protected static int vratId(HttpServletRequest request, String jmeno){
		int cislo = 0;
		try{
			cislo = Integer.parseInt(request.getParameter(jmeno));
		} catch(Exception e) {}
		return cislo;
	}
	
	protected static String kontrola(String nazev, HttpServletRequest request) {
		String parameter = request.getParameter(nazev);
		if(parameter == null) return parameter;
		if(parameter.length() < 2 || parameter.equals("%")) request.setAttribute("error2", true);
		return parameter;
	}
	
	protected static double kontrolaDouble(String nazev, HttpServletRequest request) {
		String parametr = request.getParameter(nazev);
		parametr = parametr.replace(",", ".");
		double parameter = 0.0;
		try {
			parameter = Double.parseDouble(parametr);
		} catch (Exception e) {
			request.setAttribute("error4", true);
		}
		if (parameter <= 0) request.setAttribute("error4", true);
		
		return parameter;
	}
	
	protected static void kontrolaMaximalniDelky(String nazev, HttpServletRequest request, int maximalniDelka) {
	  if (nazev.length() > maximalniDelka) request.setAttribute("error5", true);
	}
	
	protected static String[] getObjekty(HttpServletRequest request, String nazev) {
		String[] ids = request.getParameterValues(nazev);
		if(ids == null || ids.length < 1){
			request.setAttribute("error2", true);
			return null;
		}
		return ids;
	}
	
	protected String getUzivatele(HttpServletRequest request){
	  return (String) request.getSession().getAttribute("login");
	}
	
	protected void vypisAkce(String akce, HttpServletRequest request){
	  System.out.println(new Cas().ziskejDatum() + " - _" + akce + ": " + getUzivatele(request));
	}
	
	public static Date vratDatum(String atribut, HttpServletRequest request) {
    String datum = (String) request.getParameter(atribut);
    datum = datum.replace('.', '-');
    
    int pocetUdaju = datum.length() - datum.replace("-", "").length();
    if (pocetUdaju < 1 || pocetUdaju > 2) {
      request.setAttribute("error3", true);
      return new Date();
    }
    else if (pocetUdaju == 2) {
      if (datum.charAt(datum.length() - 1) == '-') datum += "9999";
    }
    else datum += "-9999";
    
    DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    format.setLenient(false);    
    Date datumDatabaze = new Date();
    try {
      datumDatabaze = format.parse(datum);
    } catch (ParseException e1) {
      if (datum.contains("29-2-") || datum.contains("29-02-")) { //29. unor - prestupny rok
        try {
          return format.parse("29-02-2000");
        } catch (Exception e2) {}
      }
      else request.setAttribute("error3", true);
    }
    return datumDatabaze;
  }
	
	protected double vratPocetOdpracovanychHodin(Date casOd, Date casDo) {
	  double rozdil = casDo.getTime() - casOd.getTime();	  
	  rozdil /= 1000; //na sekundy
	  rozdil /= 60; //na minuty
	  rozdil /= 60; //na hodiny  
	  return rozdil;
	}
}
