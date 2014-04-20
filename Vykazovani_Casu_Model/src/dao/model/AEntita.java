package dao.model;

import java.util.Iterator;

import org.hibernate.mapping.Column;
import org.hibernate.mapping.Property;

public abstract class AEntita implements java.io.Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 258378465050075685L;

  public static Column getOmezeniSloupce(Class<?> trida, String atribut){
    Property p = (new org.hibernate.cfg.Configuration())
      .addClass(trida)
      .getClassMapping(trida.getName())
      .getProperty(atribut);
    Iterator<?> iter = p.getColumnIterator();
    Column c = null;
    if(iter.hasNext()) c = (Column) iter.next();
    return c;
  }
}
