package dao.dao;


/**
 * @generated
 */
public class PracovniPomerDAO {
  /**
   * @generated
   */
  public PracovniPomerDAO() {
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
  public Long create(dao.model.PracovniPomer object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().save(object);
    return object.getId();
  }

  /**
   * @generated
   */
  public dao.model.PracovniPomer read(Long id) {
    if (id == null)
      throw new IllegalArgumentException("id");
    return (dao.model.PracovniPomer) getSession().get(
        dao.model.PracovniPomer.class, id);
  }

  /**
   * @generated
   */
  public void update(dao.model.PracovniPomer object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().update(object);
  }

  /**
   * @generated
   */
  public void delete(dao.model.PracovniPomer object) {
    if (object == null)
      throw new IllegalArgumentException("object");
    getSession().delete(object);
  }

  /**
   * @generated
   */
  public String toString() {
    return "PracovniPomerDAO";
  }
}