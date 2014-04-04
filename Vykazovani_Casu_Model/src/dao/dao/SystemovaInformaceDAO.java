package dao.dao;


/**
 * @generated
 */
public class SystemovaInformaceDAO {
  /**
   * @generated
   */
  public SystemovaInformaceDAO() {
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
  public Long create(dao.model.SystemovaInformace object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().save(object);
    return object.getId();
  }

  /**
   * @generated
   */
  public dao.model.SystemovaInformace read(Long id) {
    if (id == null)
      throw new IllegalArgumentException("id");
    return (dao.model.SystemovaInformace) getSession().get(
        dao.model.SystemovaInformace.class, id);
  }

  /**
   * @generated
   */
  public void update(dao.model.SystemovaInformace object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().update(object);
  }

  /**
   * @generated
   */
  public void delete(dao.model.SystemovaInformace object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().delete(object);
  }

  /**
   * @generated
   */
  public String toString() {
    return "SystemovaInformaceDAO";
  }
}