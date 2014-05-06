package dao.databaze;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dao.model.Kalendar;
import dao.model.KalendarCinnost;
import dao.model.NastaveniSystemu;
import dao.model.PracovniPomer;
import dao.model.Uzivatel;

public class Databaze extends ADatabaze{

	public Object ziskejSystemoveInformace() {
		// TODO Auto-generated method stub
		return null;
	}

  @SuppressWarnings("unchecked")
  public List<KalendarCinnost> ziskejCinnosti(NastaveniSystemu datumOdN, NastaveniSystemu datumDoN, Uzivatel uzivatel) {
    List<KalendarCinnost> list = null;
    Session session = null;
    try{
      session = hibernate.getSession();
      String dotaz = "select o from " + KalendarCinnost.class.getName() + " o";
      dotaz += " where o.uzivatel.id='" + uzivatel.getId() + "' and o.datum>='" + datumOdN.getHodnota() + "' and o.datum<='" + datumDoN.getHodnota() + "'";
      dotaz += getRazeni(null, new String[]{"datum desc", "casOd asc"});
      Query query = session.createQuery(dotaz);
      list = query.list();
    }catch(RuntimeException e){
      throw e;
    }finally{}
    return list;
  }
  
  @SuppressWarnings("unchecked")
  public List<Kalendar> ziskejImporty(NastaveniSystemu datumOdN, NastaveniSystemu datumDoN, Uzivatel uzivatel) {
    List<Kalendar> list = null;
    Session session = null;
    try{
      session = hibernate.getSession();
      String dotaz = "select o from " + Kalendar.class.getName() + " o";
      dotaz += " where o.uzivatel.id='" + uzivatel.getId() + "' and o.datumImportu>='" + datumOdN.getHodnota() + "' and o.datumImportu<='" + datumDoN.getHodnota() + "'";
      dotaz += getRazeni(null, new String[]{"datumImportu desc"});
      Query query = session.createQuery(dotaz);
      list = query.list();
    }catch(RuntimeException e){
      throw e;
    }finally{}
    return list;
  }
  
  @SuppressWarnings("unchecked")
  public List<KalendarCinnost> ziskejCinnosti(String datumOd, String datumDo, PracovniPomer pomer) {
    List<KalendarCinnost> list = null;
    Session session = null;
    try{
      session = hibernate.getSession();
      String dotaz = "select o from " + KalendarCinnost.class.getName() + " o";
      dotaz += " where o.uzivatel.id='" + pomer.getUzivatel().getId() + "' and o.pracovniPomer.id='" + pomer.getId() + "' and o.datum>='" + datumOd + "' and o.datum<='" + datumDo + "'";
      dotaz += getRazeni(null, new String[]{"datum desc", "casOd asc"});
      Query query = session.createQuery(dotaz);
      list = query.list();
    }catch(RuntimeException e){
      throw e;
    }finally{}
    return list;
  }

}
