package dao.databaze;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import dao.model.NastaveniSystemu;
import dao.util.HibernateHelper;

public abstract class ADatabaze{
  
  private static final String POSLEDNI_PRIPOJENI = "posledni.pripojeni";
  private boolean kontrola = true;
  
  protected Session ctx;
  
  public ADatabaze(){
    ctx = HibernateHelper.getInstance().getSession();
  }
  
  public Object nacti(Class<?> trida, Long id) {
    zkontrolujSession();
    Object object = ctx.load(trida, id);
    return object;
  }
  
  public Object nacti(Class<?> trida, String atribut, String hodnota){
    if(kontrola) zkontrolujSession();
    Query query = ctx.createQuery("select o from " + trida.getName() + " o where o." + atribut + "='" + hodnota + "'");
    List<?> list = query.list();
    if(list != null && !list.isEmpty()) return list.get(0);
    else return null;
  }
  
  public Object nacti(Class<?> trida, Object atributy[], Object hodnoty[]){
    zkontrolujSession();
    Query query = ctx.createQuery(vytvorDotaz(trida, atributy, hodnoty));
    List<?> list = query.list();
    if(list != null && !list.isEmpty()) return list.get(0);
    else return null;
  }
  
  public void vlozUprav(Object object, Long id) {
    if(kontrola) zkontrolujSession();
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
    zkontrolujSession();
    ctx.beginTransaction();
    ctx.delete(object);
    ctx.getTransaction().commit();
  }
  
  public List<?> ziskejObjekty(Class<?> trida, Object[] atributy, Object[] hodnoty) {
    zkontrolujSession();
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

  public void inicializaceSetu(Set<?> set) {
    zkontrolujSession();
    Hibernate.initialize(set);
  }

  private void zkontrolujSession() {
    try{
      kontrola = false;
      NastaveniSystemu nastaveni = (NastaveniSystemu) nacti(NastaveniSystemu.class, "nazev", POSLEDNI_PRIPOJENI);
      if(nastaveni == null) nastaveni = new NastaveniSystemu();
      nastaveni.setNazev(POSLEDNI_PRIPOJENI);
      nastaveni.setHodnota((new Date()).toString());
      vlozUprav(nastaveni, nastaveni.getId());
      kontrola = true;
    }catch(Throwable th){
      System.out.println(new Date() + " " + th.getMessage());
      HibernateHelper.getInstance().closeSession();
      ctx = HibernateHelper.getInstance().getSession();
    }
  }
}
