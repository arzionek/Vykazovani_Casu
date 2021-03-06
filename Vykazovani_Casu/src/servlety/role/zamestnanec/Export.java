package servlety.role.zamestnanec;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlety.nastroje.ExportDoSablony;

import dao.beany.Cas;
import dao.beany.Chyby;
import dao.databaze.Databaze;
import dao.model.ExportSablona;
import dao.model.PracovniPomer;
import dao.model.SablonaVykaz;
import dao.model.Uzivatel;

public class Export extends AServletZamestnanec{

	private static final long serialVersionUID = 4012199642961685377L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		if(!response.isCommitted()){
			System.out.println(new Cas().ziskejDatum() + " - _export: " + request.getSession().getAttribute("login"));
			export(request, response, pripojeni);
		}
	}

	private void export(HttpServletRequest request, HttpServletResponse response, Databaze pripojeni) throws ServletException, IOException {
	  Uzivatel uzivatel = (Uzivatel) request.getAttribute("uzivatel");
	  ExportSablona export = new ExportSablona();
	  export.setUzivatel(uzivatel);
	  
	  if(akce.getExportPomer().equals(volanaAkce) || akce.getExportXls().equals(volanaAkce)){
  	  long pracovniPomerId = vratId(request, "pomer");
  	  if(pracovniPomerId != 0){
  	    export.getPracovniPomer().setId(pracovniPomerId);
  	    List<SablonaVykaz> sablony = pripojeni.ziskejObjekty(SablonaVykaz.class, new String[]{"pracovniPomer.id"}, new String[]{"" + export.getPracovniPomer().getId()}, true, uzivatel, new String[]{"typ"});
  	    request.setAttribute("sablony", sablony);
  	  }
	  }
	  if(akce.getExportXls().equals(volanaAkce)){
	    Date datumOd = kontrolaDatum("mesic", null, request);
	    long sablonaVykazId = vratId(request, "sablona");
	    long pracovniPomerId = vratId(request, "pomer");
	    if(sablonaVykazId == 0) pridejChybu(request, Chyby.POVINNY_UDAJ, "sablona");
	    else export.getSablonaVykaz().setId(sablonaVykazId);
	    
	    Object chyba = overChyby(request);
	    
	    export.setDatumOd(datumOd);
      export.setDatumDo(setDatumDo(datumOd));
      export.getPracovniPomer().setId(pracovniPomerId);
      
	    if(chyba == null){
	       ExportDoSablony.provestExport(response, export, pripojeni, request);
	       Object chyba2 = overChyby(request);
	       if(chyba2 == null) export = new ExportSablona();
	    }
	  }
	  
	  if (export.getDatumOd() == null) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(new Date());	    
	    cal.set(Calendar.HOUR_OF_DAY, 0);
      cal.set(Calendar.MINUTE, 0);
      cal.set(Calendar.SECOND, 0);
      cal.set(Calendar.MILLISECOND, 0);
	    int den = cal.get(Calendar.DATE);
	    if (den <= 15) {
	      cal.set(Calendar.DATE, 1);
	    }
	    else {
	      cal.add(Calendar.MONTH, 1);
	      cal.set(Calendar.DATE, 1);
	    }
	    export.setDatumOd(cal.getTime());
	  }
	  List<PracovniPomer> pomery = pripojeni.ziskejObjekty(PracovniPomer.class, uzivatel, "kod");
    request.setAttribute("pomery", pomery);
	  request.setAttribute("objekt", export);
	  presmerovani(request, response, adresa + "/export.jsp");	
	}
	
	private Date setDatumDo(Date datumOd) {
	  if (datumOd == null) return null;
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(datumOd);
	  cal.set(Calendar.DATE, cal.getMaximum(Calendar.DATE)); 
	  return cal.getTime();
	}
}
