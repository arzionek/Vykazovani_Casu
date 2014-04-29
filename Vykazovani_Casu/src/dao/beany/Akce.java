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
	
	private static final String NASTAVENI_POMERU = "3";
	private static final String NASTAVENI_POMERU_VLOZIT = "31";
	private static final String NASTAVENI_POMERU_UPRAVIT = "32";
	private static final String NASTAVENI_POMERU_SMAZAT = "33";

	private static final String NASTAVENI_DEFINICE_KALENDARE = "4";
	private static final String NASTAVENI_DEFINICE_KALENDARE_VLOZIT = "41";
	private static final String NASTAVENI_DEFINICE_KALENDARE_UPRAVIT = "42";
	private static final String NASTAVENI_DEFINICE_KALENDARE_SMAZAT = "43";
	
	private static final String PREHLED = "6";
  private static final String VYTVORENI_VLOZIT = "61";
  private static final String PREHLED_UPRAVIT = "62";
  private static final String PREHLED_SMAZAT = "63";
  private static final String PREHLED_OBDOBI = "64";
  
  private static final String IMPORT = "7";
  private static final String IMPORT_NAHRAT = "71";
  private static final String IMPORT_SMAZAT = "72";

	
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

	public String getNastaveniPomeru() {
		return NASTAVENI_POMERU;
	}

	public String getNastaveniPomeruVlozit() {
		return NASTAVENI_POMERU_VLOZIT;
	}

	public String getNastaveniPomeruUpravit() {
		return NASTAVENI_POMERU_UPRAVIT;
	}

	public String getNastaveniPomeruSmazat() {
		return NASTAVENI_POMERU_SMAZAT;
	}
	
	public String getNastaveniDefiniceKalendare() {
		return NASTAVENI_DEFINICE_KALENDARE;
	}

	public String getNastaveniDefiniceKalendareVlozit() {
		return NASTAVENI_DEFINICE_KALENDARE_VLOZIT;
	}

	public String getNastaveniDefiniceKalendareUpravit() {
		return NASTAVENI_DEFINICE_KALENDARE_UPRAVIT;
	}

	public String getNastaveniDefiniceKalendareSmazat() {
		return NASTAVENI_DEFINICE_KALENDARE_SMAZAT;
	}

  public String getPrehled() {
    return PREHLED;
  }

  public String getVytvoreniVlozit() {
    return VYTVORENI_VLOZIT;
  }

  public String getPrehledUpravit() {
    return PREHLED_UPRAVIT;
  }

  public String getPrehledSmazat() {
    return PREHLED_SMAZAT;
  }

  public String getImport() {
    return IMPORT;
  }

  public String getImportNahrat() {
    return IMPORT_NAHRAT;
  }

  public String getImportSmazat() {
    return IMPORT_SMAZAT;
  }

  public String getPrehledObdobi() {
    return PREHLED_OBDOBI;
  }

}
