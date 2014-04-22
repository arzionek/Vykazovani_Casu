package servlety.role.zamestnanec;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.beany.Cas;
import dao.beany.Chyby;
import dao.model.Cinnost;
import dao.model.KalendarCinnost;
import dao.model.PracovniPomer;
import dao.model.SablonaVykaz;
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
    if(akce.getNastaveniCinnosti().equals(volano)) {
      vypisAkce("nastaveni_cinnosti", request);
      nastaveniCinnosti(request, response);  
    } 
    else if(akce.getNastaveniSvatky().equals(volano)) {
      vypisAkce("nastaveni_svatku", request);
      nastaveniSvatku(request, response);
    } 
    else if (akce.getNastaveniPomeru().equals(volano)) {
      vypisAkce("nastaveni_pomeru", request);
      nastaveniPomeru(request, response);
    }
    else {    
      presmerovani(request, response, adresa + "/nastaveni.jsp");
    }
  }

  private void nastaveniSvatku(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Uzivatel uzivatel = (Uzivatel) request.getAttribute("uzivatel");  
    Svatek svatek = new Svatek();
    long svatekId = vratId(request, "objektId");
    if(akce.getNastaveniSvatkyVlozit().equals(volanaAkce)){
      String kod = request.getParameter("kod");
      if(kod != null){
        kod = (String) kontrola(request, Svatek.class, "kod");
        String nazev = (String) kontrola(request, Svatek.class, "nazev");
        Date datum = (Date) kontrola(request, Svatek.class, "datum");
        Svatek svatek2 = pripojeni.nacti(Svatek.class, new String[]{"kod", "nazev", "datum"}, new Object[]{kod, nazev, new Cas(datum).getDatumDatabaze()});
        if(svatek2 != null && svatek2.getId() != svatekId) request.setAttribute(Chyby.DUPLICITNI_ZADANI, Chyby.DUPLICITNI_ZADANI_ZPRAVA);
        
        Object chyba = overChyby(request);
        
        if(svatekId != 0 && chyba == null) svatek = pripojeni.nacti(Svatek.class, svatekId);
        else if(svatekId != 0) svatek.setId(svatekId);
        svatek.setKod(kod);
        svatek.setNazev(nazev);
        svatek.setDatum(datum);
        svatek.setUzivatel(uzivatel);
        
        if(chyba == null){
          pripojeni.vlozUprav(svatek, svatek.getId());
          svatek = new Svatek();
        }
      }
      vypisAkce("_vlozit", request);
    }else if(akce.getNastaveniSvatkyUpravit().equals(volanaAkce)){
      svatek = pripojeni.nacti(Svatek.class, svatekId);
      vypisAkce("_upravit", request);
    }else if(akce.getNastaveniSvatkySmazat().equals(volanaAkce)){
      svatek = pripojeni.nacti(Svatek.class, svatekId);
      pripojeni.smaz(svatek);
      svatek = new Svatek();
      vypisAkce("_smazat", request);
    }
    
    request.setAttribute("objekt", svatek);
    List<Svatek> svatky = pripojeni.ziskejObjekty(Svatek.class, new Object[]{"uzivatel.id"}, new Object[]{uzivatel.getId()});
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
        kod = (String) kontrola(request, Cinnost.class, "kod");
        String nazev = (String) kontrola(request, Cinnost.class, "nazev");
        Cinnost cinnost2 = pripojeni.nacti(Cinnost.class, new String[]{"kod", "nazev"}, new Object[]{kod, nazev});
        if(cinnost2 != null && cinnost2.getId() != cinnostId) request.setAttribute(Chyby.DUPLICITNI_ZADANI, Chyby.DUPLICITNI_ZADANI_ZPRAVA);
        
        Object chyba = overChyby(request);

        if(cinnostId != 0 && chyba == null) cinnost = pripojeni.nacti(Cinnost.class, cinnostId);
        else if(cinnostId != 0) cinnost.setId(cinnostId);
        cinnost.setKod(kod);
        cinnost.setNazev(nazev);
        cinnost.setUzivatel(uzivatel);
        
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
      cinnost = new Cinnost();
      vypisAkce("_smazat", request);
    }

    request.setAttribute("objekt", cinnost);
    List<Cinnost> cinnosti = pripojeni.ziskejObjekty(Cinnost.class, new Object[]{"uzivatel.id"}, new Object[]{uzivatel.getId()});
    Iterator<?> it = cinnosti.iterator();
    while (it.hasNext()) {
      Cinnost c = (Cinnost) it.next();
      Set<KalendarCinnost> set = c.getKalendarCinnost();
      pripojeni.inicializaceSetu(set);
    }
    request.setAttribute("objekty", cinnosti);
    presmerovani(request, response, adresa + "/cinnosti.jsp");
  }

  private void nastaveniPomeru(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Uzivatel uzivatel = (Uzivatel) request.getAttribute("uzivatel");
    PracovniPomer pomer = new PracovniPomer();
    long pomerId = vratId(request, "objektId");
    if(akce.getNastaveniPomeruVlozit().equals(volanaAkce)) {
      String kod = request.getParameter("kod");
      if(kod != null){
        kod = (String) kontrola(request, PracovniPomer.class, "kod");
        String nazev = (String) kontrola(request, PracovniPomer.class, "nazev");
        double velikost = (Double) kontrola(request, PracovniPomer.class, "velikostUvazku");
        PracovniPomer pomer2 = pripojeni.nacti(PracovniPomer.class, new String[]{"kod", "nazev"}, new Object[]{kod, nazev});
        if(pomer2 != null && pomer2.getId() != pomerId) request.setAttribute(Chyby.DUPLICITNI_ZADANI, Chyby.DUPLICITNI_ZADANI_ZPRAVA);
        
        Object chyba = overChyby(request);

        if(pomerId != 0 && chyba == null) pomer = pripojeni.nacti(PracovniPomer.class, pomerId);
        else if(pomerId != 0) pomer.setId(pomerId);
        pomer.setKod(kod);
        pomer.setNazev(nazev);
        pomer.setVelikostUvazku(velikost);
        pomer.setUzivatel(uzivatel);
        
        if(chyba == null){
          pripojeni.vlozUprav(pomer, pomer.getId());
          pomer = new PracovniPomer();
        }
      }
      vypisAkce("_vlozit", request);
    } else if(akce.getNastaveniPomeruUpravit().equals(volanaAkce)){
      pomer = (PracovniPomer) pripojeni.nacti(PracovniPomer.class, pomerId);
      vypisAkce("_upravit", request);
    } else if(akce.getNastaveniPomeruSmazat().equals(volanaAkce)){
      pomer = (PracovniPomer) pripojeni.nacti(PracovniPomer.class, pomerId);
      pripojeni.smaz(pomer);
      pomer = new PracovniPomer();
      vypisAkce("_smazat", request);
    }

    request.setAttribute("objekt", pomer);
    List<PracovniPomer> pomery = pripojeni.ziskejObjekty(PracovniPomer.class, new Object[]{"uzivatel.id"}, new Object[]{uzivatel.getId()});
    Iterator<?> it = pomery.iterator();
    while (it.hasNext()) {
      PracovniPomer p = (PracovniPomer) it.next();
      Set<KalendarCinnost> set1 = p.getKalendarCinnost();
      pripojeni.inicializaceSetu(set1);
      Set<SablonaVykaz> set2 = p.getSablonaVykaz();
      pripojeni.inicializaceSetu(set2);
    }
    request.setAttribute("objekty", pomery);
    presmerovani(request, response, adresa + "/pomery.jsp");
  }
}
