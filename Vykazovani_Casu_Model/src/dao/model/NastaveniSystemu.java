package dao.model;


/**
 * @generated
 */
public class NastaveniSystemu implements java.io.Serializable {
  /**
   * @generated
   */
  private String nazev;
  /**
   * @generated
   */
  private String hodnota;
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
}