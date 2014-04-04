package dao.model;


/**
 * @generated
 */
public class Cinnost implements java.io.Serializable {
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
  private static final long serialVersionUID = -1111356055L;
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
	public String toString() {
    return "Cinnost" + " kod=" + kod + " nazev=" + nazev + " id=" + id;
  }

	/**
   * @generated
   */
	public Cinnost() {
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
}