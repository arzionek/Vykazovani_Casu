package com.xyz.myproject.model;


/**
 * @generated
 */
public class KalendarCinnost implements java.io.Serializable {
	/**
   * @generated
   */
  private Uzivatel uzivatel;
	/**
   * @generated
   */
  private java.util.Date datum;
	/**
   * @generated
   */
  private double pocetHodin;
	/**
   * @generated
   */
  private Cinnost cinnost;
	/**
   * @generated
   */
  private static final long serialVersionUID = -2114231508L;
	/**
   * @generated
   */
  private Long id;

	/**
   * @generated
   */
  private PracovniPomer pracovniPomer;

	/**
   * @generated
   */
  private Kalendar kalendar;

	/**
   * @generated
   */
	public String toString() {
    return "KalendarCinnost" + " datum=" + datum + " pocetHodin=" + pocetHodin
        + " id=" + id;
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
	public double getPocetHodin() {
    return this.pocetHodin;
  }

	/**
	 * @generated
	 */
	public void setPocetHodin(double pocetHodin) {
		this.pocetHodin = pocetHodin;
	}

	/**
   * @generated
   */
	public Cinnost getCinnost() {
    return this.cinnost;
  }

	/**
	 * @generated
	 */
	public void setCinnost(Cinnost cinnost) {
		this.cinnost = cinnost;
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
	public KalendarCinnost() {
  }

	/**
   * @generated
   */
	public PracovniPomer getPracovniPomer() {
    return this.pracovniPomer;
  }

	/**
	 * @generated
	 */
	public void setPracovniPomer(PracovniPomer pracovniPomer) {
		this.pracovniPomer = pracovniPomer;
	}

	/**
   * @generated
   */
	public Kalendar getKalendar() {
    return this.kalendar;
  }

	/**
	 * @generated
	 */
	public void setKalendar(Kalendar kalendar) {
		this.kalendar = kalendar;
	}
}