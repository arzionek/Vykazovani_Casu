package dao.beany;

public class Oznameni {
  public static final String VLOZENI_ZAZNAMU = "vlozeniZaznamu";
  
  public static final String VLOZENI_ZAZNAMU_ZPRAVA = "Z�znam byl �sp�n� vlo�en do datab�ze.";
  
  public String getVlozeniZaznamu() {
    return VLOZENI_ZAZNAMU;
  }

  public String getVlozeniZaznamuZprava() {
    return VLOZENI_ZAZNAMU_ZPRAVA;
  }
}
