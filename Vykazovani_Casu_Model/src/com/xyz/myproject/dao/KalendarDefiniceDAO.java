package com.xyz.myproject.dao;


/**
 * @generated
 */
public class KalendarDefiniceDAO {
  /**
   * @generated
   */
  public KalendarDefiniceDAO() {
  }

  /**
   * @generated
   */
  private org.hibernate.Session getSession() {
    return com.xyz.myproject.util.HibernateHelper.getInstance().getSession();
  }

  /**
   * @generated
   */
  public Long create(com.xyz.myproject.model.KalendarDefinice object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().save(object);
    return object.getId();
  }

  /**
   * @generated
   */
  public com.xyz.myproject.model.KalendarDefinice read(Long id) {
    if (id == null)
      throw new IllegalArgumentException("id");
    return (com.xyz.myproject.model.KalendarDefinice) getSession().get(
        com.xyz.myproject.model.KalendarDefinice.class, id);
  }

  /**
   * @generated
   */
  public void update(com.xyz.myproject.model.KalendarDefinice object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().update(object);
  }

  /**
   * @generated
   */
  public void delete(com.xyz.myproject.model.KalendarDefinice object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().delete(object);
  }

  /**
   * @generated
   */
  public String toString() {
    return "KalendarDefiniceDAO";
  }
}