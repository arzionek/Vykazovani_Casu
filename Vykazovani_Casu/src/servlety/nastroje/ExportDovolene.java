package servlety.nastroje;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import dao.model.ExportSablona;

public class ExportDovolene extends ExportDoSablony {

  private static final long serialVersionUID = -7150966574093419954L;
  
  public static Vysledek exportDovolene(ExportSablona export) {
    Vysledek vysledek = new Vysledek();
    vysledek.nazevSouboru = "dovolena";
    try {
      ByteArrayInputStream bais = new ByteArrayInputStream(export.getSablonaVykaz().getData());
      
      HSSFWorkbook workbook = new HSSFWorkbook(bais);
      HSSFSheet sheet = workbook.getSheetAt(0);

      //TODO

      bais.close();

      vysledek.data = getData(workbook);
    }catch (IOException e) {
      e.printStackTrace();
    }
    return vysledek;
  }

}
