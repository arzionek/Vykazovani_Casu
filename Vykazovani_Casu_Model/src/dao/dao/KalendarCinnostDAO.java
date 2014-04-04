package dao.dao;


/**
 * @generated
 */
public class KalendarCinnostDAO {
  /**
   * @generated
   */
  public KalendarCinnostDAO() {
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
  public Long create(dao.model.KalendarCinnost object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().save(object);
    return object.getId();
  }

  /**
   * @generated
   */
  public dao.model.KalendarCinnost read(Long id) {
    if (id == null)
      throw new IllegalArgumentException("id");
    return (dao.model.KalendarCinnost) getSession().get(
        dao.model.KalendarCinnost.class, id);
  }

  /**
   * @generated
   */
  public void update(dao.model.KalendarCinnost object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().update(object);
  }

  /**
   * @generated
   */
  public void delete(dao.model.KalendarCinnost object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().delete(object);
  }

  /**
   * @generated
   */
  public String toString() {
    return "KalendarCinnostDAO";
  }
}