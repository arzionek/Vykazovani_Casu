package dao.beany;

/**
 * Tøída obsahující akce pro smìrování
 * @author Vít Štìpánek
 *
 */
public class Akce {

  private static final String ODHLASIT = "-1";
  
  private static final String NASTAVENI_CINNOSTI = "1";
  private static final String NASTAVENI_CINNOSTI_VLOZIT = "11";
  private static final String NASTAVENI_CINNOSTI_UPRAVIT = "12";
  private static final String NASTAVENI_CINNOSTI_SMAZAT = "13";
  private static final String NASTAVENI_SVATKY = "2";
  private static final String NASTAVENI_SVATKY_VLOZIT = "21";
  private static final String NASTAVENI_SVATKY_UPRAVIT = "22";
  private static final String NASTAVENI_SVATKY_SMAZAT = "23";
	
	public String getOdhlasit() {
		return ODHLASIT;
	}

  public String getNastaveniCinnosti() {
    return NASTAVENI_CINNOSTI;
  }

  public String getNastaveniCinnostiVlozit() {
    return NASTAVENI_CINNOSTI_VLOZIT;
  }

  public String getNastaveniCinnostiUpravit() {
    return NASTAVENI_CINNOSTI_UPRAVIT;
  }

  public String getNastaveniCinnostiSmazat() {
    return NASTAVENI_CINNOSTI_SMAZAT;
  }

  public String getNastaveniSvatky() {
    return NASTAVENI_SVATKY;
  }

  public String getNastaveniSvatkyVlozit() {
    return NASTAVENI_SVATKY_VLOZIT;
  }

  public String getNastaveniSvatkyUpravit() {
    return NASTAVENI_SVATKY_UPRAVIT;
  }

  public String getNastaveniSvatkySmazat() {
    return NASTAVENI_SVATKY_SMAZAT;
  }

}
