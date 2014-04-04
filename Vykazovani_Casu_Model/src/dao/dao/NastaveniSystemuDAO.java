package dao.dao;


/**
 * @generated
 */
public class NastaveniSystemuDAO {
  /**
   * @generated
   */
  public NastaveniSystemuDAO() {
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
  public Long create(dao.model.NastaveniSystemu object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().save(object);
    return object.getId();
  }

  /**
   * @generated
   */
  public dao.model.NastaveniSystemu read(Long id) {
    if (id == null)
      throw new IllegalArgumentException("id");
    return (dao.model.NastaveniSystemu) getSession().get(
        dao.model.NastaveniSystemu.class, id);
  }

  /**
   * @generated
   */
  public void update(dao.model.NastaveniSystemu object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().update(object);
  }

  /**
   * @generated
   */
  public void delete(dao.model.NastaveniSystemu object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().delete(object);
  }

  /**
   * @generated
   */
  public String toString() {
    return "NastaveniSystemuDAO";
  }
}