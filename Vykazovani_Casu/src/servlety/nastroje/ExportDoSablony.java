package servlety.nastroje;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import dao.beany.Cas;
import dao.databaze.Databaze;
import dao.model.ExportSablona;
import dao.model.KalendarCinnost;
import dao.model.PracovniPomer;
import dao.model.SablonaVykaz;

public class ExportDoSablony {

  public static void provestExport(HttpServletResponse response, ExportSablona export, Databaze pripojeni) {
    SablonaVykaz vykaz = pripojeni.nacti(SablonaVykaz.class, export.getSablonaVykaz().getId());
    pripojeni.inicializaceObjektu(vykaz);
    
    PracovniPomer pomer = pripojeni.nacti(PracovniPomer.class, export.getPracovniPomer().getId());
    pomer.setUzivatel(export.getUzivatel());
    
    List<KalendarCinnost> cinnosti = pripojeni.ziskejCinnosti(new Cas(export.getDatumOd()).getDatumDatabaze(false), new Cas(export.getDatumDo()).getDatumDatabaze(true), pomer);
    
    Vysledek vysledek = null;
    if(vykaz.getTyp().equals(SablonaVykaz.EVIDENCE_DOCHAZKY)) vysledek = exportEvidenceDochazky(vykaz, pomer, cinnosti);
    else if(vykaz.getTyp().equals(SablonaVykaz.PRACOVNI_VYKAZ)) vysledek = exportPracovnihoVykazu(vykaz, pomer, cinnosti);
    else if(vykaz.getTyp().equals(SablonaVykaz.DOVOLENA)) vysledek = exportDovolene(vykaz, pomer, cinnosti);
    
    Cas cas = new Cas();
    String datum = cas.getRok() + "_" + cas.getMesic() + "_" + cas.getDen();
    Download.download(response, vysledek.data, datum + "_" + vysledek.nazevSouboru + ".xls");
  }

  private static class Vysledek{
    byte[] data;
    String nazevSouboru;
  }
  
  private static Vysledek exportDovolene(SablonaVykaz vykaz, PracovniPomer pomer, List<KalendarCinnost> cinnosti) {
    Vysledek vysledek = new Vysledek();
    vysledek.nazevSouboru = "dovolena";
    byte[] data = vykaz.getData();
    // TODO Auto-generated method stub
    
    
    vysledek.data = data;
    return vysledek;
  }

  private static Vysledek exportPracovnihoVykazu(SablonaVykaz vykaz, PracovniPomer pomer, List<KalendarCinnost> cinnosti) {
    Vysledek vysledek = new Vysledek();
    vysledek.nazevSouboru = "pracovni_vykaz";
    byte[] data = vykaz.getData();
    // TODO Auto-generated method stub
    
    
    vysledek.data = data;
    return vysledek;
  }

  private static Vysledek exportEvidenceDochazky(SablonaVykaz vykaz, PracovniPomer pomer, List<KalendarCinnost> cinnosti) {
    Vysledek vysledek = new Vysledek();
    vysledek.nazevSouboru = "evidence_dochazky";
    byte[] data = vykaz.getData();
    // TODO Auto-generated method stub
    
    
    vysledek.data = data;
    return vysledek;
  }

}
