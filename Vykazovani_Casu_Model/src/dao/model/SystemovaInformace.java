package dao.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SystemovaInformace extends AEntita implements java.io.Serializable {
	/**
   * @generated
   */
  private java.util.Date datum;
  private String datum2;
  /**
   * @generated
   */
  private String nadpis;
  /**
   * @generated
   */
  private String data;
  /**
   * @generated
   */
  private static final long serialVersionUID = -605524016L;
  /**
   * @generated
   */
  private Long id;

  /**
   * @generated
   */
	public String toString() {
    return "SystemovaInformace" + " datum=" + datum + " nadpis=" + nadpis
        + " data=" + data + " id=" + id;
  }

	/**
   * @generated
   */
	public SystemovaInformace() {
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
  public String getNadpis() {
    return this.nadpis;
  }

  /**
   * @generated
   */
  public void setNadpis(String nadpis) {
    this.nadpis = nadpis;
  }

  /**
   * @generated
   */
  public String getData() {
    return this.data;
  }

  /**
   * @generated
   */
  public void setData(String data) {
    this.data = data;
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
      DateFormat df = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss"); 
      String datumCas[] = (df.format(datum)).split("_");
      
      String den = datumCas[0];
      if(den.length() == 1) den = "0" + den;
      String mesic = datumCas[1];
      if(mesic.length() == 1) mesic = "0" + mesic;
      String rok = datumCas[2];
      if(rok.length() == 1) rok = "0" + rok;
      datum2 = den + "." + mesic + "." + rok;
    }
    return datum2;
  }

  public void setDatum2(String datum2) {
    this.datum2 = datum2;
  }
}