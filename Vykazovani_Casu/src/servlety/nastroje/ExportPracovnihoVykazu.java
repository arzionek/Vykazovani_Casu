package servlety.nastroje;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import dao.model.ExportSablona;

public class ExportPracovnihoVykazu extends ExportDoSablony {

  private static final long serialVersionUID = -81168711497304198L;
  
  public static Vysledek exportPracovnihoVykazu(ExportSablona export) {
    Vysledek vysledek = new Vysledek();
    vysledek.nazevSouboru = "pracovni_vykaz";
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
