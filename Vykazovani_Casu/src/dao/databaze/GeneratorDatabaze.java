package dao.databaze;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.util.HibernateHelper;

public class GeneratorDatabaze {
  
  @SuppressWarnings("static-access")
  public static void main(String[] args) {
    try {
      HibernateHelper hiber = HibernateHelper.getInstance();
      Date date = new Date();
      SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd");
      String nazev = "WebContent\\scripty\\" + format.format(date) + "_pokus.sql";
      hiber.main(new String[]{nazev});
      BufferedReader bfr = new BufferedReader(new FileReader(nazev));
      String dokument = "";
      String line;
      while((line = bfr.readLine()) != null) {
        dokument += line + ";\n";
      }
      bfr.close();
      
      int i = 0;
      String klic = "fk_uzivatel_id";
      int index = dokument.indexOf("drop foreign key " + klic + ";");
      while(index != -1){
        dokument = dokument.replaceFirst("drop foreign key " + klic + ";", "drop foreign key " + klic + "_" + i + ";");
        dokument = dokument.replaceFirst("add constraint " + klic + " ", "add constraint " + klic + "_" + i + " ");
        i++;
        index = dokument.indexOf("drop foreign key " + klic + ";");
      }
      
      String dokument2 = dokument;
      String pridat = " ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci";
      index = dokument2.indexOf("create table");
      int index2 = dokument2.indexOf("add index");
      int posunuti = 0;
      for (int j = index; j < index2; j++) {
        if(dokument.charAt(j) == ';'){
          dokument2 = dokument2.substring(0, posunuti + j) + pridat + dokument2.substring(posunuti + j);
          posunuti += pridat.length();
        }
      }
      
      FileWriter fw = new FileWriter(nazev);
      fw.write(dokument2);
      fw.close();
      //hibernate.main(new String[]{});
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
