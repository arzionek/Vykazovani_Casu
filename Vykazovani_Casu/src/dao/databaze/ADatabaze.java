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
    if(list != null && list.isEmpty()) return list.get(0);
    else return null;
  }
  
  public Object nacti(Class<?> trida, Object atributy[], Object hodnoty[]){
    String dotaz = "select o from " + trida.getName() + " o where";
    for (int i = 0; i < atributy.length; i++) {
      String podminka = " o." + atributy[i].toString() + "='" + hodnoty[i].toString() + "'";
      if(i > 0) podminka = " and" + podminka;
      dotaz += podminka;
    }
    Query query = ctx.createQuery(dotaz);
    List<?> list = query.list();
    if(list != null && !list.isEmpty()) return list.get(0);
    else return null;
  }
  
  

}
