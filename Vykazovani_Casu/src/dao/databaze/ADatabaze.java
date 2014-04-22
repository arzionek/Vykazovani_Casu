package dao.databaze;

import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    T object = null;
    Session session = null;
    try{
      session = hibernate.getSession();
      Query query = session.createQuery("select o from " + trida.getName() + " o where o." + atribut + "='" + hodnota + "'");
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
  
  public <T> T nacti(Class<T> trida, Object atributy[], Object hodnoty[]){
    return nacti(trida, atributy, hodnoty, false);
  }
  
  public <T> T nacti(Class<T> trida, Object atributy[], Object hodnoty[], boolean zaroven){
    T object = null;
    Session session = null;
    try{
      session = hibernate.getSession();
      Query query = session.createQuery(vytvorDotaz(trida, atributy, hodnoty, zaroven));
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
  
  @SuppressWarnings("unchecked")
  public <T> List<T> ziskejObjekty(Class<T> trida, Object[] atributy, Object[] hodnoty) {
    List<T> list = null;
    Session session = null;
    try{
      session = hibernate.getSession();
      Query query = session.createQuery(vytvorDotaz(trida, atributy, hodnoty, false));
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

  private String vytvorDotaz(Class<?> trida, Object[] atributy, Object[] hodnoty, boolean zaroven) {
    String dotaz = "select o from " + trida.getName() + " o where";
    for (int i = 0; i < atributy.length; i++) {
      String podminka = " o." + atributy[i].toString() + "='" + hodnoty[i].toString() + "'";
      if(i > 0){
        if(zaroven) podminka = " and" + podminka;
        else podminka = " or" + podminka;
      }
      dotaz += podminka;
    }
    return dotaz;
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
