package servlety.role.zamestnanec;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.model.Cinnost;
import dao.model.Svatek;
import dao.model.Uzivatel;

public class Nastaveni extends AServletZamestnanec{

	private static final long serialVersionUID = 4012199642961685377L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		if(!response.isCommitted()){
		  vypisAkce("nastaveni", request);
		  adresa += "/nastaveni";
			nastaveni(request, response);
		}
	}

	private void nastaveni(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String volano = "";
    if(volanaAkce != null && volanaAkce.length() > 0) volano = volanaAkce.substring(0, 1);
    if(akce.getNastaveniCinnosti().equals(volano)){
      vypisAkce("nastaveni_cinnosti", request);
      nastaveniCinnosti(request, response);  
    }if(akce.getNastaveniSvatky().equals(volano)){
      vypisAkce("nastaveni_svatky", request);
      nastaveniSvatky(request, response);
    }else{    
      presmerovani(request, response, adresa + "/nastaveni.jsp");
    }
	}

  private void nastaveniSvatky(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Uzivatel uzivatel = (Uzivatel) request.getAttribute("uzivatel");
    Svatek svatek = new Svatek();
    long svatekId = vratId(request, "objektId");
    if(akce.getNastaveniSvatkyVlozit().equals(volanaAkce)){
      String kod = request.getParameter("kod");
      if(kod != null){
        kod = kontrola("kod", request);
        String nazev = kontrola("nazev", request);
        Date datum = vratDatum("datum", request, false);
        if(svatekId != 0) svatek = (Svatek) pripojeni.nacti(Svatek.class, svatekId);
        svatek.setKod(kod);
        svatek.setNazev(nazev);
        svatek.setDatum(datum);
        svatek.setUzivatel(uzivatel);
        
        Object chyba = request.getAttribute("error2");
        Object chyba2 = request.getAttribute("error3");
        if(chyba == null && chyba2 == null){
          pripojeni.vlozUprav(svatek, svatek.getId());
          svatek = new Svatek();
        }
      }
      vypisAkce("_vlozit", request);
    }else if(akce.getNastaveniSvatkyUpravit().equals(volanaAkce)){
      svatek = (Svatek) pripojeni.nacti(Svatek.class, svatekId);
      vypisAkce("_upravit", request);
    }else if(akce.getNastaveniSvatkySmazat().equals(volanaAkce)){
      svatek = (Svatek) pripojeni.nacti(Svatek.class, svatekId);
      pripojeni.smaz(svatek);
      vypisAkce("_smazat", request);
    }
    
    request.setAttribute("objekt", svatek);
    List<?> svatky = pripojeni.ziskejObjekty(Svatek.class, new Object[]{"uzivatel.id"}, new Object[]{uzivatel.getId()});
    request.setAttribute("objekty", svatky);
    presmerovani(request, response, adresa + "/svatky.jsp");
  }

  private void nastaveniCinnosti(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Uzivatel uzivatel = (Uzivatel) request.getAttribute("uzivatel");
    Cinnost cinnost = new Cinnost();
    long cinnostId = vratId(request, "objektId");
    if(akce.getNastaveniCinnostiVlozit().equals(volanaAkce)){
      String kod = request.getParameter("kod");
      if(kod != null){
        kod = kontrola("kod", request);
        String nazev = kontrola("nazev", request);
        
        if(cinnostId != 0) cinnost = (Cinnost) pripojeni.nacti(Cinnost.class, cinnostId);
        cinnost.setKod(kod);
        cinnost.setNazev(nazev);
        cinnost.setUzivatel(uzivatel);
        
        Object chyba = request.getAttribute("error2");
        if(chyba == null){
          pripojeni.vlozUprav(cinnost, cinnost.getId());
          cinnost = new Cinnost();
        }
      }
      vypisAkce("_vlozit", request);
    }else if(akce.getNastaveniCinnostiUpravit().equals(volanaAkce)){
      cinnost = (Cinnost) pripojeni.nacti(Cinnost.class, cinnostId);
      vypisAkce("_upravit", request);
    }else if(akce.getNastaveniCinnostiSmazat().equals(volanaAkce)){
      cinnost = (Cinnost) pripojeni.nacti(Cinnost.class, cinnostId);
      pripojeni.smaz(cinnost);
      vypisAkce("_smazat", request);
    }
    
    request.setAttribute("objekt", cinnost);
    List<?> cinnosti = pripojeni.ziskejObjekty(Cinnost.class, new Object[]{"uzivatel.id"}, new Object[]{uzivatel.getId()});
    request.setAttribute("objekty", cinnosti);
    presmerovani(request, response, adresa + "/cinnosti.jsp");
  }
}
