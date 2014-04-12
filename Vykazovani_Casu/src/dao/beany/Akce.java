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

}
