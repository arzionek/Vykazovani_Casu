package dao.dao;


/**
 * @generated
 */
public class UzivatelDAO {
  /**
   * @generated
   */
  public UzivatelDAO() {
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
  public Long create(dao.model.Uzivatel object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().save(object);
    return object.getId();
  }

  /**
   * @generated
   */
  public dao.model.Uzivatel read(Long id) {
    if (id == null)
      throw new IllegalArgumentException("id");
    return (dao.model.Uzivatel) getSession().get(dao.model.Uzivatel.class, id);
  }

  /**
   * @generated
   */
  public void update(dao.model.Uzivatel object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().update(object);
  }

  /**
   * @generated
   */
  public void delete(dao.model.Uzivatel object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().delete(object);
  }

  /**
   * @generated
   */
  public String toString() {
    return "UzivatelDAO";
  }
}