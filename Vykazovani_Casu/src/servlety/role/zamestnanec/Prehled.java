package servlety.role.zamestnanec;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.beany.Cas;
import dao.databaze.Databaze;
import dao.model.KalendarCinnost;
import dao.model.NastaveniSystemu;
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
	  NastaveniSystemu datumOdN = pripojeni.nacti(NastaveniSystemu.class, "nazev", "datumOd", true, uzivatel);
	  if(datumOdN == null || datumOdN.getId() == null){
      datumOdN = new NastaveniSystemu();
      datumOdN.setNazev("datumOd");
      datumOdN.setHodnota(new Cas(new Date()).getDatumDatabaze());
      datumOdN.setUzivatel(uzivatel);
    }
    NastaveniSystemu datumDoN = pripojeni.nacti(NastaveniSystemu.class, "nazev", "datumDo", true, uzivatel);
    if(datumDoN == null || datumDoN.getId() == null){
      datumDoN = new NastaveniSystemu();
      datumDoN.setNazev("datumDo");
      datumDoN.setHodnota(new Cas(new Date()).getDatumDatabaze());
      datumDoN.setUzivatel(uzivatel);
    }
    
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
    }else if(akce.getPrehledObdobi().equals(volanaAkce)){
      Date datumOd = kontrolaDatum("datumOd", request);
      Date datumDo = kontrolaDatum("datumDo", request);
      kontrolaDatumCas(datumOd, datumDo, request, "datumOd");
      
      Object chyba = overChyby(request);
      if(chyba == null){
        datumOdN.setHodnota(new Cas(datumOd).getDatumDatabaze());
        pripojeni.vlozUprav(datumOdN, datumOdN.getId());
        datumDoN.setHodnota(new Cas(datumDo).getDatumDatabaze());
        pripojeni.vlozUprav(datumDoN, datumOdN.getId());
      }
    }
    
    if(!response.isCommitted()){
      List<KalendarCinnost> cinnosti = pripojeni.ziskejCinnosti(datumOdN, datumDoN, uzivatel);
      for (int i = 0; cinnosti != null && i < cinnosti.size(); i++) {
        KalendarCinnost kc = cinnosti.get(i);
        pripojeni.inicializaceObjektu(kc.getCinnost());
        pripojeni.inicializaceObjektu(kc.getPracovniPomer());
      }
      request.setAttribute("objekty", cinnosti);
      request.setAttribute("datumOd", datumOdN);
      request.setAttribute("datumDo", datumDoN);
      request.setAttribute("datepickerFormat", "dd.mm.yy");
  	  presmerovani(request, response, adresa + "/zadane_cinnosti.jsp");
    }
	}
}
