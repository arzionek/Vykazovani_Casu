package com.xyz.myproject.model;


/**
 * @generated
 */
public class Svatek implements java.io.Serializable {
	/**
   * @generated
   */
  private java.util.Date datum;
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
  private static final long serialVersionUID = 779216996L;
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
}