package servlety.role.zamestnanec;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.beany.Cas;
import dao.beany.Chyby;
import dao.databaze.Databaze;
import dao.model.KalendarCinnost;
import dao.model.NastaveniSystemu;
import dao.model.PracovniPomer;
import dao.model.Svatek;
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
      datumOdN.setHodnota(new Cas(new Date()).getDatumDatabaze(false));
      datumOdN.setUzivatel(uzivatel);
    }
    NastaveniSystemu datumDoN = pripojeni.nacti(NastaveniSystemu.class, "nazev", "datumDo", true, uzivatel);
    if(datumDoN == null || datumDoN.getId() == null){
      datumDoN = new NastaveniSystemu();
      datumDoN.setNazev("datumDo");
      datumDoN.setHodnota(new Cas(new Date()).getDatumDatabaze(true));
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
      Date datumOd = kontrolaDatum("datumOd", null, request);
      Date datumDo = kontrolaDatum("datumDo", null, request);
      kontrolaDatumCas(datumOd, datumDo, request, "datumOd");
      
      Object chyba = overChyby(request);
      if(chyba == null){
        datumOdN.setHodnota(new Cas(datumOd).getDatumDatabaze(false));
        pripojeni.vlozUprav(datumOdN, datumOdN.getId());
        datumDoN.setHodnota(new Cas(datumDo).getDatumDatabaze(true));
        pripojeni.vlozUprav(datumDoN, datumOdN.getId());
      }
    }
    
    if(!response.isCommitted()){
      List<KalendarCinnost> cinnosti = pripojeni.ziskejCinnosti(datumOdN, datumDoN, uzivatel);
      List<Svatek> svatky = pripojeni.ziskejObjekty(Svatek.class, uzivatel, "datum");
      nastavCinnosti(cinnosti, svatky);
      List<PracovniPomer> pomery = pripojeni.ziskejObjekty(PracovniPomer.class, uzivatel, "kod");
      nastavPomery(pomery, svatky, datumOdN);
      request.setAttribute("objekty", cinnosti);
      request.setAttribute("pomery", pomery);
      request.setAttribute("datumOd", datumOdN);
      request.setAttribute("datumDo", datumDoN);
      Cas cas = new Cas(datumOdN.getHodnota());
      request.setAttribute("obdobi", cas.getMesicRok() + " " + cas.getRok());
      request.setAttribute("datepickerFormat", "dd.mm.yy");
  	  presmerovani(request, response, adresa + "/zadane_cinnosti.jsp");
    }
	}

  private void nastavCinnosti(List<KalendarCinnost> cinnosti, List<Svatek> svatky) {
    for (int i = 0; cinnosti != null && i < cinnosti.size(); i++) {
      KalendarCinnost kc = cinnosti.get(i);
      pripojeni.inicializaceObjektu(kc.getCinnost());
      pripojeni.inicializaceObjektu(kc.getPracovniPomer());
      kc.setVarovani(ziskejVarovani(kc, svatky));
      Cas cas = new Cas(kc.getDatum());
      kc.setDatum3(cas.getDenTydenZkratka() + " " + cas.getDatum());
    }
  }

  private void nastavPomery(List<PracovniPomer> pomery, List<Svatek> svatky, NastaveniSystemu datumOdN) {
    Cas casMesicOd = new Cas(datumOdN.getHodnota());
    casMesicOd.setDen(1);
    Cas casMesicDo = new Cas(datumOdN.getHodnota());
    casMesicDo.setDen(31);
    for (int i = 0; pomery != null && i < pomery.size(); i++) {
      PracovniPomer p = pomery.get(i);
      List<KalendarCinnost> cin = pripojeni.ziskejCinnosti(casMesicOd.getDatumDatabaze(false), casMesicDo.getDatumDatabaze(true), p);
      p.setMesicniFond(ziskejMesicniFond(p, casMesicDo, svatky));
      p.setOdpracovano(ziskejOdpracovano(cin));
      if(p.getOdpracovano() > p.getMesicniFond()) p.setVarovani(Chyby.POMER_MESICNI_FOND);
      else p.setVarovani(" ");
    }
  }

  private double ziskejOdpracovano(List<KalendarCinnost> cin) {
    double odpracovano = 0;
    for (int i = 0; i < cin.size(); i++) {
      odpracovano += cin.get(i).getPocetHodin();
    }
    return odpracovano;
  }

  private double ziskejMesicniFond(PracovniPomer p, Cas casMesicDo, List<Svatek> svatky) {
    double fond = 0;
    Cas cas = new Cas(casMesicDo.getDatumDatabaze(false));
    for (int i = 1; i <= casMesicDo.getDen(); i++) {
      cas.setDen(i);
      cas.nastavDen(i, cas.getMesic(), cas.getRok());
      if(!cas.getDenTydenZkratka().contains("Ne") && !cas.getDenTydenZkratka().contains("So") && !jeSvatek(cas, svatky)){ 
        fond += (8.0 * p.getVelikostUvazku());
      }
    }
    return fond;
  }

  private boolean jeSvatek(Cas cas, List<Svatek> svatky) {
    for (int i = 0; svatky != null && i < svatky.size(); i++) {
      Cas casSv = new Cas(svatky.get(i).getDatum());
      if(casSv.getMesic() == cas.getMesic() && casSv.getDen() == cas.getDen()){
        return true;
      }
    }
    return false;
  }

  private String ziskejVarovani(KalendarCinnost kc, List<Svatek> svatky) {
    String varovani = "";
    
    Cas cas = new Cas(kc.getDatum());
    if(cas.getDenTydenZkratka().contains("Ne") || cas.getDenTydenZkratka().contains("So")) varovani += " " + Chyby.PRACE_VIKEND;
    else if(jeSvatek(cas, svatky)) varovani += " " + Chyby.PRACE_SVATEK;
    
    return varovani;
  }
}
