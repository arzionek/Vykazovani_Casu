package servlety.role.zamestnanec;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.model.Cinnost;
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
    }else{
      presmerovani(request, response, adresa + "/nastaveni.jsp");
    }
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
    List<?> cinnosti = (List<?>) pripojeni.ziskejObjekty(Cinnost.class, new Object[]{"uzivatel.id"}, new Object[]{uzivatel.getId()});
    request.setAttribute("objekty", cinnosti);
    presmerovani(request, response, adresa + "/cinnosti.jsp");
  }
}
