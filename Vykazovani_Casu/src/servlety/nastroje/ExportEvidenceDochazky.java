package servlety.nastroje;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;

import servlety.role.zamestnanec.Vytvoreni;
import dao.beany.Cas;
import dao.model.ExportSablona;
import dao.model.KalendarCinnost;
import dao.model.PracovniPomer;
import dao.model.Svatek;
import dao.model.Uzivatel;

public class ExportEvidenceDochazky extends ExportDoSablony {
  
  private static final long serialVersionUID = -4756236081184968312L;

  public static Vysledek exportEvidenceDochazky(ExportSablona export, List<Svatek> svatky) throws Exception{
    Vysledek vysledek = new Vysledek();
    vysledek.nazevSouboru = "evidence_dochazky";
    try {
      ByteArrayInputStream bais = new ByteArrayInputStream(export.getSablonaVykaz().getData());
      
      HSSFWorkbook workbook = new HSSFWorkbook(bais);
      HSSFSheet sheet = workbook.getSheet("Docházka");
      if(sheet == null) workbook.getSheetAt(0);

      evidenceDochazkyHlavicka(sheet, export.getUzivatel());
      
      int rowCount = 3;
      int cisloCinnosti = 0;
      Cas konecCas = new Cas(export.getDatumOd());
      konecCas.setDen(31);
      List<KalendarCinnost> cinnosti = export.getCinnosti();
      for (int i = 1; i <= konecCas.getDen(); i++) {
        vycistiRadku(sheet, rowCount);
        Cas cas = new Cas(export.getDatumOd());
        cas.setDen(i);
        cas.nastavDen(i, cas.getMesic(), cas.getRok());
        
        nastavBunku(sheet, rowCount, 0, cas.getDatumDate());
        if(!jeVikend(cas) && !jeSvatek(cas, svatky)){
          nastavBunku(sheet, rowCount, 1, cas.getDatumDate());
          if(cisloCinnosti < cinnosti.size()){
            KalendarCinnost cin = cinnosti.get(cisloCinnosti);
            if(!jeDovolena(cin) && !jeSluzebniCesta(cin) && !jeNemoc(cin) && cas.getDatumDate().equals(cin.getDatum())){
              nastavBunku2(sheet, rowCount, 2, getTyp(cas));
              
              int cellCount = 3;
              while(cas.getDatumDate().equals(cin.getDatum())){
                if(cellCount < 15){
                  if(cellCount == 6){
                    KalendarCinnost cin0 = cinnosti.get(cisloCinnosti - 1);
                    nastavBunku(sheet, rowCount, cellCount++, cin0.getCasDo());
                    nastavBunku(sheet, rowCount, cellCount++, cin.getCasOd());
                    nastavBunku(sheet, rowCount, cellCount++, Vytvoreni.vratPocetHodin(cin0.getCasDo(), cin.getCasOd()));
                  }
                  nastavBunku(sheet, rowCount, cellCount++, cin.getCasOd());
                  nastavBunku(sheet, rowCount, cellCount++, cin.getCasDo());
                  nastavBunku(sheet, rowCount, cellCount++, cin.getPocetHodin());
                }
                cisloCinnosti++;
                if(cisloCinnosti == cinnosti.size()) break;
                cin = cinnosti.get(cisloCinnosti);
              }
            }else if(cas.getDatumDate().equals(cin.getDatum())) {
              nastavBunku(sheet, "Q" + (rowCount + 1), cin.getCinnost().getNazev());
              while(cas.getDatumDate().equals(cin.getDatum())){
                cisloCinnosti++;
                if(cisloCinnosti == cinnosti.size()) break;
                cin = cinnosti.get(cisloCinnosti);
              }
            }
          }
        }else if(cisloCinnosti < cinnosti.size()){
          KalendarCinnost cin = cinnosti.get(cisloCinnosti);
          while(cas.getDatumDate().equals(cin.getDatum())){
            cisloCinnosti++;
            if(cisloCinnosti == cinnosti.size()) break;
            cin = cinnosti.get(cisloCinnosti);
          }
        }
        rowCount++;
      }
      
      evidenceDochazkyPaticka(sheet, export.getPracovniPomer());
      
      bais.close();
      vysledek.data = getData(workbook);
    }catch (IOException e) {
      e.printStackTrace();
    }
    return vysledek;
  }

  private static void nastavBunku(HSSFSheet sheet, String oznaceni, Object hodnota) throws Exception{
    CellReference cr = new CellReference(oznaceni);
    nastavBunku(sheet, cr.getRow(), cr.getCol(), hodnota);
  }

  private static void evidenceDochazkyHlavicka(HSSFSheet sheet, Uzivatel uzivatel) throws Exception{
    nastavBunku(sheet, 0, 5, uzivatel.getCeleJmeno());
    nastavBunku(sheet, 35, 8, uzivatel.getCeleJmeno());
  }
  
  private static void evidenceDochazkyPaticka(HSSFSheet sheet, PracovniPomer pracovniPomer) throws Exception{
    nastavBunku2(sheet, 35, 5, "SUM(F4:F34)+SUM(L4:L34)+SUM(O4:O34)");
    nastavBunku2(sheet, 36, 5, "F36+F38");
    nastavBunku2(sheet, 37, 5, "SUM(I4:I34)");
    nastavBunku2(sheet, 38, 5, "8*COUNT(C4:C34)*" + pracovniPomer.getVelikostUvazku());
  }
  
  private static String getTyp(Cas cas) {
    String typ = null;
    if(cas.getDenTydenZkratka().equals("Po")) typ = "T3";
    else if(cas.getDenTydenZkratka().equals("Út")) typ = "U3";
    else if(cas.getDenTydenZkratka().equals("St")) typ = "V3";
    else if(cas.getDenTydenZkratka().equals("Èt")) typ = "W3";
    else if(cas.getDenTydenZkratka().equals("Pá")) typ = "X3";
    return typ;
  }
}
