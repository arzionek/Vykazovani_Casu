package servlety.nastroje;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;

import servlety.role.zamestnanec.Prehled;
import servlety.role.zamestnanec.Vytvoreni;

import dao.beany.Cas;
import dao.databaze.Databaze;
import dao.model.ExportSablona;
import dao.model.KalendarCinnost;
import dao.model.PracovniPomer;
import dao.model.SablonaVykaz;
import dao.model.Svatek;
import dao.model.Uzivatel;

public class ExportDoSablony extends Prehled{

  private static final long serialVersionUID = -7136951769912776457L;

  public static void provestExport(HttpServletResponse response, ExportSablona export, Databaze pripojeni) {
    SablonaVykaz vykaz = pripojeni.nacti(SablonaVykaz.class, export.getSablonaVykaz().getId());
    pripojeni.inicializaceObjektu(vykaz);
    export.setSablonaVykaz(vykaz);
    
    PracovniPomer pomer = pripojeni.nacti(PracovniPomer.class, export.getPracovniPomer().getId());
    pomer.setUzivatel(export.getUzivatel());
    export.setPracovniPomer(pomer);
    
    List<KalendarCinnost> cinnosti = pripojeni.ziskejCinnosti(new Cas(export.getDatumOd()).getDatumDatabaze(false), new Cas(export.getDatumDo()).getDatumDatabaze(true), pomer, new String[]{"datum asc", "casOd asc"});
    for (int i = 0; cinnosti != null && i < cinnosti.size(); i++) {
      KalendarCinnost kc = cinnosti.get(i);
      pripojeni.inicializaceObjektu(kc.getCinnost());
    }
    export.setCinnosti(cinnosti);
    List<Svatek> svatky = pripojeni.ziskejObjekty(Svatek.class, pomer.getUzivatel(), "datum");
    
    Vysledek vysledek = null;
    if(vykaz.getTyp().equals(SablonaVykaz.EVIDENCE_DOCHAZKY)) vysledek = exportEvidenceDochazky(export, svatky);
    else if(vykaz.getTyp().equals(SablonaVykaz.PRACOVNI_VYKAZ)) vysledek = exportPracovnihoVykazu(export);
    else if(vykaz.getTyp().equals(SablonaVykaz.DOVOLENA)) vysledek = exportDovolene(export);
    
    Cas cas = new Cas();
    String datum = cas.getRok() + "_" + cas.getMesic() + "_" + cas.getDen();
    Download.download(response, vysledek.data, datum + "_" + vysledek.nazevSouboru + ".xls");
  }

  private static class Vysledek{
    byte[] data;
    String nazevSouboru;
  }
  
  private static Vysledek exportDovolene(ExportSablona export) {
    Vysledek vysledek = new Vysledek();
    vysledek.nazevSouboru = "dovolena";
    try {
      ByteArrayInputStream bais = new ByteArrayInputStream(export.getSablonaVykaz().getData());
      
      HSSFWorkbook workbook = new HSSFWorkbook(bais);
      HSSFSheet sheet = workbook.getSheetAt(0);

      //TODO

      bais.close();

      vysledek.data = getData(workbook);
    }catch (IOException e) {
      e.printStackTrace();
    }
    return vysledek;
  }

  private static Vysledek exportPracovnihoVykazu(ExportSablona export) {
    Vysledek vysledek = new Vysledek();
    vysledek.nazevSouboru = "pracovni_vykaz";
    try {
      ByteArrayInputStream bais = new ByteArrayInputStream(export.getSablonaVykaz().getData());
      
      HSSFWorkbook workbook = new HSSFWorkbook(bais);
      HSSFSheet sheet = workbook.getSheetAt(0);

      //TODO

      bais.close();

      vysledek.data = getData(workbook);
    }catch (IOException e) {
      e.printStackTrace();
    }
    return vysledek;
  }

