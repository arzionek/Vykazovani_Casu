package dao.model;

import java.util.Date;
import java.util.Iterator;

import org.hibernate.MappingException;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.Property;
import org.hibernate.type.DoubleType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.hibernate.type.Type;

import dao.util.HibernateHelper;

public abstract class AEntita implements java.io.Serializable {

  private static final long serialVersionUID = 258378465050075685L;

  private static final Configuration cfg = HibernateHelper.getInstance().getConfiguration();
  private static boolean vytvoreno = false;
  
  public static Column getSloupec(Class<?> trida, String atribut){
    if(!vytvoreno){
      cfg.buildMappings();
      vytvoreno = true;
    }
    Iterator<?> iter = null;
    try{
      Property p = cfg.getClassMapping(trida.getName()).getProperty(atribut);
      iter = p.getColumnIterator();
    }catch(MappingException e){}
    Column c = null;
    if(iter != null && iter.hasNext()) c = (Column) iter.next();
    return c;
  }
  
  public static Class<?> getTypSloupce(Class<?> trida, String atribut){
    Type typ = HibernateHelper.getInstance().getFactory().getClassMetadata(trida).getPropertyType(atribut);
    if(typ instanceof StringType) return String.class;
    else if(typ instanceof DoubleType) return Double.class;
    else if(typ instanceof TimestampType) return Date.class;
    return Object.class;
  }
}
