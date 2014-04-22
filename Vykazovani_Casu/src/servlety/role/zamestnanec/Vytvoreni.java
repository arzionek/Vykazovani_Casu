package servlety.role.zamestnanec;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.beany.Cas;
import dao.beany.Chyby;
import dao.model.Cinnost;
import dao.model.KalendarCinnost;
import dao.model.PracovniPomer;
import dao.model.Uzivatel;

public class Vytvoreni extends AServletZamestnanec{

	private static final long serialVersionUID = 5600900190713821993L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		if(!response.isCommitted()){
		  vypisAkce("vytvoreni", request);
			vytvoreni(request, response);
		}
	}

	private void vytvoreni(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  Uzivatel uzivatel = (Uzivatel) request.getAttribute("uzivatel");  
    KalendarCinnost kalendarCinnost = new KalendarCinnost();
    long kalendarCinnostId = vratId(request, "cinnostId");
    if(akce.getVytvoreniVlozit().equals(volanaAkce)){
      //if(kod != null){
        Date datum = (Date) kontrola(request, KalendarCinnost.class, "datum");
        Date casOd = (Date) kontrola(request, KalendarCinnost.class, "casOd");
        Date casDo = (Date) kontrola(request, KalendarCinnost.class, "casDo");
        long pomerId = vratId(request, "pomer");
        long cinnostId = vratId(request, "cinnost");
        KalendarCinnost cinnost2 = pripojeni.nacti(KalendarCinnost.class, new String[]{"uzivatel.id", "pracovniPomer.id", "cinnost.id", "datum"}, new Object[]{uzivatel.getId(), pomerId, cinnostId, new Cas(datum).getDatumDatabaze()}, true);
        if(cinnost2 != null && cinnost2.getId() != kalendarCinnostId) request.setAttribute(Chyby.DUPLICITNI_ZADANI, Chyby.DUPLICITNI_ZADANI_ZPRAVA);
        
        Object chyba = overChyby(request);
        
        if(kalendarCinnostId != 0 && chyba == null) kalendarCinnost = pripojeni.nacti(KalendarCinnost.class, kalendarCinnostId);
        else if(kalendarCinnostId != 0) kalendarCinnost.setId(kalendarCinnostId);
        kalendarCinnost.setDatum(datum);
        kalendarCinnost.setCasOd(casOd);
        kalendarCinnost.setCasDo(casDo);
        kalendarCinnost.setPocetHodin(vratPocetOdpracovanychHodin(casOd, casDo));
        PracovniPomer pomer = pripojeni.nacti(PracovniPomer.class, pomerId);
        kalendarCinnost.setPracovniPomer(pomer);
        Cinnost cinnost = pripojeni.nacti(Cinnost.class, cinnostId);
        kalendarCinnost.setCinnost(cinnost);
        kalendarCinnost.setUzivatel(uzivatel);
        
        if(chyba == null){
          pripojeni.vlozUprav(kalendarCinnost, kalendarCinnost.getId());
          kalendarCinnost = new KalendarCinnost();
        }
      //}
      vypisAkce("_vlozit", request);
    }
    
    request.setAttribute("objekt", kalendarCinnost);
    List<Cinnost> cinnosti = pripojeni.ziskejObjekty(Cinnost.class, new Object[]{"uzivatel.id"}, new Object[]{uzivatel.getId()});
    request.setAttribute("cinnosti", cinnosti);
    List<PracovniPomer> pomery = pripojeni.ziskejObjekty(PracovniPomer.class, new Object[]{"uzivatel.id"}, new Object[]{uzivatel.getId()});
    request.setAttribute("pomery", pomery);
    request.setAttribute("datepickerFormat", "dd.mm.yy");
    presmerovani(request, response, adresa + "/zadane_cinnosti_nove.jsp");
	}
}
