package servlety.nastroje;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dao.model.Uzivatel;

public class WeboveSluzbyStag {

  private static final String WS = "https://stag-ws.zcu.cz/ws/services/rest";
  private static final String CISLO_STUDENTA = WS + "/users/" + "getOsobniCislaByExternalLogin";
  private static final String UDAJE_STUDENT = WS + "/student/" + "getStudentInfo";
  private static final String CISLO_UCITELE = WS + "/users/" + "getUcitIdnoByExternalLogin";
  private static final String UDAJE_UCITEL = WS + "/ucitel/" + "getUcitelInfo";
  
  private static TrustManager[] trustAllCerts = new TrustManager[]{
      new X509TrustManager() {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
          return null;
        }
        public void checkClientTrusted(
            java.security.cert.X509Certificate[] certs, String authType) {
        }
        public void checkServerTrusted(
            java.security.cert.X509Certificate[] certs, String authType) {
        }
      }
  };

  public static void nastavUdaje(Uzivatel uzivatel, String stagUserTicket) {
    try{
      String cislo = getCisloUcitele(uzivatel.getLogin(), stagUserTicket);
      String target = null;
      String nodeElement = null;
      if(cislo == null){
        cislo = getCisloStudenta(uzivatel.getLogin(), stagUserTicket);
        target = UDAJE_STUDENT;
        target += "?osCislo=" + URLEncoder.encode(cislo, "UTF-8");
        nodeElement = "student";
      }else{
        cislo = getCisloUcitele(uzivatel.getLogin(), stagUserTicket);
        target = UDAJE_UCITEL;
        target += "?ucitIdno=" + URLEncoder.encode(cislo, "UTF-8");
        nodeElement = "ucitelFullInfo";
      }
      
      SSLContext context2 = SSLContext.getInstance("SSL");
      context2.init(null, trustAllCerts, new java.security.SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(context2.getSocketFactory());

      HttpURLConnection httpConnection = getHttpConnection(new URL(target), stagUserTicket);
      httpConnection.connect();

      int responseCode = httpConnection.getResponseCode();
      if (responseCode == 200) nastavUzivatele(httpConnection.getInputStream(), uzivatel, nodeElement); 
      else System.out.println("Nastala chyba_1: " + httpConnection.getResponseMessage());
    }catch(Exception e){
      e.printStackTrace();
    }
  }


  private static void nastavUzivatele(InputStream inputStream, Uzivatel uzivatel, String nodeElement) throws ParserConfigurationException, SAXException, IOException {
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(inputStream);
    doc.getDocumentElement().normalize();

    NodeList nList = doc.getElementsByTagName(nodeElement);
    for (int temp = 0; temp < nList.getLength(); temp++) {
      Node nNode = nList.item(temp);
      if (nNode.getNodeType() == Node.ELEMENT_NODE) {
        Element e = (Element) nNode;
        uzivatel.setJmeno(getAtribut(e, "jmeno"));
        uzivatel.setPrijmeni(getAtribut(e, "prijmeni"));
        uzivatel.setTitulPred(getAtribut(e, "titulPred"));
        uzivatel.setTitulZa(getAtribut(e, "titulZa"));
      }
    }
  }

  private static String getCisloStudenta(String login, String stagUserTicket) {
    String result = null;
    try{
      String target = CISLO_STUDENTA;
      target += "?login=" + URLEncoder.encode(login, "UTF-8");

      SSLContext context2 = SSLContext.getInstance("SSL");
      context2.init(null, trustAllCerts, new java.security.SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(context2.getSocketFactory());

      HttpURLConnection httpConnection = getHttpConnection(new URL(target), stagUserTicket);
      httpConnection.connect();

      int responseCode = httpConnection.getResponseCode();
      if (responseCode == 200){
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(httpConnection.getInputStream());
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("osobniCisla");
        for (int temp = 0; temp < nList.getLength(); temp++) {
          Node nNode = nList.item(temp);
          if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element e = (Element) nNode;
            result = getAtribut(e, "osCislo");
          }
        }
      }
      else System.out.println("Nastala chyba_0: " + httpConnection.getResponseMessage());
    }catch(Exception e){
      e.printStackTrace();
    }
    return result;
  }
  
  private static String getCisloUcitele(String login, String stagUserTicket) {
    String result = null;
    try{
      String target = CISLO_UCITELE;
      target += "?externalLogin=" + URLEncoder.encode(login, "UTF-8");

      SSLContext context2 = SSLContext.getInstance("SSL");
      context2.init(null, trustAllCerts, new java.security.SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(context2.getSocketFactory());

      HttpURLConnection httpConnection = getHttpConnection(new URL(target), stagUserTicket);
      httpConnection.connect();

      int responseCode = httpConnection.getResponseCode();
      if (responseCode == 200){
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(httpConnection.getInputStream());
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getChildNodes();
        for (int temp = 0; temp < nList.getLength(); temp++) {
          Node nNode = nList.item(temp);
          if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element e = (Element) nNode;
            result = getAtribut(e, "ucitIdno");
          }
        }
      }
      else System.out.println("Nastala chyba_0: " + httpConnection.getResponseMessage());
    }catch(Exception e){
      e.printStackTrace();
    }
    return result;
  }

  private static String getAtribut(Element e, String atribut) {
    if(e.getElementsByTagName(atribut).item(0) != null) return e.getElementsByTagName(atribut).item(0).getTextContent();
    else return null;
  }

  private static HttpURLConnection getHttpConnection(URL url, String ticket) throws IOException {
    HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
    byte[] encoded = Base64.encodeBase64((ticket + ":").getBytes());
    if(ticket != null) httpConnection.setRequestProperty("Authorization", "Basic " + new String(encoded));
    httpConnection.setConnectTimeout(5000);
    httpConnection.setRequestMethod("GET");
    httpConnection.setInstanceFollowRedirects(false);
    httpConnection.setUseCaches(false);
    httpConnection.setDoOutput(true);
    httpConnection.setDoInput(true);
    return httpConnection;
  }

}
