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
import dao.beany.Chyby;
import dao.model.AEntita;
import dao.model.Cinnost;
import dao.model.KalendarDefinice;
import dao.model.PracovniPomer;
import dao.model.SablonaVykaz;
import dao.model.Svatek;
import dao.model.Uzivatel;

public abstract class AVlastniServlet extends AServlet{
	
	private static final long serialVersionUID = 4290993986585412083L;

	protected void presmerovani(HttpServletRequest request, HttpServletResponse response, String adresa) throws ServletException, IOException{
		if(!response.isCommitted()){
  	  RequestDispatcher dispatcher;
  		dispatcher = request.getRequestDispatcher("/jsp" + adresa);
  		dispatcher.forward(request, response);
		}
		pripojeni.uzavritSpojeni(false);
	}
	
	protected void nastavUdajeOPrihlasenem(HttpServletRequest request, HttpSession session, String role) {
		long id = Integer.parseInt(session.getAttribute("id_uzivatel").toString());
		Uzivatel uzivatel = (Uzivatel) pripojeni.nacti(Uzivatel.class, id);
		request.setAttribute("uzivatel", uzivatel);
		if(role.equals("zamestnanec")){
			request.setAttribute("zamestnanec", uzivatel);
		}
	}
	
	protected static String getUzivatele(HttpServletRequest request){
    return (String) request.getSession().getAttribute("login");
  }
  
  protected static void vypisAkce(String akce, HttpServletRequest request){
    System.out.println(new Cas().ziskejDatum() + " - _" + akce + ": " + getUzivatele(request));
  }
	
	protected static int vratId(HttpServletRequest request, String jmeno){
		int cislo = 0;
		try{
			cislo = Integer.parseInt(request.getParameter(jmeno));
		} catch(Exception e) {}
		return cislo;
	}
	
	protected static String[] vratIdObjektù(HttpServletRequest request, String nazev) {
    String[] ids = request.getParameterValues(nazev);
    if(ids == null || ids.length < 1){
      request.setAttribute(Chyby.POVINNY_UDAJ, nazev);
      return null;
    }
    return ids;
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
	
	protected static Object kontrola(HttpServletRequest request, Class<?> trida, String nazev) {
	  Class<?> typDat = AEntita.getTypSloupce(trida, nazev);
	  if (typDat.equals(Double.class)) {
	    return kontrolaDouble(nazev, request);
	  }

	  if (typDat.equals(Date.class)) {
	    return kontrolaDatum(nazev, request);
	  }  

	  if (typDat.equals(String.class)) {
	    String parametr = kontrolaVyplneni(nazev, request);
	    kontrolaMaximalniDelky(parametr, request, AEntita.getSloupec(trida, nazev).getLength());  
	    return parametr;
	  }
	  
	  return null;
	}
	
	protected static int kontrolaCislo(String nazev, HttpServletRequest request){
    int cislo = 0;
    try{
      cislo = Integer.parseInt(request.getParameter(nazev));
      if(cislo < 0){
        request.setAttribute(Chyby.CELE_NEZAPORNE_CISLO, nazev);
        cislo = 0;
      }
    }catch(Exception e){
      request.setAttribute(Chyby.CELE_NEZAPORNE_CISLO, nazev);
    }
    return cislo;
  }
	
	 private static Double kontrolaDouble(String nazev, HttpServletRequest request) {
	    String parametr = request.getParameter(nazev);
	    parametr = parametr.replace(",", ".");
	    double cislo = 0.0;
	    try {
	      cislo = Double.parseDouble(parametr);
	    } catch (Exception e) {
	      request.setAttribute(Chyby.REALNE_NEZAPORNE_CISLO, nazev);
	    }
	    if (cislo <= 0) request.setAttribute(Chyby.REALNE_NEZAPORNE_CISLO, nazev);
	    return cislo;
	  }
	
	 private static Date kontrolaDatum(String nazev, HttpServletRequest request) {
	   String datum = (String) request.getParameter(nazev);
	   DateFormat format = null;
	   if(nazev.contains("cas")){
	     int pocetUdaju = datum.length() - datum.replace(":", "").length();
       if (pocetUdaju != 1) {
         request.setAttribute(Chyby.PLATNE_DATUM, nazev);
         return new Date();
       }
	     datum = "01-01-2000 " + datum;
       
	     format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	   }else{
  	   datum = datum.replace('.', '-');
  	    
  	   int pocetUdaju = datum.length() - datum.replace("-", "").length();
  	   if (pocetUdaju < 1 || pocetUdaju > 2) {
  	     request.setAttribute(Chyby.PLATNE_DATUM, nazev);
  	     return new Date();
  	   }
  	   else if (pocetUdaju == 2) {
  	     if (datum.charAt(datum.length() - 1) == '-') datum += "9999";
  	   }
  	   else datum += "-9999";
  	   
  	   format = new SimpleDateFormat("dd-MM-yyyy");
	   }
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
  	   else request.setAttribute(Chyby.PLATNE_DATUM, nazev);
  	 }
  	 return datumDatabaze;
	  }
	
