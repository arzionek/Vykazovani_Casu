package dao.databaze;

import dao.util.HibernateHelper;

public class GeneratorDatabaze {
  
  @SuppressWarnings("static-access")
  public static void main(String[] args) {
    try {
      HibernateHelper hibernate = HibernateHelper.getInstance();
      //hibernate.main(new String[]{"WebContent\\scripty\\pokus.sql"});
      hibernate.main(new String[]{});
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
