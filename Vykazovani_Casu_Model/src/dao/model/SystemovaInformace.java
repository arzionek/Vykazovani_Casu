package dao.model;


/**
 * @generated
 */
public class SystemovaInformace implements java.io.Serializable {
	/**
   * @generated
   */
  private java.util.Date datum;
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
}