package servlety.role.zamestnanec;

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

      for (Iterator<?> i = calendar.getComponents(Component.VEVENT).iterator(); i.hasNext(); ) {
        VEvent component = (VEvent) i.next();
        String description = component.getDescription().getValue();
        if (description.equals("")) description = component.getSummary().getValue();

        for (int j = 0; j < definice.size(); j++) {
          KalendarDefinice kd = definice.get(j);
          if (description.contains(kd.getTagKalendarCinnost()) && description.contains(kd.getTagPracovniPomer())) {
            Date start = component.getStartDate().getDate();        
            Date end = component.getEndDate().getDate();
            ulozUdalost(start, end, uzivatel, kd);
            break;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void ulozUdalost(Date start, Date end, Uzivatel uzivatel, KalendarDefinice kalendarDefinice) {
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
    //cinnost.setCinnost(cinnost);
    //cinnost.setPracovniPomer(pracovniPomer);
    cinnost.setUzivatel(uzivatel);
    cinnost.setKalendar(kalendar);
    cinnost.setCasOd(start);
    cinnost.setCasDo(end);
    //TODO ulozit
    
  }

}
