package dao.model;

public class SablonaVykaz extends AEntita implements java.io.Serializable {
	/**
   * @generated
   */
  private String nazev;
  /**
   * @generated
   */
  private String typ;
  public static final String PRACOVNI_VYKAZ = "Pracovní výkaz";
  public static final String EVIDENCE_DOCHAZKY = "Evidence docházky";
  public static final String DOVOLENA = "Dovolená";
  private static final String[] TYPY = {PRACOVNI_VYKAZ, EVIDENCE_DOCHAZKY, DOVOLENA};
  /**
   * @generated
   */
  private byte[] data;
  /**
   * @generated
   */
  private Uzivatel uzivatel;
  /**
   * @generated
   */
  private static final long serialVersionUID = -2119251784L;
  /**
   * @generated
   */
  private Long id;

  /**
   * @generated
   */
  private java.util.Set<PracovniPomer> pracovniPomer = new java.util.HashSet<PracovniPomer>();

  /**
   * @generated
   */
  private String kod;

  /**
   * @generated
   */
	public SablonaVykaz() {
  }

	/**
   * @generated
   */
	public String toString() {
    return "SablonaVykaz" + " kod=" + kod + " nazev=" + nazev + " typ=" + typ
        + " data=" + java.util.Arrays.toString(data) + " id=" + id;
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
  public String getTyp() {
    return this.typ;
  }

  /**
   * @generated
   */
  public void setTyp(String typ) {
    this.typ = typ;
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

  public static String[] getTypy() {
    return TYPY;
  }

  /**
   * @generated
   */
  public java.util.Set<PracovniPomer> getPracovniPomer() {
    return pracovniPomer;
  }

  /**
   * @generated
   */
  public void setPracovniPomer(java.util.Set<PracovniPomer> pracovniPomer) {
    this.pracovniPomer = pracovniPomer;
  }

  /**
   * @generated
   */
  public void addPracovniPomer(PracovniPomer pracovniPomer) {
    getPracovniPomer().add(pracovniPomer);
  }

  /**
   * @generated
   */
  public void removePracovniPomer(PracovniPomer pracovniPomer) {
    getPracovniPomer().remove(pracovniPomer);
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
}