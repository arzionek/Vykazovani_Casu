package servlety.role.zamestnanec;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.beany.Akce;
import dao.beany.Cas;
import dao.beany.Chyby;
import dao.beany.Oznameni;
import dao.databaze.Databaze;
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
	  vytvoreniCinnosti(request, response, uzivatel, kalendarCinnost, pripojeni, akce, volanaAkce);
	  presmerovani(request, response, adresa + "/zadane_cinnosti_nove.jsp");
	}

	public static double vratPocetHodin(Date casOd, Date casDo) {
    double rozdil = casDo.getTime() - casOd.getTime();    
    rozdil /= 1000; //na sekundy
    rozdil /= 60; //na minuty
    rozdil /= 60; //na hodiny
    return rozdil;
  }
	
  public static void vytvoreniCinnosti(HttpServletRequest request, HttpServletResponse response, Uzivatel uzivatel, KalendarCinnost kalendarCinnost, Databaze pripojeni, Akce akce, String volanaAkce) {
    long kalendarCinnostId = vratId(request, "objektId");
    if(akce.getVytvoreniVlozit().equals(volanaAkce)){
      //if(kod != null){
        String datumText = request.getParameter("datum");
        Date datum = (Date) kontrola(request, KalendarCinnost.class, "datum");
        String casOdText = request.getParameter("casOd");
        Date casOd = (Date) kontrola(request, KalendarCinnost.class, "casOd");
        String casDoText = request.getParameter("casDo");
        Date casDo = (Date) kontrola(request, KalendarCinnost.class, "casDo");
        kontrolaDatumCas(casOd, casDo, request, "casOd");
        double pocetHodin = vratPocetHodin(casOd, casDo);
        kontrolaDelkyCinnosti(request, pocetHodin, "casDo");
        long pomerId = vratId(request, "pomer");
        long cinnostId = vratId(request, "cinnost");
        String popis = (String) kontrola(request, KalendarCinnost.class, "popis");
        if (datum != null && casDo != null && casDo != null) {
          List<KalendarCinnost> cinnostList = pripojeni.ziskejObjekty(KalendarCinnost.class, new String[]{"datum"}, new Object[]{new Cas(datum).getDatumDatabaze(false)}, true, uzivatel, new String[]{"casOd asc"});
          zkontrolujZadaneCinnosti(request, cinnostList, casOd, casDo, kalendarCinnostId);
        }
        
        Object chyba = overChyby(request);
        
        if(kalendarCinnostId != 0 && chyba == null) kalendarCinnost = pripojeni.nacti(KalendarCinnost.class, kalendarCinnostId);
        else if(kalendarCinnostId != 0) kalendarCinnost.setId(kalendarCinnostId);
        kalendarCinnost.setDatum2(datumText);
        kalendarCinnost.setDatum(datum);
        kalendarCinnost.setCasOd2(casOdText);
        kalendarCinnost.setCasOd(casOd);
        kalendarCinnost.setCasDo2(casDoText);
        kalendarCinnost.setCasDo(casDo);
        if(request.getAttribute(Chyby.PLATNE_DATUM_POROVNANI) == null && casOd != null && casDo != null) kalendarCinnost.setPocetHodin(vratPocetHodin(casOd, casDo));
        PracovniPomer pomer = pripojeni.nacti(PracovniPomer.class, pomerId);
        kalendarCinnost.setPracovniPomer(pomer);
        Cinnost cinnost = pripojeni.nacti(Cinnost.class, cinnostId);
        kalendarCinnost.setCinnost(cinnost);
        kalendarCinnost.setPopis(popis);
        kalendarCinnost.setUzivatel(uzivatel);
        
        if(chyba == null){
          pripojeni.vlozUprav(kalendarCinnost, kalendarCinnost.getId());
          request.setAttribute(Oznameni.VLOZENI_ZAZNAMU, true);
          kalendarCinnost = new KalendarCinnost();
        }
      //}
      vypisAkce("_vlozit", request);
    }
    
    if (kalendarCinnost.getDatum() == null) kalendarCinnost.setDatum(getDatum(new Date()));
    request.setAttribute("objekt", kalendarCinnost);
    List<Cinnost> cinnosti = pripojeni.ziskejObjekty(Cinnost.class, uzivatel);
    request.setAttribute("cinnosti", cinnosti);
    List<PracovniPomer> pomery = pripojeni.ziskejObjekty(PracovniPomer.class, uzivatel);
    request.setAttribute("pomery", pomery);
    request.setAttribute("datepickerFormat", "dd.mm.yy");
  }

  private static void zkontrolujZadaneCinnosti(HttpServletRequest request, List<KalendarCinnost> cinnostList, Date casOd, Date casDo, long kalendarCinnostId) {
    boolean chyba = false;
    for (int i = 0; cinnostList != null && i < cinnostList.size(); i++) {
      KalendarCinnost cinnost2 = cinnostList.get(i);
      Date casOd2 = new Date(cinnost2.getCasOd().getTime() + 1);
      Date casDo2 = new Date(cinnost2.getCasDo().getTime() - 1);
      if(casOd.after(casDo2) || casDo.before(casOd2) || kalendarCinnostId == cinnost2.getId()){
        
      }else{
        chyba = true;
      }
    }
    if(chyba) request.setAttribute(Chyby.DUPLICITNI_ZADANI, "casOd"); 
  }
  
  private static Date getDatum(Date datum) {
    java.util.Calendar cal = java.util.Calendar.getInstance();
    cal.setTime(datum);
    cal.set(java.util.Calendar.HOUR, 0);
    cal.set(java.util.Calendar.MINUTE, 0);
    cal.set(java.util.Calendar.SECOND, 0);
    cal.set(java.util.Calendar.MILLISECOND, 0);
    return cal.getTime();
  }
}
