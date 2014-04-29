package dao.model;

public class NastaveniSystemu extends AEntita implements java.io.Serializable {
  /**
   * @generated
   */
  private String nazev;
  /**
   * @generated
   */
  private String hodnota;
  private String datum;
  /**
   * @generated
   */
  private Uzivatel uzivatel;

  /**
   * @generated
   */
  private static final long serialVersionUID = -1153222512L;
  /**
   * @generated
   */
  private Long id;

  /**
   * @generated
   */
  public NastaveniSystemu() {
  }

  /**
   * @generated
   */
  public String toString() {
    return "NastaveniSystemu" + " nazev=" + nazev + " hodnota=" + hodnota
        + " id=" + id;
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
  public String getHodnota() {
    return this.hodnota;
  }

  /**
   * @generated
   */
  public void setHodnota(String hodnota) {
    this.hodnota = hodnota;
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
  public Long getId() {
    return this.id;
  }

  /**
   * @generated
   */
  public void setId(Long id) {
    this.id = id;
  }

  public String getDatum() {
    String rok = hodnota.substring(0, hodnota.indexOf('-'));
    hodnota = hodnota.substring(hodnota.indexOf('-') + 1);
    String mesic = hodnota.substring(0, hodnota.indexOf('-'));
    if(mesic.length() == 1) mesic = "0" + mesic;
    hodnota = hodnota.substring(hodnota.indexOf('-') + 1);
    String den = hodnota.substring(0, hodnota.indexOf(' '));
    if(den.length() == 1) den = "0" + den;
    datum = den + "." + mesic + "." + rok;
    return datum;
  }

  public void setDatum(String datum) {
    this.datum = datum;
  }
}