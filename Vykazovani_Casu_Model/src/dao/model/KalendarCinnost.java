package dao.model;


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
  private static final long serialVersionUID = -1328657581L;
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
  private java.util.Date casDo;

  /**
   * @generated
   */
  private java.util.Date casOd;

  /**
   * @generated
   */
	public String toString() {
    return "KalendarCinnost" + " datum=" + datum + " pocetHodin=" + pocetHodin
        + " id=" + id + " casOd=" + casOd + " casDo=" + casDo;
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

  /**
   * @generated
   */
  public java.util.Date getCasDo() {
    return this.casDo;
  }

  /**
   * @generated
   */
  public void setCasDo(java.util.Date casDo) {
    this.casDo = casDo;
  }

  /**
   * @generated
   */
  public java.util.Date getCasOd() {
    return this.casOd;
  }

  /**
   * @generated
   */
  public void setCasOd(java.util.Date casOd) {
    this.casOd = casOd;
  }
}