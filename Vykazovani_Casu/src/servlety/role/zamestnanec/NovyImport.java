package servlety.role.zamestnanec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.beany.Akce;
import dao.databaze.Databaze;
import dao.model.Kalendar;
import dao.model.Uzivatel;

public class NovyImport extends AServletZamestnanec {
  
  private static final long serialVersionUID = 2531087300109453246L;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    super.doGet(request, response);
    if(!response.isCommitted()){
      vypisAkce("novyImport", request);
      novyImport(request, response);
    }
  }

  private void novyImport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Uzivatel uzivatel = (Uzivatel) request.getAttribute("uzivatel");  
    Kalendar kalendar = new Kalendar();
    provedeniImportu(request, response, uzivatel, kalendar, pripojeni, akce, volanaAkce);
    presmerovani(request, response, adresa + "/import_nove.jsp");
  }

  private void provedeniImportu(HttpServletRequest request, HttpServletResponse response, Uzivatel uzivatel, Kalendar kalendar, Databaze pripojeni, Akce akce, String volanaAkce) {
    long kalendarId = vratId(request, "objektId");
    if (akce.getImportNahrat().equals(volanaAkce)) {
      //
    }
  }
}
