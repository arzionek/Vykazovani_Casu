package dao.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Svatek extends AEntita implements java.io.Serializable {
	/**
   * @generated
   */
  private java.util.Date datum;
  private String datum2;
  
  /**
   * @generated
   */
  private String nazev;
  /**
   * @generated
   */
  private Uzivatel uzivatel;
  /**
   * @generated
   */
  private String kod;
  /**
   * @generated
   */
  private static final long serialVersionUID = -1644369827L;
  /**
   * @generated
   */
  private Long id;

  /**
   * @generated
   */
	public Svatek() {
  }

	/**
   * @generated
   */
	public String toString() {
    return "Svatek" + " datum=" + datum + " nazev=" + nazev + " kod=" + kod
        + " id=" + id;
  }

  /**
   * @generated
   */
  public java.util.Date getDatum() {
    return this.datum;
  }

  /**
   * @generated
   */
  public void setDatum(java.util.Date datum) {
    this.datum = datum;
  }

  /**
   * @generated
   */
  public String getNazev() {
    return this.nazev;
  }

  /**
   * @generated
   */
  public void setNazev(String nazev) {
    this.nazev = nazev;
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
  public String getKod() {
    return this.kod;
  }

  /**
   * @generated
   */
  public void setKod(String kod) {
    this.kod = kod;
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

  public String getDatum2() {
    if(datum != null){
      DateFormat df = new SimpleDateFormat("dd_M_yyyy_HH_mm_ss"); 
      String datumCas[] = (df.format(datum)).split("_");
      
      String den = datumCas[0];
      if(den.length() == 1) den = "0" + den;
      String mesic = datumCas[1];
      if(mesic.length() == 1) mesic = "0" + mesic;
      datum2 = den + "." + mesic + ".";
    }
    return datum2;
  }

  public void setDatum2(String datum2) {
    this.datum2 = datum2;
  }
}