package com.xyz.myproject.model;


/**
 * @generated
 */
public class Kalendar implements java.io.Serializable {
	/**
   * @generated
   */
  private java.util.Set<KalendarCinnost> kalendarCinnost = new java.util.HashSet<KalendarCinnost>();

	/**
   * @generated
   */
  private java.util.Date datumImportu;
  /**
   * @generated
   */
  private KalendarDefinice kalendarDefinice;
  /**
   * @generated
   */
  private static final long serialVersionUID = -710732898L;
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
	public Kalendar() {
  }

	/**
   * @generated
   */
	public String toString() {
    return "Kalendar" + " datumImportu=" + datumImportu + " id=" + id;
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
}