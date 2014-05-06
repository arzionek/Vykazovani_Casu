package dao.model;

public class KalendarDefinice extends AEntita implements java.io.Serializable {
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
  private String tagPracovniPomer;
  /**
   * @generated
   */
  private String tagKalendarCinnost;
  /**
   * @generated
   */
  private java.util.Set<Kalendar> kalendar = new java.util.HashSet<Kalendar>();
  /**
   * @generated
   */
  private static final long serialVersionUID = -279607080L;
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
  private String tagPopis;

  /**
   * @generated
   */
	public KalendarDefinice() {
  }

	/**
   * @generated
   */
	public String toString() {
    return "KalendarDefinice" + " kod=" + kod + " nazev=" + nazev
        + " tagPracovniPomer=" + tagPracovniPomer + " tagKalendarCinnost="
        + tagKalendarCinnost + " id=" + id + " tagPopis=" + tagPopis;
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
  public String getTagPracovniPomer() {
    return this.tagPracovniPomer;
  }

  /**
   * @generated
   */
  public void setTagPracovniPomer(String tagPracovniPomer) {
    this.tagPracovniPomer = tagPracovniPomer;
  }

  /**
   * @generated
   */
  public String getTagKalendarCinnost() {
    return this.tagKalendarCinnost;
  }

  /**
   * @generated
   */
  public void setTagKalendarCinnost(String tagKalendarCinnost) {
    this.tagKalendarCinnost = tagKalendarCinnost;
  }

  /**
   * @generated
   */
  public java.util.Set<Kalendar> getKalendar() {
    return kalendar;
  }

  /**
   * @generated
   */
  public void setKalendar(java.util.Set<Kalendar> kalendar) {
    this.kalendar = kalendar;
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
  public void addKalendar(Kalendar kalendar) {
    getKalendar().add(kalendar);
  }

  /**
   * @generated
   */
  public void removeKalendar(Kalendar kalendar) {
    getKalendar().remove(kalendar);
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
  public String getTagPopis() {
    return this.tagPopis;
  }

  /**
   * @generated
   */
  public void setTagPopis(String tagPopis) {
    this.tagPopis = tagPopis;
  }
}