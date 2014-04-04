package dao.dao;


/**
 * @generated
 */
public class CinnostDAO {
  /**
   * @generated
   */
  public CinnostDAO() {
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
  public Long create(dao.model.Cinnost object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().save(object);
    return object.getId();
  }

  /**
   * @generated
   */
  public dao.model.Cinnost read(Long id) {
    if (id == null)
      throw new IllegalArgumentException("id");
    return (dao.model.Cinnost) getSession().get(
        dao.model.Cinnost.class, id);
  }

  /**
   * @generated
   */
  public void update(dao.model.Cinnost object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().update(object);
  }

  /**
   * @generated
   */
  public void delete(dao.model.Cinnost object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().delete(object);
  }

  /**
   * @generated
   */
  public String toString() {
    return "CinnostDAO";
  }
}