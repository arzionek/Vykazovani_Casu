package servlety.role.zamestnanec;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.beany.Cas;
import dao.databaze.Databaze;
import dao.model.Kalendar;
import dao.model.KalendarCinnost;
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
    
    List<Kalendar> kalendare = pripojeni.ziskejObjekty(Kalendar.class, uzivatel);
    for (int i = 0; kalendare != null && i < kalendare.size(); i++) {
      Kalendar k = kalendare.get(i);
      pripojeni.inicializaceSetu(k.getKalendarCinnost());
    }
    request.setAttribute("kalendare", kalendare); 

		presmerovani(request, response, adresa + "/import.jsp");
	}
}
