package dao.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExportSablona extends AEntita {

  private static final long serialVersionUID = 3159839262963637723L;
  private Uzivatel uzivatel;
  private PracovniPomer pracovniPomer;
  private SablonaVykaz sablonaVykaz;
  private Date datumOd;
  private Date datumDo;
  private String datumOd2;
  private String datumDo2;
  private List<KalendarCinnost> cinnosti;
  
  public ExportSablona(){
    this.pracovniPomer = new PracovniPomer();
    this.sablonaVykaz = new SablonaVykaz();
  }
  
  public PracovniPomer getPracovniPomer() {
    return pracovniPomer;
  }
  
  public void setPracovniPomer(PracovniPomer pracovniPomer) {
    this.pracovniPomer = pracovniPomer;
  }

  public SablonaVykaz getSablonaVykaz() {
    return sablonaVykaz;
  }

  public void setSablonaVykaz(SablonaVykaz sablonaVykaz) {
    this.sablonaVykaz = sablonaVykaz;
  }

  public Date getDatumOd() {
    return datumOd;
  }

  public void setDatumOd(Date datumOd) {
    this.datumOd = datumOd;
  }

  public Date getDatumDo() {
    return datumDo;
  }

  public void setDatumDo(Date datumDo) {
    this.datumDo = datumDo;
  }

  public String getDatumOd2() {
    if(datumOd != null){
      DateFormat df = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss"); 
      String datumCas[] = (df.format(datumOd)).split("_");
      
      String den = datumCas[0];
      if(den.length() == 1) den = "0" + den;
      String mesic = datumCas[1];
      if(mesic.length() == 1) mesic = "0" + mesic;
      String rok = datumCas[2];
      if(rok.length() == 1) rok = "0" + rok;
      datumOd2 = den + "." + mesic + "." + rok;
    }
    return datumOd2;
  }

  public void setDatumOd2(String datumOd2) {
    this.datumOd2 = datumOd2;
  }

  public String getDatumDo2() {
    if(datumDo != null){
      DateFormat df = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss"); 
      String datumCas[] = (df.format(datumDo)).split("_");
      
      String den = datumCas[0];
      if(den.length() == 1) den = "0" + den;
      String mesic = datumCas[1];
      if(mesic.length() == 1) mesic = "0" + mesic;
      String rok = datumCas[2];
      if(rok.length() == 1) rok = "0" + rok;
      datumDo2 = den + "." + mesic + "." + rok;
    }
    return datumDo2;
  }

  public void setDatumDo2(String datumDo2) {
    this.datumDo2 = datumDo2;
  }

  public Uzivatel getUzivatel() {
    return uzivatel;
  }

  public void setUzivatel(Uzivatel uzivatel) {
    this.uzivatel = uzivatel;
  }

  public List<KalendarCinnost> getCinnosti() {
    return cinnosti;
  }

  public void setCinnosti(List<KalendarCinnost> cinnosti) {
    this.cinnosti = cinnosti;
  }

}
