package dao.model;

import java.util.Iterator;

import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.Property;

import dao.util.HibernateHelper;

public abstract class AEntita implements java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 258378465050075685L;

  private static final Configuration cfg = HibernateHelper.getInstance().getConfiguration();
  private static boolean vytvoreno = false;
  
  public static Column getOmezeniSloupce(Class<?> trida, String atribut){
    if(!vytvoreno){
      cfg.buildMappings();
      vytvoreno = true;
    }
    Property p = cfg.getClassMapping(trida.getName()).getProperty(atribut);
    Iterator<?> iter = p.getColumnIterator();
    Column c = null;
    if(iter.hasNext()) c = (Column) iter.next();
    return c;
  }
}
