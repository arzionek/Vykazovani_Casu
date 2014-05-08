package dao.beany;

public class Oznameni {
  public static final String VLOZENI_ZAZNAMU = "vlozeniZaznamu";
  
  public static final String VLOZENI_ZAZNAMU_ZPRAVA = "Záznam byl úspìšnì vložen do databáze.";
  
  public String getVlozeniZaznamu() {
    return VLOZENI_ZAZNAMU;
  }

  public String getVlozeniZaznamuZprava() {
    return VLOZENI_ZAZNAMU_ZPRAVA;
  }
}
