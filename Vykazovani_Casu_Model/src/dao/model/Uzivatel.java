package dao.model;

public class Uzivatel extends AEntita implements java.io.Serializable {
	
  private String celeJmeno;
  /**
   * @generated
   */
  private String prijmeni;
	/**
   * @generated
   */
  private String titulPred;
	/**
   * @generated
   */
  private String titulZa;
	/**
   * @generated
   */
  private String login;
	/**
   * @generated
   */
  private String heslo;
	/**
   * @generated
   */
  private java.util.Set<Cinnost> cinnost = new java.util.HashSet<Cinnost>();
	/**
   * @generated
   */
  private static final long serialVersionUID = -1014965043L;
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
  private java.util.Set<KalendarCinnost> kalendarCinnost = new java.util.HashSet<KalendarCinnost>();

	/**
   * @generated
   */
  private java.util.Set<SablonaVykaz> sablonaVykaz = new java.util.HashSet<SablonaVykaz>();
  /**
   * @generated
   */
  private java.util.Set<Svatek> svatek = new java.util.HashSet<Svatek>();
  /**
   * @generated
   */
  private java.util.Set<NastaveniSystemu> nastaveniSystemu = new java.util.HashSet<NastaveniSystemu>();

  /**
   * @generated
   */
  private java.util.Set<KalendarDefinice> kalendarDefinice = new java.util.HashSet<KalendarDefinice>();
  /**
   * @generated
   */
  private java.util.Set<Kalendar> kalendar = new java.util.HashSet<Kalendar>();

  /**
   * @generated
   */
  private String jmeno;

  /**
   * @generated
   */
	public String getPrijmeni() {
    return this.prijmeni;
  }

	/**
   * @generated
   */
	public void setPrijmeni(String prijmeni) {
    this.prijmeni = prijmeni;
  }

	/**
   * @generated
   */
	public String getTitulPred() {
    return this.titulPred;
  }

	/**
   * @generated
   */
	public void setTitulPred(String titulPred) {
    this.titulPred = titulPred;
  }

	/**
   * @generated
   */
	public String getTitulZa() {
    return this.titulZa;
  }

	/**
   * @generated
   */
	public void setTitulZa(String titulZa) {
    this.titulZa = titulZa;
  }

	/**
   * @generated
   */
	public String getLogin() {
    return this.login;
  }

	/**
   * @generated
   */
	public void setLogin(String login) {
    this.login = login;
  }

	/**
   * @generated
   */
	public String getHeslo() {
    return this.heslo;
  }

	/**
   * @generated
   */
	public void setHeslo(String heslo) {
    this.heslo = heslo;
  }

	/**
   * @generated
   */
	public java.util.Set<Cinnost> getCinnost() {
    return cinnost;
  }

	/**
	 * @generated
	 */
	public void setCinnost(java.util.Set<Cinnost> cinnost) {
		this.cinnost = cinnost;
	}

	/**
	 * @generated
	 */
	public void addCinnost(Cinnost cinnost) {
		getCinnost().add(cinnost);
	}

	/**
	 * @generated
	 */
	public void removeCinnost(Cinnost cinnost) {
		getCinnost().remove(cinnost);
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
    return "Uzivatel" + " jmeno=" + jmeno + " prijmeni=" + prijmeni
        + " titulPred=" + titulPred + " titulZa=" + titulZa + " login=" + login
        + " heslo=" + heslo + " id=" + id;
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

  /**
   * @generated
   */
  public java.util.Set<Svatek> getSvatek() {
    return svatek;
  }

  /**
   * @generated
   */
  public void setSvatek(java.util.Set<Svatek> svatek) {
    this.svatek = svatek;
  }

  /**
   * @generated
   */
  public void addSvatek(Svatek svatek) {
    getSvatek().add(svatek);
  }

  /**
   * @generated
   */
  public void removeSvatek(Svatek svatek) {
    getSvatek().remove(svatek);
  }

  /**
   * @generated
   */
  public java.util.Set<NastaveniSystemu> getNastaveniSystemu() {
    return nastaveniSystemu;
  }

  /**
   * @generated
   */
  public void setNastaveniSystemu(
      java.util.Set<NastaveniSystemu> nastaveniSystemu) {
    this.nastaveniSystemu = nastaveniSystemu;
  }

  /**
   * @generated
   */
  public void addNastaveniSystemu(NastaveniSystemu nastaveniSystemu) {
    getNastaveniSystemu().add(nastaveniSystemu);
  }

  /**
   * @generated
   */
  public void removeNastaveniSystemu(NastaveniSystemu nastaveniSystemu) {
    getNastaveniSystemu().remove(nastaveniSystemu);
  }

  /**
   * @generated
   */
  public java.util.Set<KalendarDefinice> getKalendarDefinice() {
    return kalendarDefinice;
  }

  /**
   * @generated
   */
  public void setKalendarDefinice(
      java.util.Set<KalendarDefinice> kalendarDefinice) {
    this.kalendarDefinice = kalendarDefinice;
  }

  /**
   * @generated
   */
  public void addKalendarDefinice(KalendarDefinice kalendarDefinice) {
    getKalendarDefinice().add(kalendarDefinice);
  }

  /**
   * @generated
   */
  public void removeKalendarDefinice(KalendarDefinice kalendarDefinice) {
    getKalendarDefinice().remove(kalendarDefinice);
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
  public Uzivatel() {
  }

  public String getCeleJmeno() {
    celeJmeno = getTitulPred() + " " + getJmeno() + " " + getPrijmeni();
    if(getTitulZa().length() > 0) celeJmeno += ", " + getTitulZa();
    return celeJmeno;
  }

  public void setCeleJmeno(String celeJmeno) {
    this.celeJmeno = celeJmeno;
  }

  /**
   * @generated
   */
  public String getJmeno() {
    return this.jmeno;
  }

  /**
   * @generated
   */
  public void setJmeno(String jmeno) {
    this.jmeno = jmeno;
  }
}