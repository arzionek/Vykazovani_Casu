package servlety.nastroje;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.VEvent;
import dao.model.Kalendar;
import dao.model.KalendarCinnost;
import dao.model.KalendarDefinice;
import dao.model.Uzivatel;

public class ImportKalendare {

  public ImportKalendare() {

  }

  public void novyKalendar(File kalendar, Uzivatel uzivatel) {
    try {
      List<KalendarDefinice> definice = new ArrayList<KalendarDefinice>();  //TODO nacist data

      FileInputStream fis = new FileInputStream(kalendar);
      CalendarBuilder builder = new CalendarBuilder();
      Calendar calendar = builder.build(fis);
      
      for (int j = 0; j < definice.size(); j++) {
        KalendarDefinice kd = definice.get(j); 
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
            String pomer = text.substring(text.lastIndexOf(pomStart) + 1, text.indexOf(pomKonec));
            String cinnost = text.substring(text.lastIndexOf(cinStart) + 1, text.indexOf(cinKonec));
            Date start = component.getStartDate().getDate();
            Date end = component.getEndDate().getDate();
            ulozUdalost(start, end, uzivatel, kd, pomer, cinnost);
            break;
          }
        }      
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void ulozUdalost(Date start, Date end, Uzivatel uzivatel, KalendarDefinice kalendarDefinice, String pomer, String cinnost2) {
    Kalendar kalendar = new Kalendar();
    Date ted = new Date();
    kalendar.setDatumImportu(ted);
    kalendar.setKalendarDefinice(kalendarDefinice);
    kalendar.setUzivatel(uzivatel);
    //TODO ulozit

    KalendarCinnost cinnost = new KalendarCinnost();
    cinnost.setDatum(ted);
    double cas = end.getTime() - start.getTime();
    cas /= (1000.0 * 60 * 60);
    cinnost.setPocetHodin(cas);
    //cinnost.setCinnost(cinnost);  //TODO najit cinnost (vytvorit ji)
    //cinnost.setPracovniPomer(pracovniPomer);  //TODO najit pomer (vytvorit ho)
    cinnost.setUzivatel(uzivatel);
    cinnost.setKalendar(kalendar);
    cinnost.setCasOd(start);
    cinnost.setCasDo(end);
    //TODO ulozit

  }

}
