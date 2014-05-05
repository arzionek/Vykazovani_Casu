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
        upload.setHeaderEncoding("UTF-8"); 
        try {
          FileItem fileItem = null;
          long kalendarDefiniceId = 0;
          KalendarDefinice kalendarDefinice = null;
          @SuppressWarnings("unchecked")
          List<FileItem> fields = upload.parseRequest(request);
          for (int i = 0; i < fields.size(); i++) {
            FileItem fi = fields.get(i); 
            if (!fi.isFormField()) fileItem = fi;
            else if(fi.getFieldName().equals("definice")){
              try {
                kalendarDefiniceId = Long.parseLong(fi.getString());
              } catch (Exception e) {
                //
              }
            }
          }
          File file = new File("cal.ics");
          fileItem.write(file);
          FileInputStream stream = new FileInputStream(file);

          kontrolaChybnySoubor(request, stream, "soubor");
          kalendarDefinice = pripojeni.nacti(KalendarDefinice.class, new Object[]{"id"}, new Object[]{kalendarDefiniceId}, uzivatel);
          kontrolaNenulovostiObjektu(request, kalendarDefinice, "definice");
          
          if (overChyby(request) == null) {
            stream = new FileInputStream(file);
            kalendar.setData(fileItem.get());
            kalendar.setDatumImportu(new Date());
            kalendar.setUzivatel(uzivatel);
            kalendar.setKalendarDefinice(kalendarDefinice);     
            pripojeni.vlozUprav(kalendar, kalendar.getId());

            if (novyKalendar(stream, uzivatel, kalendarDefinice, kalendar, request) == 0) {
              pripojeni.smaz(kalendar);
              kontrolaRedundance(request, true, "soubor");
            }

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

  private int novyKalendar(FileInputStream stream, Uzivatel uzivatel, KalendarDefinice kd, Kalendar kalendar, HttpServletRequest request) {
    int citac = 0;
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
          String uid = component.getUid().getValue();
          if (pripojeni.nacti(KalendarCinnost.class, new Object[]{"googleId"}, new Object[]{uid}, uzivatel) != null) {
            continue;
          }
          else {
            citac++;
            ulozUdalost(start, end, uzivatel, kalendar, pomer, cinnost, uid);
          }
        }
      }      

    } catch (Exception e) {
      e.printStackTrace();
    }
    return citac;
  }

  private void ulozUdalost(Date start, Date end, Uzivatel uzivatel, Kalendar kalendar, String pomer, String cinnost, String uid) {
    Cinnost cin = pripojeni.nacti(Cinnost.class, new Object[]{"nazev"}, new Object[]{cinnost}, uzivatel);
    if (cin == null) {
      int cinDelka = cinnost.length();
      String cinKod = cinnost.replace(" ", "").substring(0, Math.min(3, cinDelka));
      String cinKod2 = cinKod;
      cin = pripojeni.nacti(Cinnost.class, new Object[]{"kod"}, new Object[]{cinKod}, uzivatel);
      int cislo = -1;
      while (cin != null) {
        cislo++;
        cin = pripojeni.nacti(Cinnost.class, new Object[]{"kod"}, new Object[]{cinKod.concat(String.valueOf(cislo))}, uzivatel);
        cinKod2 = cinKod.concat(String.valueOf(cislo));
      }
      cin = new Cinnost();
      cin.setKod((cinKod2));
      cin.setNazev(cinnost);
      cin.setUzivatel(uzivatel);
      pripojeni.vlozUprav(cin, cin.getId());
    }

    PracovniPomer pom = pripojeni.nacti(PracovniPomer.class, new Object[]{"nazev"}, new Object[]{pomer}, uzivatel);
    if (pom == null) {
      int pomDelka = pomer.length();
      String pomKod = pomer.replace(" ", "").substring(0, Math.min(3, pomDelka));
      String pomKod2 = pomKod;
      pom = pripojeni.nacti(PracovniPomer.class, new Object[]{"kod"}, new Object[]{pomKod}, uzivatel);
      int cislo = -1;
      while (pom != null) {
        cislo++;
        pom = pripojeni.nacti(PracovniPomer.class, new Object[]{"kod"}, new Object[]{pomKod.concat(String.valueOf(cislo))}, uzivatel);
        pomKod2 = pomKod.concat(String.valueOf(cislo));
      }
      pom = new PracovniPomer();
      pom.setKod(pomKod2);
      pom.setNazev(pomer);
      pom.setUzivatel(uzivatel);
      pom.setTypUvazku("Pracovní pomìr");
      pom.setVelikostUvazku(1);
      pripojeni.vlozUprav(pom, pom.getId());
    }

    KalendarCinnost kalendarCinnost = new KalendarCinnost();
    kalendarCinnost.setCasOd(start);
    kalendarCinnost.setCasDo(end);
    kalendarCinnost.setCinnost(cin);
    kalendarCinnost.setGoogleId(uid);
    
    Date datum = (Date) start.clone();
    datum.setHours(0);
    datum.setMinutes(0);
    datum.setSeconds(0);
    
    kalendarCinnost.setDatum(datum);
    kalendarCinnost.setKalendar(kalendar);
    kalendarCinnost.setPracovniPomer(pom);
    kalendarCinnost.setUzivatel(uzivatel);
    kalendarCinnost.setPocetHodin((end.getTime() - start.getTime())/(1000*60*60));
    pripojeni.vlozUprav(kalendarCinnost, kalendarCinnost.getId());
  }
}
