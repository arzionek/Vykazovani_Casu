package dao.beany;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

/**
 * Tøída pro vytvoøení objektu èasu
 * @author Vít Štìpánek
 *
 */
public class Cas{

	private String datum;
	private String cas;
	private String denTyden;
	private String denTydenZkratka;
	
	private int sekunda;
	private int minuta;
	private int hodina;
	private int den;
	private int mesic;
	private int rok;
	
	/**
	 * Konstruktor tøídy
	 */
	public Cas(){
		Date dat = new Date();
		DateFormat df = new SimpleDateFormat("dd_M_yyyy_HH_mm_ss"); 
		String datumCas[] = (df.format(dat)).split("_");
		
		den = Integer.parseInt(datumCas[0]);
		mesic = Integer.parseInt(datumCas[1]);
		rok = Integer.parseInt(datumCas[2]);
		hodina = Integer.parseInt(datumCas[3]);
		minuta = Integer.parseInt(datumCas[4]);
		sekunda = Integer.parseInt(datumCas[5]);
		
		nastavDatum("" + den, "" + mesic, "" + rok);
		nastavCas("" + hodina, "" + minuta);
		nastavDen(den, mesic, rok);
	}
	
	/**
	 * Konstruktor tøídy
	 */
	public Cas(Date dat){
		DateFormat df = new SimpleDateFormat("dd_M_yyyy_HH_mm_ss"); 
		String datumCas[] = (df.format(dat)).split("_");
		
		den = Integer.parseInt(datumCas[0]);
		mesic = Integer.parseInt(datumCas[1]);
		rok = Integer.parseInt(datumCas[2]);
		hodina = Integer.parseInt(datumCas[3]);
		minuta = Integer.parseInt(datumCas[4]);
		sekunda = Integer.parseInt(datumCas[5]);
		
		nastavDatum("" + den, "" + mesic, "" + rok);
		nastavCas("" + hodina, "" + minuta);
		nastavDen(den, mesic, rok);
	}
	
	/**
	 * Konstruktor tøídy
	 * @param datum
	 */
	public Cas(String datum){
		this.rok = Integer.parseInt(datum.substring(0, datum.indexOf('-')));
		datum = datum.substring(datum.indexOf('-') + 1);
		this.mesic = Integer.parseInt(datum.substring(0, datum.indexOf('-')));
		datum = datum.substring(datum.indexOf('-') + 1);
		this.den = Integer.parseInt(datum.substring(0, datum.indexOf(' ')));
		datum = datum.substring(datum.indexOf(' ') + 1);
		try{
			this.hodina = Integer.parseInt(datum.substring(0, datum.indexOf(':')));
			datum = datum.substring(datum.indexOf(':') + 1);
		}catch(Exception e){
			this.hodina = 0;
		}
		try{
			this.minuta = Integer.parseInt(datum.substring(0, datum.indexOf(':')));
			datum = datum.substring(datum.indexOf(':') + 1);
		}catch(Exception e){
			this.minuta = 0;
		}
		try{
			this.sekunda = Integer.parseInt(datum.substring(0, 2));
		}catch(Exception e){
			this.sekunda = 0;
		}
		
		nastavDatum("" + den, "" + mesic, "" + rok);
		nastavCas("" + hodina, "" + minuta);
		nastavDen(den, mesic, rok);
	}
	
	private void nastavCas(String hodina, String minuta) {
		if(hodina.length() == 1) hodina = "0" + hodina;
		if(minuta.length() == 1) minuta = "0" + minuta;
		this.cas = hodina + ":" + minuta;
	}

	private void nastavDatum(String den, String mesic, String rok) {
		if(den.length() == 1) den = "0" + den;
		if(mesic.length() == 1) mesic = "0" + mesic;
		this.datum = den + "." + mesic + "." + rok;
	}

	public String toString(){
		return den + ". " + mesic + ". " + rok + "  " + hodina + " : " + minuta + " : " + sekunda;
	}
	
	/**
	 * Metoda vracející kalendáø na základì data a èasu
	 * @return
	 */
	public Calendar getCalendar(){
		Calendar cd = Calendar.getInstance();
		cd.set(rok, mesic - 1, den, hodina, minuta, sekunda);
		return cd;
	}
	
	public String ziskejDatum(){
		Date dat = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return df.format(dat);
	}
	
	public String getDatum(){
		return datum;
	}
	
