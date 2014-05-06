package dao.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class KalendarCinnost extends AEntita implements java.io.Serializable {
	/**
   * @generated
   */
  private Uzivatel uzivatel;
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
  private String casDo2;

  /**
   * @generated
   */
  private java.util.Date casOd;
  private String casOd2;

  /**
   * @generated
   */
  private java.util.Date datum;
  private String datum2;
  private String datum3;

  /**
   * @generated
   */
  private String popis;

  /**
   * @generated
   */
  private String googleId;

  /**
   * @generated
   */
	public String toString() {
    return "KalendarCinnost" + " datum=" + datum + " pocetHodin=" + pocetHodin
        + " id=" + id + " casOd=" + casOd + " casDo=" + casDo + " popis="
        + popis + " googleId=" + googleId;
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

	private String varovani;
	
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

  public String getCasDo2() {
    if(casDo != null){
      DateFormat df = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss"); 
      String datumCas[] = (df.format(casDo)).split("_");
      
      String hodina = datumCas[3];
      if(hodina.length() == 1) hodina = "0" + hodina;
      String minuta = datumCas[4];
      if(minuta.length() == 1) minuta = "0" + minuta;
      casDo2 = hodina + ":" + minuta;
    }
    return casDo2;
  }

  public void setCasDo2(String casDo2) {
    this.casDo2 = casDo2;
  }

  public String getCasOd2() {
    if(casOd != null){
      DateFormat df = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss"); 
      String datumCas[] = (df.format(casOd)).split("_");
      
      String hodina = datumCas[3];
      if(hodina.length() == 1) hodina = "0" + hodina;
      String minuta = datumCas[4];
      if(minuta.length() == 1) minuta = "0" + minuta;
      casOd2 = hodina + ":" + minuta;
    }
    return casOd2;
  }

  public void setCasOd2(String casOd2) {
    this.casOd2 = casOd2;
  }

  public String getDatum2() {
    if(datum != null){
      DateFormat df = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss"); 
      String datumCas[] = (df.format(datum)).split("_");
      
      String den = datumCas[0];
      if(den.length() == 1) den = "0" + den;
      String mesic = datumCas[1];
      if(mesic.length() == 1) mesic = "0" + mesic;
      String rok = datumCas[2];
      if(rok.length() == 1) rok = "0" + rok;
      datum2 = den + "." + mesic + "." + rok;
    }
    return datum2;
  }

  public void setDatum2(String datum2) {
    this.datum2 = datum2;
  }

  /**
   * @generated
   */
  public String getPopis() {
    return this.popis;
  }

  /**
   * @generated
   */
  public void setPopis(String popis) {
    this.popis = popis;
  }

  /**
   * @generated
   */
  public String getGoogleId() {
    return this.googleId;
  }

  /**
   * @generated
   */
  public void setGoogleId(String googleId) {
    this.googleId = googleId;
  }

  public String getVarovani() {
    return varovani;
  }

  public void setVarovani(String varovani) {
    this.varovani = varovani;
  }

  public String getDatum3() {
    return datum3;
  }

  public void setDatum3(String datum3) {
    this.datum3 = datum3;
  }
}