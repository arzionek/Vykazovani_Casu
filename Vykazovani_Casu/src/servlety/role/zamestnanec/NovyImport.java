package servlety.role.zamestnanec;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import servlety.nastroje.ImportKalendare;
import dao.beany.Akce;
import dao.model.KalendarDefinice;
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
    provedeniImportu(request, response, uzivatel, akce, volanaAkce);
    presmerovani(request, response, adresa + "/import_nove.jsp");
  }

  private void provedeniImportu(HttpServletRequest request, HttpServletResponse response, Uzivatel uzivatel, Akce akce, String volanaAkce) {
    if (akce.getImportNahrat().equals(volanaAkce)) {
   
      boolean isMultipartCOntent = ServletFileUpload.isMultipartContent(request);
      if (!isMultipartCOntent) {
        //TODO chyba
      }
      else {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
          List<FileItem> fields = upload.parseRequest(request);
          FileItem fileItem = fields.get(1);        
          File file = new File("/cal.ics");
          fileItem.write(file);
          ImportKalendare imp = ImportKalendare.getImport;
          List<KalendarDefinice> definice = pripojeni.ziskejObjekty(KalendarDefinice.class, uzivatel);
          imp.novyKalendar(file, uzivatel, definice);
        } catch (Exception e) {
          e.printStackTrace();
        }   
      }
    }
    
  }
}
