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
  
  public static final String DUPLICITNI_ZADANI_ZPRAVA = "�daj se stejn�mi atributy ji� existuje!";
  public static final String CELE_NEZAPORNE_CISLO_ZPRAVA = "Mus� b�t zad�no kladn� cel� ��slo!";
  public static final String POVINNY_UDAJ_ZPRAVA = "Ozna�en� �daje mus� b�t vypln�ny!";
  public static final String PLATNE_DATUM_ZPRAVA = "Neplatn� datum nebo �as!";
  public static final String REALNE_NEZAPORNE_CISLO_ZPRAVA = "Mus� b�t zad�no kladn� ��slo!";
  public static final String MAXIMALNI_DELKA_ZPRAVA = "Zadan� �daje jsou p��li� dlouh�!";
  public static final String PLATNE_DATUM_POROVNANI_ZPRAVA = "�as od je v�t�� ne� �as do!";
  public static final String REALNE_CISLO_0_1_ZPRAVA = "Mus� b�t zad�no ��slo 0 - 1!";
  public static final String PODPOROVANY_FORMAT_ZPRAVA = "Podporov�ny jsou pouze XLS soubory!";
  public static final String CHYBNY_SOUBOR_ZPRAVA = "Podporov�ny jsou pouze ics soubory!";
  public static final String REDUNDANTNI_DATA_ZPRAVA = "Kalend�� obsahuje ji� vlo�en� z�znamy!";
  
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
}
