package dao.databaze;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.model.AEntita;
import dao.model.Uzivatel;
import dao.util.HibernateHelper;

public abstract class ADatabaze{
  
  protected HibernateHelper hibernate;
  private static final int TIMEOUT = 5;
  
  public ADatabaze(){
    hibernate = HibernateHelper.getInstance();
  }
  
  @SuppressWarnings("unchecked")
  public <T> T nacti(Class<T> trida, Long id) {
    T object = null;
    Session session = null;
    try{
      session = hibernate.getSession();
      object = (T) session.load(trida, id);
    }catch(RuntimeException e){
      throw e;
    }finally{
      /*if(session != null){
        hibernate.closeSession();
      }*/
    }
    return object;
  }
  
  public <T> T nacti(Class<T> trida, String atribut, String hodnota){
    return nacti(trida, new String[]{atribut}, new String[]{hodnota}, null, null);
  }
  
  public <T> T nacti(Class<T> trida, Object atributy[], Object hodnoty[], Boolean zaroven){
    return nacti(trida, atributy, hodnoty, zaroven, null);
  }
  
  public <T> T nacti(Class<T> trida, Object atributy[], Object hodnoty[], Uzivatel uzivatel){
	  return nacti(trida, atributy, hodnoty, null, uzivatel);
  }
  
  public <T> T nacti(Class<T> trida, String atribut, String hodnota, Boolean zaroven, Uzivatel uzivatel) {
    return nacti(trida, new String[]{atribut}, new String[]{hodnota}, zaroven, uzivatel);
  }
  
  public <T> T nacti(Class<?> trida, Object atributy[], Object hodnoty[], Boolean zaroven, Uzivatel uzivatel){
    T object = null;
    Session session = null;
    try{
      session = hibernate.getSession();
      String dotaz = "select o from " + trida.getName() + " o";
      dotaz += " where" + getPodminka(trida, atributy, hodnoty, zaroven, uzivatel);
      Query query = session.createQuery(dotaz);
      @SuppressWarnings("unchecked")
      List<T> list = query.list();
      if(list != null && !list.isEmpty()) object = (T) list.get(0);
    }catch(RuntimeException e){
      throw e;
    }finally{
      /*if(session != null){
        hibernate.closeSession();
      }*/
    }
    return object;
  }

  public void vlozUprav(Object object, Long id) {
    Session session = null;
    Transaction tx = null;
    try{
      if(id == null){
        session = hibernate.getSession();
        tx = session.beginTransaction();
        tx.setTimeout(TIMEOUT);
        session.save(object);
        tx.commit();
      }else{
        session = hibernate.getSession();
        tx = session.beginTransaction();
        tx.setTimeout(TIMEOUT);
        session.save(object);
        tx.commit();
      }
    }catch(RuntimeException e){
      try{
        tx.rollback();
      }catch(RuntimeException rbe){
        rbe.printStackTrace();
      }
      throw e;
    }finally{
      /*if(session != null){
        hibernate.closeSession();
      }*/
    }
  }
  
  public void smaz(Object object) {
    Session session = null;
    Transaction tx = null;
    try{
      session = hibernate.getSession();
      tx = session.beginTransaction();
      tx.setTimeout(TIMEOUT);
      session.delete(object);
      tx.commit();
    }catch(RuntimeException e){
      try{
        tx.rollback();
      }catch(RuntimeException rbe){
        rbe.printStackTrace();
      }
      throw e;
    }finally{
      /*if(session != null){
        hibernate.closeSession();
      }*/
    }
  }
  
  public <T> List<T> ziskejObjekty(Class<T> trida, Uzivatel uzivatel) {
    return ziskejObjekty(trida, uzivatel, new String[]{});
  }
  
  public <T> List<T> ziskejObjekty(Class<T> trida, Uzivatel uzivatel, String atributRazeni) {
    return ziskejObjekty(trida, uzivatel, new String[]{atributRazeni});
  }
  
  public <T> List<T> ziskejObjekty(Class<T> trida, Uzivatel uzivatel, String[] atributyRazeni) {
    return ziskejObjekty(trida, null, null, null, uzivatel, atributyRazeni);
  }
  
  @SuppressWarnings("unchecked")
  public <T> List<T> ziskejObjekty(Class<T> trida, Object atributy[], Object hodnoty[], Boolean zaroven, Uzivatel uzivatel, String[] atributyRazeni) {
    List<T> list = null;
    Session session = null;
    try{
      session = hibernate.getSession();
      String dotaz = "select o from " + trida.getName() + " o";
      dotaz += " where" + getPodminka(trida, atributy, hodnoty, zaroven, uzivatel);
      dotaz += getRazeni(trida, atributyRazeni);
      Query query = session.createQuery(dotaz);
      list = query.list();
    }catch(RuntimeException e){
      throw e;
    }finally{
      /*if(session != null){
        hibernate.closeSession();
      }*/
    }
    return list;
  }

  private String getPodminka(Class<?> trida, Object[] atributy, Object[] hodnoty, Boolean zaroven, Uzivatel uzivatel) {
    String podminka = "";
    for (int i = 0; atributy != null && i < atributy.length; i++) {
      podminka += " o." + atributy[i].toString() + "='" + hodnoty[i].toString() + "'";
      if(i < (atributy.length - 1)){
        if(zaroven != null && zaroven.booleanValue()) podminka += " and";
        else podminka += " or";
      }
    }
    if(uzivatel != null){
    	String podminkaUzivatel = " (o.uzivatel.id=" + uzivatel.getId() + " or o.uzivatel is null)";
    	if(podminka.length() > 0) podminka = "(" + podminka + ")" + " and" + podminkaUzivatel;
    	else podminka = podminkaUzivatel;
    }
    return podminka;
  }

  protected String getRazeni(Class<?> trida, String[] atributyRazeni) {
    String razeni = "";
    if(atributyRazeni.length == 0){
      if(AEntita.getSloupec(trida, "kod") != null) razeni = "order by o.kod";
    }else{
      razeni = "order by";
      for (int i = 0; i < atributyRazeni.length; i++) {
        razeni += " o." + atributyRazeni[i];
        if(i < (atributyRazeni.length - 1)) razeni += ",";
      }
    }
    return razeni;
  }
  
  public void inicializaceSetu(Set<?> set) {
    Hibernate.initialize(set);
  }

  public void inicializaceObjektu(Object objekt) {
    Hibernate.initialize(objekt);
  }
  
  public void uzavritSpojeni(boolean vse){
    if(vse) hibernate.close();
    else hibernate.closeSession();
  }
}
