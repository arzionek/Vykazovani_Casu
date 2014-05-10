package servlety.nastroje;

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

import dao.beany.Cas;
import dao.databaze.Databaze;
import dao.model.ExportSablona;
import dao.model.KalendarCinnost;
import dao.model.PracovniPomer;
import dao.model.SablonaVykaz;
import dao.model.Svatek;

public class ExportDoSablony extends Prehled{

  private static final long serialVersionUID = -7136951769912776457L;

  public static void provestExport(HttpServletResponse response, ExportSablona export, Databaze pripojeni) {
    SablonaVykaz vykaz = pripojeni.nacti(SablonaVykaz.class, export.getSablonaVykaz().getId());
    pripojeni.inicializaceObjektu(vykaz);
    export.setSablonaVykaz(vykaz);
    
    PracovniPomer pomer = pripojeni.nacti(PracovniPomer.class, export.getPracovniPomer().getId());
    pomer.setUzivatel(export.getUzivatel());
    export.setPracovniPomer(pomer);
    
    String[] razeni = new String[]{"datum asc", "casOd asc"};
    if(vykaz.getTyp().equals(SablonaVykaz.PRACOVNI_VYKAZ)) razeni = new String[]{"cinnost.id asc", "datum asc", "casOd asc"};
    
    List<KalendarCinnost> cinnosti = pripojeni.ziskejCinnosti(new Cas(export.getDatumOd()).getDatumDatabaze(false), new Cas(export.getDatumDo()).getDatumDatabaze(true), pomer, razeni);
    for (int i = 0; cinnosti != null && i < cinnosti.size(); i++) {
      KalendarCinnost kc = cinnosti.get(i);
      pripojeni.inicializaceObjektu(kc.getCinnost());
    }
    export.setCinnosti(cinnosti);
    List<Svatek> svatky = pripojeni.ziskejObjekty(Svatek.class, pomer.getUzivatel(), "datum");
    
    Vysledek vysledek = null;
    if(vykaz.getTyp().equals(SablonaVykaz.EVIDENCE_DOCHAZKY)) vysledek = ExportEvidenceDochazky.exportEvidenceDochazky(export, svatky);
    else if(vykaz.getTyp().equals(SablonaVykaz.PRACOVNI_VYKAZ)) vysledek = ExportPracovnihoVykazu.exportPracovnihoVykazu(export, svatky);
    //else if(vykaz.getTyp().equals(SablonaVykaz.DOVOLENA)) vysledek = ExportDovolene.exportDovolene(export);
    
    Cas cas = new Cas();
    String datum = cas.getRok() + "_" + cas.getMesic() + "_" + cas.getDen();
    Download.download(response, vysledek.data, datum + "_" + vysledek.nazevSouboru + ".xls");
  }

  protected static class Vysledek{
    byte[] data;
    String nazevSouboru;
  }

  protected static void vycistiRadku(HSSFSheet sheet, int rowCount) {
    for (int i = 0; i <= 16; i++) nastavBunku(sheet, rowCount, i, "");
  }

  protected static void nastavBunku2(HSSFSheet sheet, int rowCount, int cellCount, String formula) {
    Cell cell = sheet.getRow(rowCount).getCell(cellCount);
    cell.setCellFormula(formula);
  }

  protected static void nastavBunku(HSSFSheet sheet, int rowCount, int cellCount, Object hodnota) {
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

  protected static String getCellName(Cell cell) {
    CellReference cr = new CellReference(cell.getRowIndex(), cell.getColumnIndex());
    String[] parts = cr.getCellRefParts();
    return parts[2] + "" + parts[1];
  }

  protected static byte[] getData(HSSFWorkbook workbook) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    workbook.write(baos);
    baos.close();
    return baos.toByteArray();
  }
  
  protected static boolean jeNemoc(KalendarCinnost cin) {
    String cinnost = cin.getCinnost().getNazev().toLowerCase();
    if(cinnost.contains("nemocenská") || cinnost.contains("neschopnost")) return true;
    else return false;
  }

  protected static boolean jeSluzebniCesta(KalendarCinnost cin) {
    String cinnost = cin.getCinnost().getNazev().toLowerCase();
    if(cinnost.contains("služební cesta") || cinnost.contains("pracovní cesta")) return true;
    else return false;
  }

  protected static boolean jeDovolena(KalendarCinnost cin) {
    String cinnost = cin.getCinnost().getNazev().toLowerCase();
    if(cinnost.contains("dovolená")) return true;
    else return false;
  }

  protected static boolean jeVikend(Cas cas) {
    if(cas.getDenTydenZkratka().equals("So")) return true;
    else if(cas.getDenTydenZkratka().equals("Ne")) return true;
    else return false;
  }

}
