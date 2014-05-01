package servlety.nastroje;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class Download {
  
  public static void download(HttpServletResponse response, byte[] data, String nazev) {
    try{
      ServletOutputStream outStream = response.getOutputStream();
      String mimetype = "";
      String zpusob = "";
      mimetype = "application/octet-stream";
      zpusob = "attachment";
        
      response.setContentType(mimetype);
      response.setContentLength(data.length);
      response.setHeader("Content-Disposition", zpusob + "; filename=\"" + nazev + "\"");      
      outStream.write(data);
      outStream.close();
    }catch(Exception e){
      e.printStackTrace();
    }
  }

}
