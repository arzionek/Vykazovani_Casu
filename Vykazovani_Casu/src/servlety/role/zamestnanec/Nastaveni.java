package servlety.role.zamestnanec;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import servlety.nastroje.Download;

import dao.beany.Cas;
import dao.beany.Chyby;
import dao.model.Cinnost;
import dao.model.KalendarDefinice;
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
    }else if(akce.getNastaveniSvatky().equals(volano)) {
      vypisAkce("nastaveni_svatku", request);
      nastaveniSvatku(request, response);
    }else if (akce.getNastaveniPomeru().equals(volano)) {
      vypisAkce("nastaveni_pomeru", request);
      nastaveniPomeru(request, response);
    }else if (akce.getNastaveniDefiniceKalendare().equals(volano)) {
      vypisAkce("nastaveni_kalendare", request);
      nastaveniKalendare(request, response);
    }else if (akce.getNastaveniSablon().equals(volano)) {
      vypisAkce("nastaveni_sablony", request);
      nastaveniSablony(request, response);
    }else {    
      vypisAkce("nastaveni_cinnosti", request);
      nastaveniCinnosti(request, response);  
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
        Svatek svatek2 = pripojeni.nacti(Svatek.class, new String[]{"kod", "nazev", "datum"}, new Object[]{kod, nazev, new Cas(datum).getDatumDatabaze()}, uzivatel);
        if(svatek2 != null && svatek2.getId() != svatekId) request.setAttribute(Chyby.DUPLICITNI_ZADANI, "");
        
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
        }else if(request.getAttribute(Chyby.DUPLICITNI_ZADANI) != null){
          String atribut = getShoda(svatek, svatek2);
          request.setAttribute(Chyby.DUPLICITNI_ZADANI, atribut);
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
    List<Svatek> svatky = pripojeni.ziskejObjekty(Svatek.class, uzivatel, "datum");
    request.setAttribute("objekty", svatky);
    request.setAttribute("datepickerFormat", "dd.mm.");
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
        Cinnost cinnost2 = pripojeni.nacti(Cinnost.class, new String[]{"kod", "nazev"}, new Object[]{kod, nazev}, uzivatel);
        if(cinnost2 != null && cinnost2.getId() != cinnostId) request.setAttribute(Chyby.DUPLICITNI_ZADANI, "");
        
        Object chyba = overChyby(request);

        if(cinnostId != 0 && chyba == null) cinnost = pripojeni.nacti(Cinnost.class, cinnostId);
        else if(cinnostId != 0) cinnost.setId(cinnostId);
        cinnost.setKod(kod);
        cinnost.setNazev(nazev);
        cinnost.setUzivatel(uzivatel);
        
        if(chyba == null){
          pripojeni.vlozUprav(cinnost, cinnost.getId());
          cinnost = new Cinnost();
        }else if(request.getAttribute(Chyby.DUPLICITNI_ZADANI) != null){
          String atribut = getShoda(cinnost, cinnost2);
          request.setAttribute(Chyby.DUPLICITNI_ZADANI, atribut);
        }
      }
      vypisAkce("_vlozit", request);
    }else if(akce.getNastaveniCinnostiUpravit().equals(volanaAkce)){
      cinnost = pripojeni.nacti(Cinnost.class, cinnostId);
      vypisAkce("_upravit", request);
    }else if(akce.getNastaveniCinnostiSmazat().equals(volanaAkce)){
      cinnost = pripojeni.nacti(Cinnost.class, cinnostId);
      pripojeni.smaz(cinnost);
      cinnost = new Cinnost();
      vypisAkce("_smazat", request);
    }

    request.setAttribute("objekt", cinnost);
    List<Cinnost> cinnosti = pripojeni.ziskejObjekty(Cinnost.class, uzivatel, "kod");
    for (int i = 0; cinnosti != null && i < cinnosti.size(); i++) {
    Cinnost c = cinnosti.get(i);
    pripojeni.inicializaceSetu(c.getKalendarCinnost());
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
        if(velikost > 1) pridejChybu(request, Chyby.REALNE_CISLO_0_1, "velikostUvazku");
        String typUvazku = (String) kontrola(request, PracovniPomer.class, "typUvazku");
        PracovniPomer pomer2 = pripojeni.nacti(PracovniPomer.class, new String[]{"kod", "nazev"}, new Object[]{kod, nazev}, uzivatel);
        if(pomer2 != null && pomer2.getId() != pomerId) request.setAttribute(Chyby.DUPLICITNI_ZADANI, "");
        
        Object chyba = overChyby(request);

        if(pomerId != 0 && chyba == null) pomer = pripojeni.nacti(PracovniPomer.class, pomerId);
        else if(pomerId != 0) pomer.setId(pomerId);
        pomer.setKod(kod);
        pomer.setNazev(nazev);
        pomer.setVelikostUvazku(velikost);
        pomer.setTypUvazku(typUvazku);
        pomer.setUzivatel(uzivatel);
        
        if(chyba == null){
          pripojeni.vlozUprav(pomer, pomer.getId());
          pomer = new PracovniPomer();
        }else if(request.getAttribute(Chyby.DUPLICITNI_ZADANI) != null){
          String atribut = getShoda(pomer, pomer2);
          request.setAttribute(Chyby.DUPLICITNI_ZADANI, atribut);
        }
      }
      vypisAkce("_vlozit", request);
    } else if(akce.getNastaveniPomeruUpravit().equals(volanaAkce)){
      pomer = pripojeni.nacti(PracovniPomer.class, pomerId);
      vypisAkce("_upravit", request);
    } else if(akce.getNastaveniPomeruSmazat().equals(volanaAkce)){
      pomer = pripojeni.nacti(PracovniPomer.class, pomerId);
      pripojeni.smaz(pomer);
      pomer = new PracovniPomer();
      vypisAkce("_smazat", request);
    }

    request.setAttribute("objekt", pomer);
    List<PracovniPomer> pomery = pripojeni.ziskejObjekty(PracovniPomer.class, uzivatel, "kod");
    for (int i = 0; pomery != null && i < pomery.size(); i++) {
      PracovniPomer p = pomery.get(i);
      pripojeni.inicializaceSetu(p.getKalendarCinnost());
      pripojeni.inicializaceSetu(p.getSablonaVykaz());
    }
    request.setAttribute("objekty", pomery);
    request.setAttribute("typy", PracovniPomer.getTypy());
    presmerovani(request, response, adresa + "/pomery.jsp");
  }

  private void nastaveniKalendare(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	  Uzivatel uzivatel = (Uzivatel) request.getAttribute("uzivatel");
	  KalendarDefinice definice = new KalendarDefinice();
	  long definiceID = vratId(request, "objektId");
	  
	  if(akce.getNastaveniDefiniceKalendareVlozit().equals(volanaAkce)) {
		  String kod = request.getParameter("kod");
	  
		  if(kod != null){
			  kod = (String) kontrola(request, KalendarDefinice.class, "kod");
			  String nazev = (String) kontrola(request, KalendarDefinice.class, "nazev");
	          String tagCinnosti =  (String) kontrola(request, KalendarDefinice.class, "tagKalendarCinnost");
	          String tagUvazku =  (String) kontrola(request, KalendarDefinice.class, "tagPracovniPomer");
			  
	          KalendarDefinice definice2 = pripojeni.nacti(KalendarDefinice.class, new String[]{"kod", "nazev" }, new Object[]{kod, nazev }, uzivatel);
	          if(definice2 != null && definice2.getId() != definiceID) request.setAttribute(Chyby.DUPLICITNI_ZADANI, "");
	          
	          Object chyba = overChyby(request);

	          if(definiceID != 0 && chyba == null) definice = pripojeni.nacti(KalendarDefinice.class, definiceID);
	          else if(definiceID != 0)  definice.setId(definiceID);
	          
	          
	          definice.setKod(kod);
	          definice.setNazev(nazev);
	          definice.setTagKalendarCinnost(tagCinnosti);
	          definice.setTagPracovniPomer(tagUvazku);
	          definice.setUzivatel(uzivatel);
	       
	          if(chyba == null){
	          pripojeni.vlozUprav(definice, definice.getId());
	          definice = new KalendarDefinice();
	          }else if(request.getAttribute(Chyby.DUPLICITNI_ZADANI) != null){
	              String atribut = getShoda(definice, definice2);
	              request.setAttribute(Chyby.DUPLICITNI_ZADANI, atribut);
	          }	
	          
	          
	          vypisAkce("_vlozit", request);
		  }
	  
	  
	  } else if(akce.getNastaveniDefiniceKalendareUpravit().equals(volanaAkce)){
		    
		  definice = pripojeni.nacti(KalendarDefinice.class, definiceID);
		  vypisAkce("_upravit", request);
		      
	  } else if(akce.getNastaveniDefiniceKalendareSmazat().equals(volanaAkce)){
	
		  definice = pripojeni.nacti(KalendarDefinice.class, definiceID);
	      pripojeni.smaz( definice);
	      definice = new KalendarDefinice();
	      vypisAkce("_smazat", request);
	  
	  }
	  
	  request.setAttribute("objekt", definice);
	  List<KalendarDefinice> definiceKalendare = pripojeni.ziskejObjekty(KalendarDefinice.class, uzivatel, "kod");
	  request.setAttribute("objekty", definiceKalendare);
	  presmerovani(request, response, adresa + "/definiceKalendare.jsp");
  }

  
  private void nastaveniSablony(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Uzivatel uzivatel = (Uzivatel) request.getAttribute("uzivatel");
    SablonaVykaz sablona = new SablonaVykaz();
    long sablonaId = vratId(request, "objektId");
    if(akce.getNastaveniSablonVlozit().equals(volanaAkce)) {
      if(sablonaId == 0 && ServletFileUpload.isMultipartContent(request)) sablona = vlozitSablonu(sablona, uzivatel, request);
      else sablona = upravitSablonu(sablona, uzivatel, request, sablonaId);
      vypisAkce("_vlozit", request);
    } else if(akce.getNastaveniSablonUpravit().equals(volanaAkce)){
      sablona = pripojeni.nacti(SablonaVykaz.class, sablonaId);
      pripojeni.inicializaceSetu(sablona.getPracovniPomer());
      vypisAkce("_upravit", request);
    } else if(akce.getNastaveniSablonSmazat().equals(volanaAkce)){
      sablona = pripojeni.nacti(SablonaVykaz.class, sablonaId);
      pripojeni.smaz(sablona);
      sablona = new SablonaVykaz();
      vypisAkce("_smazat", request);
    } else if(akce.getNastaveniSablonStahnout().equals(volanaAkce)){
      sablona = pripojeni.nacti(SablonaVykaz.class, sablonaId);
      pripojeni.inicializaceObjektu(sablona);
      String nazev = "sablona_" + sablona.getKod().replace(" ", "") + ".xls";
      Download.download(response, sablona.getData(), nazev);
      vypisAkce("_stahnout", request);
    } 
    
    request.setAttribute("objekt", sablona);
    List<PracovniPomer> pomery = pripojeni.ziskejObjekty(PracovniPomer.class, uzivatel, "kod");
    request.setAttribute("pomery", pomery);
    List<SablonaVykaz> vykazy = pripojeni.ziskejObjekty(SablonaVykaz.class, uzivatel, "kod");
    request.setAttribute("objekty", vykazy);
    request.setAttribute("typy", SablonaVykaz.getTypy());
    presmerovani(request, response, adresa + "/sablony.jsp");
  }

  private SablonaVykaz vlozitSablonu(SablonaVykaz sablona, Uzivatel uzivatel, HttpServletRequest request) {
    FileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload upload = new ServletFileUpload(factory);
    upload.setHeaderEncoding("UTF-8"); 
    try {
      @SuppressWarnings("unchecked")
      List<FileItem> fields = upload.parseRequest(request);
      String kod = null;
      String nazev = null;
      String typ = null;
      byte[] data = null;
      Set<PracovniPomer> pomery = new HashSet<PracovniPomer>();
      for (int i = 0; i < fields.size(); i++) {
        FileItem fi = fields.get(i);  
        if(!fi.isFormField()){
          if(fi.getName().contains(".xls") && !fi.getName().contains(".xlsx")) data = fi.get();
          else pridejChybu(request, Chyby.PODPOROVANY_FORMAT, "soubor");
        }else{
          if(fi.getFieldName().equals("kod")){
            kod = (String) kontrola(request, SablonaVykaz.class, "kod", Streams.asString(fi.getInputStream(), "UTF-8"));
          }else if(fi.getFieldName().equals("nazev")){
            nazev = (String) kontrola(request, SablonaVykaz.class, "nazev", Streams.asString(fi.getInputStream(), "UTF-8"));
          }else if(fi.getFieldName().equals("typ")){
            typ = (String) kontrola(request, SablonaVykaz.class, "typ", Streams.asString(fi.getInputStream(), "UTF-8"));
          }else if(fi.getFieldName().equals("pomery")){
            try{
              long pomerId = Long.parseLong(fi.getString());
              PracovniPomer p = new PracovniPomer();
              p.setId(pomerId);
              pomery.add(p);
            }catch(Exception e){}
          }
        }
      }
      if(data == null) pridejChybu(request, Chyby.POVINNY_UDAJ, "soubor");
      if(pomery.isEmpty()) pridejChybu(request, Chyby.POVINNY_UDAJ, "pomery");
      SablonaVykaz sablona2 = pripojeni.nacti(SablonaVykaz.class, new String[]{"kod", "nazev"}, new Object[]{kod, nazev}, uzivatel);
      if(sablona2 != null) request.setAttribute(Chyby.DUPLICITNI_ZADANI, "");

      Object chyba = overChyby(request);

      sablona.setKod(kod);
      sablona.setNazev(nazev);
      sablona.setTyp(typ);
      sablona.setData(data);
      sablona.setPracovniPomer(pomery);
      sablona.setUzivatel(uzivatel);

      if(chyba == null){
        pripojeni.vlozUprav(sablona, sablona.getId());
        sablona = new SablonaVykaz();
      }else if(request.getAttribute(Chyby.DUPLICITNI_ZADANI) != null){
        String atribut = getShoda(sablona, sablona2);
        request.setAttribute(Chyby.DUPLICITNI_ZADANI, atribut);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sablona;
  }

  private SablonaVykaz upravitSablonu(SablonaVykaz sablona, Uzivatel uzivatel, HttpServletRequest request, long sablonaId) {
    String kod = (String) kontrola(request, SablonaVykaz.class, "kod");
    String nazev = (String) kontrola(request, SablonaVykaz.class, "nazev");
    String typ = (String) kontrola(request, SablonaVykaz.class, "typ");
    Set<PracovniPomer> pomery = new HashSet<PracovniPomer>();
    String[] ids = vratIdObjektù(request, "pomery");
    for (int i = 0; i < ids.length; i++) {
      try{
        long pomerId = Long.parseLong(ids[i]);
        PracovniPomer p = new PracovniPomer();
        p.setId(pomerId);
        pomery.add(p);
      }catch(Exception e){}
    }
    SablonaVykaz sablona2 = pripojeni.nacti(SablonaVykaz.class, new String[]{"kod", "nazev"}, new Object[]{kod, nazev}, uzivatel);
    if(sablona2 != null && sablona2.getId() != sablonaId) request.setAttribute(Chyby.DUPLICITNI_ZADANI, "");

    Object chyba = overChyby(request);

    if(chyba == null) sablona = pripojeni.nacti(SablonaVykaz.class, sablonaId);
    else if(sablonaId != 0) sablona.setId(sablonaId);
    sablona.setKod(kod);
    sablona.setNazev(nazev);
    sablona.setTyp(typ);
    sablona.setPracovniPomer(pomery);

    if(chyba == null){
      pripojeni.vlozUprav(sablona, sablona.getId());
      sablona = new SablonaVykaz();
    }else if(request.getAttribute(Chyby.DUPLICITNI_ZADANI) != null){
      String atribut = getShoda(sablona, sablona2);
      request.setAttribute(Chyby.DUPLICITNI_ZADANI, atribut);
    }
    return sablona;
  }
  
}
