package dao.beany;

public class Chyby {
  public static final String DUPLICITNI_ZADANI = "duplicitniZadani";
  public static final String CELE_NEZAPORNE_CISLO = "celeNezaporneCislo";
  public static final String POVINNY_UDAJ = "povinnyUdaj";
  public static final String PLATNE_DATUM = "platneDatum";
  public static final String REALNE_NEZAPORNE_CISLO = "realneNezaporneCislo";
  public static final String MAXIMALNI_DELKA = "maximalniDelka";
  public static final String PLATNE_DATUM_POROVNANI = "platneDatumPorovnani";
  public static final String REALNE_CISLO_0_1 = "realneCislo01";
  public static final String PODPOROVANY_FORMAT = "podporovanyFormat";
  public static final String CHYBNY_SOUBOR = "chybnySoubor";
  public static final String REDUNDANTNI_DATA = "redundantniData";
  
  public static final String PRACE_VIKEND = "praceVikend";
  public static final String PRACE_SVATEK = "praceSvatek";
  public static final String POMER_MESICNI_FOND = "pomerMesicniFond";
  
  public static final String DUPLICITNI_ZADANI_ZPRAVA = "Údaj se stejnými atributy již existuje!";
  public static final String CELE_NEZAPORNE_CISLO_ZPRAVA = "Musí být zadáno kladné celé èíslo!";
  public static final String POVINNY_UDAJ_ZPRAVA = "Oznaèené údaje musí být vyplnìny!";
  public static final String PLATNE_DATUM_ZPRAVA = "Neplatné datum nebo èas!";
  public static final String REALNE_NEZAPORNE_CISLO_ZPRAVA = "Musí být zadáno kladné èíslo!";
  public static final String MAXIMALNI_DELKA_ZPRAVA = "Zadané údaje jsou pøíliš dlouhé!";
  public static final String PLATNE_DATUM_POROVNANI_ZPRAVA = "Èas od musí být menší než èas do!";
  public static final String REALNE_CISLO_0_1_ZPRAVA = "Musí být zadáno èíslo 0 - 1!";
  public static final String PODPOROVANY_FORMAT_ZPRAVA = "Podporovány jsou pouze XLS soubory!";
  public static final String CHYBNY_SOUBOR_ZPRAVA = "Podporovány jsou pouze ics soubory!";
  public static final String REDUNDANTNI_DATA_ZPRAVA = "Všechny záznamy jsou již uloženy!";
  
  public static final String PRACE_VIKEND_ZPRAVA = "Práce o víkendu!";
  public static final String PRACE_SVATEK_ZPRAVA = "Práce ve státní svátek!";
  public static final String POMER_MESICNI_FOND_ZPRAVA = "Pøekroèen mìsíèní fond!";
  
  public String getDuplicitniZadani() {
    return DUPLICITNI_ZADANI;
  }

  public String getCeleNezaporneCislo() {
    return CELE_NEZAPORNE_CISLO;
  }

  public String getPovinnyUdaj() {
    return POVINNY_UDAJ;
  }

  public String getPlatneDatum() {
    return PLATNE_DATUM;
  }

  public String getRealneNezaporneCislo() {
    return REALNE_NEZAPORNE_CISLO;
  }

  public String getMaximalniDelka() {
    return MAXIMALNI_DELKA;
  }

  public String getDuplicitniZadaniZprava() {
    return DUPLICITNI_ZADANI_ZPRAVA;
  }

  public String getCeleNezaporneCisloZprava() {
    return CELE_NEZAPORNE_CISLO_ZPRAVA;
  }

  public String getPovinnyUdajZprava() {
    return POVINNY_UDAJ_ZPRAVA;
  }

  public String getPlatneDatumZprava() {
    return PLATNE_DATUM_ZPRAVA;
  }

  public String getRealneNezaporneCisloZprava() {
    return REALNE_NEZAPORNE_CISLO_ZPRAVA;
  }

  public String getMaximalniDelkaZprava() {
    return MAXIMALNI_DELKA_ZPRAVA;
  }

  public String getPlatneDatumPorovnani() {
    return PLATNE_DATUM_POROVNANI;
  }

  public String getPlatneDatumPorovnaniZprava() {
    return PLATNE_DATUM_POROVNANI_ZPRAVA;
  }

  public String getRealneCislo01() {
    return REALNE_CISLO_0_1;
  }

  public String getRealneCislo01Zprava() {
    return REALNE_CISLO_0_1_ZPRAVA;
  }

  public String getPodporovanyFormat() {
    return PODPOROVANY_FORMAT;
  }

  public String getPodporovanyFormatZprava() {
    return PODPOROVANY_FORMAT_ZPRAVA;
  }

  public String getChybnySoubor() {
    return CHYBNY_SOUBOR;
  }

  public String getChybnySouborZprava() {
    return CHYBNY_SOUBOR_ZPRAVA;
  }
  
  public String getRedundantniData() {
    return REDUNDANTNI_DATA;
  }

  public String getRedundantniDataZprava() {
    return REDUNDANTNI_DATA_ZPRAVA;
  }

  public String getPraceVikend() {
    return PRACE_VIKEND;
  }

  public String getPraceSvatek() {
    return PRACE_SVATEK;
  }

  public String getPraceVikendZprava() {
    return PRACE_VIKEND_ZPRAVA;
  }

  public String getPraceSvatekZprava() {
    return PRACE_SVATEK_ZPRAVA;
  }

  public String getPomerMesicniFondZprava() {
    return POMER_MESICNI_FOND_ZPRAVA;
  }

  public String getPomerMesicniFond() {
    return POMER_MESICNI_FOND;
  }
}
