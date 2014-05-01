package servlety.role.zamestnanec;

import java.io.IOException;
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
	    Date datumOd = kontrolaDatum("datumOd", request);
      Date datumDo = kontrolaDatum("datumDo", request);
      kontrolaDatumCas(datumOd, datumDo, request, "datumOd");
	    long sablonaVykazId = vratId(request, "sablona");
	    if(sablonaVykazId == 0) pridejChybu(request, Chyby.POVINNY_UDAJ, "sablona");
	    else export.getSablonaVykaz().setId(sablonaVykazId);
	    
	    Object chyba = overChyby(request);
	    
	    export.setDatumOd(datumOd);
      export.setDatumDo(datumDo);
      
	    if(chyba == null){
	       ExportDoSablony.provestExport(export, pripojeni);
	    }
	  }
	  
	  List<PracovniPomer> pomery = pripojeni.ziskejObjekty(PracovniPomer.class, uzivatel, "kod");
    request.setAttribute("pomery", pomery);
	  request.setAttribute("objekt", export);
	  request.setAttribute("datepickerFormat", "dd.mm.yy");
	  presmerovani(request, response, adresa + "/export.jsp");	
	}

}
