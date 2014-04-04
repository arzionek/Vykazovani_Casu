package dao.dao;


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
    return dao.util.HibernateHelper.getInstance().getSession();
  }

  /**
   * @generated
   */
  public Long create(dao.model.KalendarDefinice object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().save(object);
    return object.getId();
  }

  /**
   * @generated
   */
  public dao.model.KalendarDefinice read(Long id) {
    if (id == null)
      throw new IllegalArgumentException("id");
    return (dao.model.KalendarDefinice) getSession().get(
        dao.model.KalendarDefinice.class, id);
  }

  /**
   * @generated
   */
  public void update(dao.model.KalendarDefinice object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().update(object);
  }

  /**
   * @generated
   */
  public void delete(dao.model.KalendarDefinice object) {
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