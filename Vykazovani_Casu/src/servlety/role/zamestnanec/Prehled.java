package servlety.role.zamestnanec;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.databaze.Databaze;
import dao.model.KalendarCinnost;
import dao.model.Uzivatel;

public class Prehled extends AServletZamestnanec{

	private static final long serialVersionUID = -7726309060094642664L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		if(!response.isCommitted()){
		  vypisAkce("prehled", request);
			prehled(request, response, pripojeni);
		}
	}
	
	private void prehled(HttpServletRequest request, HttpServletResponse response, Databaze pripojeni) throws ServletException, IOException {
	  Uzivatel uzivatel = (Uzivatel) request.getAttribute("uzivatel");  
    KalendarCinnost cinnost = new KalendarCinnost();
    long cinnostId = vratId(request, "objektId");
    if(akce.getPrehledUpravit().equals(volanaAkce)){
      cinnost = pripojeni.nacti(KalendarCinnost.class, cinnostId);
      vypisAkce("_upravit", request);
      Vytvoreni.vytvoreniCinnosti(request, response, uzivatel, cinnost, pripojeni, akce, volanaAkce);
      presmerovani(request, response, adresa + "/zadane_cinnosti_nove.jsp");
    }else if(akce.getPrehledSmazat().equals(volanaAkce)){
      cinnost = pripojeni.nacti(KalendarCinnost.class, cinnostId);
      pripojeni.smaz(cinnost);
      vypisAkce("_smazat", request);
    }
    
    if(!response.isCommitted()){
      List<KalendarCinnost> cinnosti = pripojeni.ziskejObjekty(KalendarCinnost.class, uzivatel);
      for (int i = 0; cinnosti != null && i < cinnosti.size(); i++) {
        KalendarCinnost kc = cinnosti.get(i);
        pripojeni.inicializaceObjektu(kc.getCinnost());
        pripojeni.inicializaceObjektu(kc.getPracovniPomer());
      }
      request.setAttribute("objekty", cinnosti);
  	  presmerovani(request, response, adresa + "/zadane_cinnosti.jsp");
    }
	}
}
