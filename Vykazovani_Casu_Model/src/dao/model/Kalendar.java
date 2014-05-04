package dao.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Kalendar extends AEntita implements java.io.Serializable {
	/**
   * @generated
   */
  private java.util.Set<KalendarCinnost> kalendarCinnost = new java.util.HashSet<KalendarCinnost>();

	/**
   * @generated
   */
  private java.util.Date datumImportu;
  private String datumImportu2;
  /**
   * @generated
   */
  private KalendarDefinice kalendarDefinice;
  /**
   * @generated
   */
  private static final long serialVersionUID = -1905395369L;
  /**
   * @generated
   */
  private Long id;

  /**
   * @generated
   */
  private Uzivatel uzivatel;

  /**
   * @generated
   */
  private byte[] data;

  /**
   * @generated
   */
	public Kalendar() {
  }

	/**
   * @generated
   */
	public String toString() {
    return "Kalendar" + " datumImportu=" + datumImportu + " id=" + id
        + " data=" + java.util.Arrays.toString(data);
  }

  /**
   * @generated
   */
  public java.util.Set<KalendarCinnost> getKalendarCinnost() {
    return kalendarCinnost;
  }

  /**
   * @generated
   */
  public void setKalendarCinnost(java.util.Set<KalendarCinnost> kalendarCinnost) {
    this.kalendarCinnost = kalendarCinnost;
  }

  /**
   * @generated
   */
  public void addKalendarCinnost(KalendarCinnost kalendarCinnost) {
    getKalendarCinnost().add(kalendarCinnost);
  }

  /**
   * @generated
   */
  public void removeKalendarCinnost(KalendarCinnost kalendarCinnost) {
    getKalendarCinnost().remove(kalendarCinnost);
  }

  /**
   * @generated
   */
  public java.util.Date getDatumImportu() {
    return this.datumImportu;
  }

  /**
   * @generated
   */
  public void setDatumImportu(java.util.Date datumImportu) {
    this.datumImportu = datumImportu;
  }
  
  public String getDatumImportu2() {
    if(datumImportu2 != null){
      DateFormat df = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss"); 
      String datumCas[] = (df.format(datumImportu2)).split("_");
      
      String den = datumCas[0];
      if(den.length() == 1) den = "0" + den;
      String mesic = datumCas[1];
      if(mesic.length() == 1) mesic = "0" + mesic;
      String rok = datumCas[2];
      if(rok.length() == 1) rok = "0" + rok;
      String hodina = datumCas[3];
      if(hodina.length() == 1) hodina = "0" + hodina;
      String minuta = datumCas[4];
      if(minuta.length() == 1) minuta = "0" + minuta;
      datumImportu2 = den + "." + mesic + "." + rok + " " + hodina + ":" + minuta;
    }
    return datumImportu2;
  }

  public void setDatumImportu2(String datumImportu2) {
    this.datumImportu2 = datumImportu2;
  }

  /**
   * @generated
   */
  public KalendarDefinice getKalendarDefinice() {
    return this.kalendarDefinice;
  }

  /**
   * @generated
   */
  public void setKalendarDefinice(KalendarDefinice kalendarDefinice) {
    this.kalendarDefinice = kalendarDefinice;
  }

  /**
   * @generated
   */
  public Long getId() {
    return this.id;
  }

  /**
   * @generated
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @generated
   */
  public Uzivatel getUzivatel() {
    return this.uzivatel;
  }

  /**
   * @generated
   */
  public void setUzivatel(Uzivatel uzivatel) {
    this.uzivatel = uzivatel;
  }

  /**
   * @generated
   */
  public byte[] getData() {
    return data;
  }

  /**
   * @generated
   */
  public void setData(byte[] data) {
    this.data = data;
  }
}