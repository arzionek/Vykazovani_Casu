package dao.dao;


/**
 * @generated
 */
public class SablonaVykazDAO {
  /**
   * @generated
   */
  public SablonaVykazDAO() {
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
  public Long create(dao.model.SablonaVykaz object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().save(object);
    return object.getId();
  }

  /**
   * @generated
   */
  public dao.model.SablonaVykaz read(Long id) {
    if (id == null)
      throw new IllegalArgumentException("id");
    return (dao.model.SablonaVykaz) getSession().get(
        dao.model.SablonaVykaz.class, id);
  }

  /**
   * @generated
   */
  public void update(dao.model.SablonaVykaz object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().update(object);
  }

  /**
   * @generated
   */
  public void delete(dao.model.SablonaVykaz object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().delete(object);
  }

  /**
   * @generated
   */
  public String toString() {
    return "SablonaVykazDAO";
  }
}