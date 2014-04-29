package dao.databaze;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dao.model.KalendarCinnost;
import dao.model.NastaveniSystemu;
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

}
