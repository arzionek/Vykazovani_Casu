package dao.dao;


/**
 * @generated
 */
public class SvatekDAO {
  /**
   * @generated
   */
  public SvatekDAO() {
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
  public Long create(dao.model.Svatek object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().save(object);
    return object.getId();
  }

  /**
   * @generated
   */
  public dao.model.Svatek read(Long id) {
    if (id == null)
      throw new IllegalArgumentException("id");
    return (dao.model.Svatek) getSession().get(dao.model.Svatek.class, id);
  }

  /**
   * @generated
   */
  public void update(dao.model.Svatek object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().update(object);
  }

  /**
   * @generated
   */
  public void delete(dao.model.Svatek object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().delete(object);
  }

  /**
   * @generated
   */
  public String toString() {
    return "SvatekDAO";
  }
}