	public String getDatumDatabaze(boolean konec){
	  if(konec) return rok + "-" + mesic + "-" + den + " " + 23 + ":" + 59 + ":" + 59;
	  else return rok + "-" + mesic + "-" + den + " " + hodina + ":" + minuta + ":" + sekunda;
	}

	public int getHodina() {
		return hodina;
	}

	public int getMinuta() {
		return minuta;
	}

	public int getSekunda() {
		return sekunda;
	}

	public int getRok() {
		return rok;
	}

	public void setRok(int rok) {
		this.rok = rok;
	}
	
	public int getDen() {
		return den;
	}
	
	public int getMesic() {
		return mesic;
	}

	/**
	 * metoda pro upravení data
	 * @param datum
	 * @param request
	 * @return
	 */
	public static Cas upravDatum(String nazev, HttpServletRequest request, String nazev2) {
		String datum = request.getParameter(nazev);
		String upravene = "2013-10-01 00:00";
		try{
			int dd = Integer.parseInt(datum.substring(0, datum.indexOf('.')));
			if (dd < 1 || dd > 31) request.setAttribute("error3", true);
			datum = datum.substring(datum.indexOf('.') + 1);
			int mm = Integer.parseInt(datum.substring(0, datum.indexOf('.')));
			if (mm < 1 || mm > 12) request.setAttribute("error3", true);
			datum = datum.substring(datum.indexOf('.') + 1);
			int rr = Integer.parseInt(datum);
			if (rr < (new Cas().rok) || rr > 9999) request.setAttribute("error3", true);
			if(nazev2 != null) upravene = rr + "-" + mm + "-" + dd + " " + upravCas(nazev2, request);
			else upravene = rr + "-" + mm + "-" + dd + " 00:00:00";
		}catch(Exception e){
			request.setAttribute("error3", true);
		}
		return new Cas(upravene);
	}
	
	private static String upravCas(String nazev, HttpServletRequest request) throws Exception{
		String cas = request.getParameter(nazev);
		int hh = Integer.parseInt(cas.substring(0, cas.indexOf(':')));
		if (hh < 0 || hh > 23) request.setAttribute("error3", true);
		cas = cas.substring(cas.indexOf(':') + 1);
		int mm = Integer.parseInt(cas);
		if (mm < 0 || mm > 59) request.setAttribute("error3", true);
		return hh + ":" + mm + ":00";
	}

	public static boolean platneZadani(Cas casOd, Cas casDo, HttpServletRequest request) {
		boolean vysledek = casDo.getCalendar().compareTo(casOd.getCalendar()) >= 0;
		Object chyba = request.getAttribute("error3");
		if (!vysledek && chyba == null) request.setAttribute("error33", true);
		return vysledek;
	}
	
	public static Cas upravCas(Cas datum, String nazev, HttpServletRequest request) {
		String upraveny = datum.rok + "-" + datum.mesic + "-" + datum.den + " 00:00:00";
		try{
			upraveny = datum.rok + "-" + datum.mesic + "-" + datum.den + " " + upravCas(nazev, request);
		}catch(Exception e){
			request.setAttribute("error3", true);
		}
		return new Cas(upraveny);
	}

	public String getCas() {
		return cas;
	}

	public void setCas(String cas) {
		this.cas = cas;
	}

	private void nastavDen(int d, int m, int r) {
		Calendar c = Calendar.getInstance();
		c.set(r, m-1, d);
		denTyden = ((c.getTime()).toString()).substring(0, 3);
		if(denTyden.equals("Sun")){
			denTyden = "Nedìle\t";
		}else if(denTyden.equals("Mon")){
			denTyden = "Pondìlí\t";
		}else if(denTyden.equals("Tue")){
			denTyden = "Úterý\t";
		}else if(denTyden.equals("Wed")){
			denTyden = "Støeda\t";
		}else if(denTyden.equals("Thu")){
			denTyden = "Ètvrtek\t";
		}else if(denTyden.equals("Fri")){
			denTyden = "Pátek\t";
		}else if(denTyden.equals("Sat")){
			denTyden = "Sobota\t";
		}
		denTydenZkratka = denTyden.substring(0, 2);
	}

	public String getDenTyden() {
		return denTyden;
	}

	public void setDenTyden(String denTyden) {
		this.denTyden = denTyden;
	}

	public String getDenTydenZkratka() {
		return denTydenZkratka;
	}

	public void setDenTydenZkratka(String denTydenZkratka) {
		this.denTydenZkratka = denTydenZkratka;
	}
	
}
