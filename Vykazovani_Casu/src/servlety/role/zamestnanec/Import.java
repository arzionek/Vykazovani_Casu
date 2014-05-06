package servlety.role.zamestnanec;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlety.nastroje.Download;

import dao.beany.Cas;
import dao.databaze.Databaze;
import dao.model.Kalendar;
import dao.model.KalendarCinnost;
import dao.model.NastaveniSystemu;
import dao.model.Uzivatel;

public class Import extends AServletZamestnanec{

	private static final long serialVersionUID = -7726309060094642664L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		if(!response.isCommitted()){
			System.out.println(new Cas().ziskejDatum() + " - _import: " + request.getSession().getAttribute("login"));
			importDatZKalendare(request, response, pripojeni);
		}
	}
	
	private void importDatZKalendare(HttpServletRequest request, HttpServletResponse response, Databaze pripojeni) throws ServletException, IOException {	  
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
    
    Kalendar kalendar = new Kalendar();
    long kalendarId = vratId(request, "objektId");
    if (akce.getImportSmazat().equals(volanaAkce)) {
      kalendar = pripojeni.nacti(Kalendar.class, kalendarId);
      pripojeni.inicializaceSetu(kalendar.getKalendarCinnost());
      Set<KalendarCinnost> cinnosti = kalendar.getKalendarCinnost();
      for (KalendarCinnost cinnost : cinnosti) {
        pripojeni.smaz(cinnost);
      }
      pripojeni.smaz(kalendar);
      kalendar = new Kalendar();
      vypisAkce("_smazat", request);
    }
    else if (akce.getImportStahnout().equals(volanaAkce)) {
      kalendar = pripojeni.nacti(Kalendar.class, kalendarId);
      Download.download(response, kalendar.getData(), "kalendar.ics");
      kalendar = new Kalendar();
      vypisAkce("stahnout", request);
    }
    else if (akce.getImportObdobi().equals(volanaAkce)) {
      Date datumOd = kontrolaDatum("datumOd", null, request);
      if (datumOd == null) datumOd = new Date();
      Date datumDo = kontrolaDatum("datumDo", null, request);
      if (datumDo == null) datumDo = new Date();
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
      List<Kalendar> kalendare = pripojeni.ziskejImporty(datumOdN, datumDoN, uzivatel);
      for (int i = 0; kalendare != null && i < kalendare.size(); i++) {
        Kalendar k = kalendare.get(i);
        pripojeni.inicializaceSetu(k.getKalendarCinnost());
      }
      request.setAttribute("kalendare", kalendare); 
      request.setAttribute("datumOd", datumOdN);
      request.setAttribute("datumDo", datumDoN);
      request.setAttribute("datepickerFormat", "dd.mm.yy");
      presmerovani(request, response, adresa + "/import.jsp");
    }
	}
}