	private static String kontrolaVyplneni(String nazev, HttpServletRequest request) {
		String parameter = request.getParameter(nazev);
		if(parameter == null) return parameter;
		if(parameter.length() < 2 || parameter.equals("%")) request.setAttribute(Chyby.POVINNY_UDAJ, nazev);
		return parameter;
	}
	
	private static void kontrolaMaximalniDelky(String nazev, HttpServletRequest request, int maximalniDelka) {
	  if (nazev.length() > maximalniDelka) request.setAttribute(Chyby.MAXIMALNI_DELKA, nazev);
	}
	
	protected static Object overChyby(HttpServletRequest request) {
	  Object chyba = request.getAttribute(Chyby.DUPLICITNI_ZADANI);
	  if (chyba == null) chyba = request.getAttribute(Chyby.CELE_NEZAPORNE_CISLO);
	  if (chyba == null) chyba = request.getAttribute(Chyby.POVINNY_UDAJ);
	  if (chyba == null) chyba = request.getAttribute(Chyby.PLATNE_DATUM);
	  if (chyba == null) chyba = request.getAttribute(Chyby.REALNE_NEZAPORNE_CISLO);
	  if (chyba == null) chyba = request.getAttribute(Chyby.MAXIMALNI_DELKA);
	  return chyba;
	}
	
	protected String getShoda(AEntita entita, AEntita entitaDb) {
    String nazev = "";
    if(entita instanceof PracovniPomer){
      if(((PracovniPomer) entita).getKod().equals(((PracovniPomer) entitaDb).getKod())) nazev = "kod";
      else if(((PracovniPomer) entita).getNazev().equals(((PracovniPomer) entitaDb).getNazev())) nazev = "nazev";
      
    }else if(entita instanceof Cinnost){
      if(((Cinnost) entita).getKod().equals(((Cinnost) entitaDb).getKod())) nazev = "kod";
      else if(((Cinnost) entita).getNazev().equals(((Cinnost) entitaDb).getNazev())) nazev = "nazev";
      
    }else if(entita instanceof Svatek){
      if(((Svatek) entita).getKod().equals(((Svatek) entitaDb).getKod())) nazev = "kod";
      else if(((Svatek) entita).getNazev().equals(((Svatek) entitaDb).getNazev())) nazev = "nazev";
      else if(((Svatek) entita).getDatum().equals(((Svatek) entitaDb).getDatum())) nazev = "datum";
      
    }else if(entita instanceof KalendarDefinice){
      if(((KalendarDefinice) entita).getKod().equals(((KalendarDefinice) entitaDb).getKod())) nazev = "kod"; 
      else if(((KalendarDefinice) entita).getNazev().equals(((KalendarDefinice) entitaDb).getNazev())) nazev = "nazev"; 

    }else if(entita instanceof SablonaVykaz){
      if(((SablonaVykaz) entita).getKod().equals(((SablonaVykaz) entitaDb).getKod())) nazev = "kod";
      else if(((SablonaVykaz) entita).getKod().equals(((SablonaVykaz) entitaDb).getKod())) nazev = "nazev";
      
    }
    return nazev;
  }
}
