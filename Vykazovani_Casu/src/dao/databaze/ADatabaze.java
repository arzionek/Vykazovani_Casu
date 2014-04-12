package dao.databaze;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import dao.util.HibernateHelper;

public abstract class ADatabaze{
  
  protected Session ctx;
  
  public ADatabaze(){
    ctx = HibernateHelper.getInstance().getSession();
  }
  
  public Object nacti(Class<?> trida, Long id) {
    Object object = ctx.load(trida, id);
    return object;
  }
  
  public Object nacti(Class<?> trida, String atribut, String hodnota){
    Query query = ctx.createQuery("select o from " + trida.getName() + " o where o." + atribut + "='" + hodnota + "'");
    List<?> list = query.list();
    if(list != null && !list.isEmpty()) return list.get(0);
    else return null;
  }
  
  public Object nacti(Class<?> trida, Object atributy[], Object hodnoty[]){
    Query query = ctx.createQuery(vytvorDotaz(trida, atributy, hodnoty));
    List<?> list = query.list();
    if(list != null && !list.isEmpty()) return list.get(0);
    else return null;
  }
  
  public void vlozUprav(Object object, Long id) {
    if(id == null){
      ctx.beginTransaction();
      ctx.save(object);
      ctx.getTransaction().commit();
    }else{
      ctx.beginTransaction();
      ctx.update(object);
      ctx.getTransaction().commit();
    }
  }
  
  public void smaz(Object object) {
    ctx.beginTransaction();
    ctx.delete(object);
    ctx.getTransaction().commit();
  }
  
  public List<?> ziskejObjekty(Class<?> trida, Object[] atributy, Object[] hodnoty) {
    Query query = ctx.createQuery(vytvorDotaz(trida, atributy, hodnoty));
    List<?> list = query.list();
    return list;
  }

  private String vytvorDotaz(Class<?> trida, Object[] atributy, Object[] hodnoty) {
    String dotaz = "select o from " + trida.getName() + " o where";
    for (int i = 0; i < atributy.length; i++) {
      String podminka = " o." + atributy[i].toString() + "='" + hodnoty[i].toString() + "'";
      if(i > 0) podminka = " and" + podminka;
      dotaz += podminka;
    }
    return dotaz;
  }

}
