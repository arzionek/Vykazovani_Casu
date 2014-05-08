package servlety.nastroje;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import dao.beany.Cas;
import dao.model.ExportSablona;
import dao.model.KalendarCinnost;

public class ExportPracovnihoVykazu extends ExportDoSablony {

  private static final long serialVersionUID = -81168711497304198L;
  
  public static Vysledek exportPracovnihoVykazu(ExportSablona export) {
    Vysledek vysledek = new Vysledek();
    vysledek.nazevSouboru = "pracovni_vykaz";
    try {
      ByteArrayInputStream bais = new ByteArrayInputStream(export.getSablonaVykaz().getData());
      
      HSSFWorkbook workbook = new HSSFWorkbook(bais);
      HSSFSheet sheet = workbook.getSheetAt(0);

      nastavBunku(sheet, 9, 2, export.getUzivatel().getCeleJmeno());
      //nastavBunku(sheet, 9, 6, export.getPracovniPomer().getTypUvazku());
      nastavBunku(sheet, 10, 6, export.getPracovniPomer().getVelikostUvazku());
      Cas cas = new Cas(export.getDatumOd());
      cas.setDen(1);
      cas.nastavDen(1, cas.getMesic(), cas.getRok());
      nastavBunku(sheet, 11, 6, cas.getMesic() + "/" + cas.getRok());
      
      List<KalendarCinnost> cinnosti = export.getCinnosti();
      int rowCount = 23;
      double pocetHodin = 0;
      String popisy = "";
      String terminyDovolene = "";
      int pocetDniDovolene = 0;
      String terminyPracovniNeschopnosti = "";
      int pocetDniPracovniNeschnopnosti = 0;
      for (int i = 0; i < cinnosti.size(); i++) {
        KalendarCinnost cin = null;
        cin = cinnosti.get(i);
        pocetHodin += cin.getPocetHodin();
        if(jeDovolena(cin)){
          pocetDniDovolene++;
          terminyDovolene += cin.getDatum2() + " ";
        }else if(jeNemoc(cin)){
          pocetDniPracovniNeschnopnosti++;
          terminyPracovniNeschopnosti += cin.getDatum2() + " ";
        }else{
          if(cin.getPopis().trim().length() > 0) popisy += cin.getPopis() + ", ";
        }
        KalendarCinnost cin2 = null;
        if(i < (cinnosti.size() - 1)) cin2 = cinnosti.get(i + 1);
        if(cin2 == null || cin.getCinnost().getId() != cin2.getCinnost().getId()){
          if(jeDovolena(cin)){
            nastavBunku(sheet, 40, 2, terminyDovolene);
            nastavBunku(sheet, 41, 2, "" + pocetDniDovolene);
            nastavBunku(sheet, 42, 2, pocetHodin);
          }else if(jeNemoc(cin)){
            nastavBunku(sheet, 40, 6, terminyPracovniNeschopnosti);
            nastavBunku(sheet, 41, 6, pocetDniPracovniNeschnopnosti);
            nastavBunku(sheet, 42, 6, pocetHodin);
          }else{
            nastavBunku(sheet, rowCount, 0, pocetHodin);
            nastavBunku(sheet, rowCount, 1, cin.getCinnost().getNazev());
            int index = popisy.lastIndexOf(',');
            if(index != -1)popisy = popisy.substring(0, index);
            nastavBunku(sheet, rowCount, 3, popisy);
            rowCount++;
            popisy = "";
          }
          pocetHodin = 0;
        }
      }
      nastavBunku2(sheet, 37, 1, "SUM(A24:A36)");
      nastavBunku2(sheet, 44, 3, "B38+C43+G43");
      nastavBunku(sheet, 51, 4, export.getUzivatel().getCeleJmeno());

      bais.close();

      vysledek.data = getData(workbook);
    }catch (IOException e) {
      e.printStackTrace();
    }
    return vysledek;
  }

}