  private static Vysledek exportEvidenceDochazky(ExportSablona export, List<Svatek> svatky) {
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
            if(!jeDovolena(cin) && !jeSluzebniCesta(cin) && !jeNemoc(cin)){
              nastavBunku2(sheet, rowCount, 2, getTyp(cas));
              
              int cellCount = 3;
              while(cas.getDatumDate().equals(cin.getDatum())){
                if(cellCount == 6){
                  KalendarCinnost cin0 = cinnosti.get(cisloCinnosti - 1);
                  nastavBunku(sheet, rowCount, cellCount++, cin0.getCasDo());
                  nastavBunku(sheet, rowCount, cellCount++, cin.getCasOd());
                  nastavBunku(sheet, rowCount, cellCount++, Vytvoreni.vratPocetHodin(cin0.getCasDo(), cin.getCasOd()));
                }
                nastavBunku(sheet, rowCount, cellCount++, cin.getCasOd());
                nastavBunku(sheet, rowCount, cellCount++, cin.getCasDo());
                nastavBunku(sheet, rowCount, cellCount++, cin.getPocetHodin());
                cisloCinnosti++;
                if(cisloCinnosti == cinnosti.size()) break;
                cin = cinnosti.get(cisloCinnosti);
              }
            }else{
              nastavBunku(sheet, rowCount, 16, cin.getCinnost().getNazev());
              while(cas.getDatumDate().equals(cin.getDatum())){
                cisloCinnosti++;
                if(cisloCinnosti == cinnosti.size()) break;
                cin = cinnosti.get(cisloCinnosti);
              }
            }
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

  private static void vycistiRadku(HSSFSheet sheet, int rowCount) {
    for (int i = 0; i <= 16; i++) nastavBunku(sheet, rowCount, i, "");
  }

  private static void nastavBunku2(HSSFSheet sheet, int rowCount, int cellCount, String formula) {
    Cell cell = sheet.getRow(rowCount).getCell(cellCount);
    cell.setCellFormula(formula);
  }

  private static void nastavBunku(HSSFSheet sheet, int rowCount, int cellCount, Object hodnota) {
    Cell cell = sheet.getRow(rowCount).getCell(cellCount);
    cell.setCellFormula(null);
    if(hodnota instanceof Date){
      Date hodnota2 = (Date) hodnota;
      cell.setCellValue(hodnota2);
    }else if(hodnota instanceof String){
      String hodnota2 = (String) hodnota;
      cell.setCellValue(hodnota2);
    }else if(hodnota instanceof Double){
      Double hodnota2 = (Double) hodnota;
      cell.setCellValue(hodnota2);
    }
  }

  private static String getCellName(Cell cell) {
    CellReference cr = new CellReference(cell.getRowIndex(), cell.getColumnIndex());
    String[] parts = cr.getCellRefParts();
    return parts[2] + "" + parts[1];
  }

  private static void evidenceDochazkyPaticka(HSSFSheet sheet, PracovniPomer pracovniPomer) {
    nastavBunku2(sheet, 35, 5, "SUM(F4:F34)+SUM(L4:L34)+SUM(O4:O34)");
    nastavBunku2(sheet, 36, 5, "F36+F38");
    nastavBunku2(sheet, 37, 5, "SUM(I4:I34)");
    nastavBunku2(sheet, 38, 5, "8*COUNT(C4:C34)*" + pracovniPomer.getVelikostUvazku());
  }

  private static boolean jeNemoc(KalendarCinnost cin) {
    String cinnost = cin.getCinnost().getNazev().toLowerCase();
    if(cinnost.contains("nemocenská") || cinnost.contains("neschopnost")) return true;
    else return false;
  }

  private static boolean jeSluzebniCesta(KalendarCinnost cin) {
    String cinnost = cin.getCinnost().getNazev().toLowerCase();
    if(cinnost.contains("služební cesta") || cinnost.contains("pracovní cesta")) return true;
    else return false;
  }

  private static boolean jeDovolena(KalendarCinnost cin) {
    String cinnost = cin.getCinnost().getNazev().toLowerCase();
    if(cinnost.contains("dovolená")) return true;
    else return false;
  }

  private static boolean jeVikend(Cas cas) {
    if(cas.getDenTydenZkratka().equals("So")) return true;
    else if(cas.getDenTydenZkratka().equals("Ne")) return true;
    else return false;
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

  private static void evidenceDochazkyHlavicka(HSSFSheet sheet, Uzivatel uzivatel) {
    nastavBunku(sheet, 0, 5, uzivatel.getCeleJmeno());
    nastavBunku(sheet, 35, 8, uzivatel.getCeleJmeno());
  }

  private static byte[] getData(HSSFWorkbook workbook) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    workbook.write(baos);
    baos.close();
    return baos.toByteArray();
  }

}
