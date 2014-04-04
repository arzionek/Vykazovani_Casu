package dao.dao;


/**
 * @generated
 */
public class KalendarDAO {
  /**
   * @generated
   */
  public KalendarDAO() {
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
  public Long create(dao.model.Kalendar object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().save(object);
    return object.getId();
  }

  /**
   * @generated
   */
  public dao.model.Kalendar read(Long id) {
    if (id == null)
      throw new IllegalArgumentException("id");
    return (dao.model.Kalendar) getSession().get(
        dao.model.Kalendar.class, id);
  }

  /**
   * @generated
   */
  public void update(dao.model.Kalendar object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().update(object);
  }

  /**
   * @generated
   */
  public void delete(dao.model.Kalendar object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().delete(object);
  }

  /**
   * @generated
   */
  public String toString() {
    return "KalendarDAO";
  }
}