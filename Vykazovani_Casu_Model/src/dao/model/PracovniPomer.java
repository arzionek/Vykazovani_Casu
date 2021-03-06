package dao.model;

public class PracovniPomer extends AEntita implements java.io.Serializable {
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
  private String nazev;
	/**
   * @generated
   */
  private double velikostUvazku;
  
  private double mesicniFond;
  private double odpracovano;
  private String varovani;

	/**
   * @generated
   */
  private static final long serialVersionUID = 1289371412L;
	/**
   * @generated
   */
  private Long id;

	/**
   * @generated
   */
  private java.util.Set<KalendarCinnost> kalendarCinnost = new java.util.HashSet<KalendarCinnost>();

	/**
   * @generated
   */
  private String typUvazku;
  
  private static final String[] TYPY = {"Dohoda o proveden� pr�ce", "Dohoda o proveden� �innosti", "Hlavn� pracovn� pom�r", "Vedlej�� pracovn� pom�r"};

  /**
   * @generated
   */
  private java.util.Set<SablonaVykaz> sablonaVykaz = new java.util.HashSet<SablonaVykaz>();

  /**
   * @generated
   */
	public String toString() {
    return "PracovniPomer" + " kod=" + kod + " nazev=" + nazev
        + " velikostUvazku=" + velikostUvazku + " id=" + id + " typUvazku="
        + typUvazku;
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
	public double getVelikostUvazku() {
    return this.velikostUvazku;
  }

	/**
   * @generated
   */
	public void setVelikostUvazku(double velikostUvazku) {
    this.velikostUvazku = velikostUvazku;
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
	public PracovniPomer() {
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
	public void setKalendarCinnost(
			java.util.Set<KalendarCinnost> kalendarCinnost) {
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
  public String getTypUvazku() {
    return this.typUvazku;
  }

  /**
   * @generated
   */
  public void setTypUvazku(String typUvazku) {
    this.typUvazku = typUvazku;
  }

  public static String[] getTypy() {
    return TYPY;
  }

  /**
   * @generated
   */
  public java.util.Set<SablonaVykaz> getSablonaVykaz() {
    return sablonaVykaz;
  }

  /**
   * @generated
   */
  public void setSablonaVykaz(java.util.Set<SablonaVykaz> sablonaVykaz) {
    this.sablonaVykaz = sablonaVykaz;
  }

  /**
   * @generated
   */
  public void addSablonaVykaz(SablonaVykaz sablonaVykaz) {
    getSablonaVykaz().add(sablonaVykaz);
  }

  /**
   * @generated
   */
  public void removeSablonaVykaz(SablonaVykaz sablonaVykaz) {
    getSablonaVykaz().remove(sablonaVykaz);
  }

  public double getMesicniFond() {
    mesicniFond = Math.round(mesicniFond * 100.0) / 100.0;
    return mesicniFond;
  }

  public void setMesicniFond(double mesicniFond) {
    this.mesicniFond = mesicniFond;
  }

  public double getOdpracovano() {
    odpracovano = Math.round(odpracovano * 100.0) / 100.0;
    return odpracovano;
  }

  public void setOdpracovano(double odpracovano) {
    this.odpracovano = odpracovano;
  }

  public String getVarovani() {
    return varovani;
  }

  public void setVarovani(String varovani) {
    this.varovani = varovani;
  }

}