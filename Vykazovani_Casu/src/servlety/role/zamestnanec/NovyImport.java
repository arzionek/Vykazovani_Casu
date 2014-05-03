package servlety.role.zamestnanec;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.VEvent;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.beany.Akce;
import dao.model.Cinnost;
import dao.model.Kalendar;
import dao.model.KalendarCinnost;
import dao.model.KalendarDefinice;
import dao.model.PracovniPomer;
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
    Kalendar kalendar = new Kalendar();
    if (akce.getImportNahrat().equals(volanaAkce)) {  
      boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
      if (!isMultipartContent) {
        kontrolaChybnySoubor(request, isMultipartContent, "soubor");
      }
      else {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
          @SuppressWarnings("unchecked")
          List<FileItem> fields = upload.parseRequest(request);
          FileItem fileItem = fields.get(0);
          File file = new File("cal.ics");
          fileItem.write(file);
          FileInputStream stream = new FileInputStream(file);

          kontrolaChybnySoubor(request, stream, "soubor");

          if (overChyby(request) == null) {
            stream = new FileInputStream(file);

            long kalendarDefiniceId = vratId(request, "definice");
            List<KalendarDefinice> definice = pripojeni.ziskejObjekty(KalendarDefinice.class, uzivatel);
            KalendarDefinice kalendarDefinice = definice.get(1);  //TODO
            for (int i = 0; i < definice.size(); i++) {
              if (definice.get(i).getId() == kalendarDefiniceId) {
                kalendarDefinice = definice.get(i);
                break;
              }
            }

            kalendar.setData(fileItem.get());
            kalendar.setDatumImportu(new Date());
            kalendar.setUzivatel(uzivatel);
            kalendar.setKalendarDefinice(kalendarDefinice);     
            pripojeni.vlozUprav(kalendar, kalendar.getId());

            novyKalendar(stream, uzivatel, kalendarDefinice, kalendar);

            stream.close();
            file.delete();
          }
        } catch (Exception e) {
          e.printStackTrace();
        }   
      }
    }

    request.setAttribute("objekt", kalendar);
    List<KalendarDefinice> definice = pripojeni.ziskejObjekty(KalendarDefinice.class, uzivatel);
    request.setAttribute("definice", definice);
  }

  private void novyKalendar(FileInputStream stream, Uzivatel uzivatel, KalendarDefinice kd, Kalendar kalendar) {
    try {
      CalendarBuilder builder = new CalendarBuilder();
      Calendar calendar = builder.build(stream);

      String pomStart = "<" + kd.getTagPracovniPomer() + ">";
      String pomKonec = "</" + kd.getTagPracovniPomer() + ">";
      String cinStart = "<" + kd.getTagKalendarCinnost() + ">";
      String cinKonec = "</" + kd.getTagKalendarCinnost() + ">";

      for (Iterator<?> i = calendar.getComponents(Component.VEVENT).iterator(); i.hasNext(); ) {
        VEvent component = (VEvent) i.next();
        String text = component.getDescription().getValue();
        if (text.equals("")) text = component.getSummary().getValue();

        if (text.contains(pomStart) && text.contains(pomKonec) 
            && text.contains(cinStart) && text.contains(cinKonec)) {
          String pomer = text.substring(text.lastIndexOf(pomStart) + pomStart.length(), text.indexOf(pomKonec));
          String cinnost = text.substring(text.lastIndexOf(cinStart) + cinStart.length(), text.indexOf(cinKonec));
          Date start = component.getStartDate().getDate();
          Date end = component.getEndDate().getDate();
          ulozUdalost(start, end, uzivatel, kalendar, pomer, cinnost);
        }
      }      

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void ulozUdalost(Date start, Date end, Uzivatel uzivatel, Kalendar kalendar, String pomer, String cinnost) {
    Cinnost cin = pripojeni.nacti(Cinnost.class, new Object[]{"nazev"}, new Object[]{cinnost}, uzivatel);
    if (cin == null) {
      int cinDelka = cinnost.length();
      String cinKod = cinnost.substring(0, Math.min(3, cinDelka));
      cin = pripojeni.nacti(Cinnost.class, new Object[]{"kod"}, new Object[]{cinKod}, uzivatel);
      int cislo = -1;
      while (cin != null) {
        cislo++;
        cin = pripojeni.nacti(Cinnost.class, new Object[]{"kod"}, new Object[]{(cinKod + cislo)}, uzivatel);
      }
      cin = new Cinnost();
      cin.setKod((cinKod + cislo));
      cin.setNazev(cinnost);
      cin.setUzivatel(uzivatel);
      pripojeni.vlozUprav(cin, cin.getId());
    }

    PracovniPomer pom = pripojeni.nacti(PracovniPomer.class, new Object[]{"nazev"}, new Object[]{pomer}, uzivatel);
    if (pom == null) {
      int pomDelka = pomer.length();
      String pomKod = pomer.substring(0, Math.min(3, pomDelka));
      pom = pripojeni.nacti(PracovniPomer.class, new Object[]{"kod"}, new Object[]{pomKod}, uzivatel);
      int cislo = -1;
      while (pom != null) {
        cislo++;
        pom = pripojeni.nacti(PracovniPomer.class, new Object[]{"kod"}, new Object[]{(pomKod + cislo)}, uzivatel);
      }
      pom = new PracovniPomer();
      pom.setKod((pomKod + cislo));
      pom.setNazev(pomer);
      pom.setUzivatel(uzivatel);
      pom.setTypUvazku("Pracovn� pom�r");
      pom.setVelikostUvazku(50);//TODO
      pripojeni.vlozUprav(pom, pom.getId());
    }

    KalendarCinnost kalendarCinnost = new KalendarCinnost();
    kalendarCinnost.setCasOd(start);
    kalendarCinnost.setCasDo(end);
    kalendarCinnost.setCinnost(cin);
    kalendarCinnost.setDatum(new Date());
    kalendarCinnost.setKalendar(kalendar);
    kalendarCinnost.setPracovniPomer(pom);
    kalendarCinnost.setUzivatel(uzivatel);
    kalendarCinnost.setPocetHodin((end.getTime() - start.getTime())/(1000*60*60));
    pripojeni.vlozUprav(kalendarCinnost, kalendarCinnost.getId());
  }
}
