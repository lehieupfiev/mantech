/**
 * Written by Tien Nguyen <lilylnx@users.sf.net>
 * FREE FOR ALL BUT DOES NOT MEAN THERE IS NO PRICE.
 */
package mantech.dao;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.validator.EmailValidator;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import net.lilylnx.springnet.core.hibernate.HibernateGenericDAO;

import mantech.domain.User;
import mantech.repository.UserRepository;

/**
 * 
 * @author Tien Nguyen
 * @version $Id: UserDAO.java,v 1.0 2011/09/07 2:15:44 lilylnx Exp $
 */
public class UserDAO extends HibernateGenericDAO<User> implements UserRepository {

  public UserDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  /* (non-Javadoc)
   * @see mantech.repository.UserRepository#getPasswordByUsername(java.lang.String)
   */
  @Override
  public String getPasswordByUsername(String username) {
    return (String)this.session().createCriteria(persistClass)
        .add(Restrictions.eq("username", username))
        .setProjection(Projections.property("password"))
        .setComment("userDAO.getPasswordByUsername")
        .uniqueResult();
  }

  /* (non-Javadoc)
   * @see mantech.repository.UserRepository#getPasswordByEmail(java.lang.String)
   */
  @Override
  public String getPasswordByEmail(String email) {
    return (String)this.session().createCriteria(persistClass)
        .add(Restrictions.eq("email", email))
        .setProjection(Projections.property("password"))
        .setComment("userDAO.getPasswordByEmail")
        .uniqueResult();
  }

  /* (non-Javadoc)
   * @see mantech.repository.UserRepository#getByUsername(java.lang.String)
   */
  @Override
  public User getByUsername(String username) {
    return (User)this.session().createCriteria(persistClass)
        .add(Restrictions.eq("username", username))
        .setComment("userDAO.getByUsername")
        .uniqueResult();
  }

  /* (non-Javadoc)
   * @see mantech.repository.UserRepository#getByEmail(java.lang.String)
   */
  @Override
  public User getByEmail(String email) {
    return (User)this.session().createCriteria(persistClass)
        .add(Restrictions.eq("email", email))
        .setComment("userDAO.getByEmail")
        .uniqueResult();
  }
  

  @SuppressWarnings("unchecked")
  @Override
  public List<User> getTechnicianFree() {
    Criteria crit = session().createCriteria(persistClass, "u");
    crit.createAlias("u.role", "r").add(Restrictions.eq("r.id", (byte)2));
    crit.add(Restrictions.eq("u.status",""));
    return crit.list();
  }

  /*
   * (non-Javadoc)
   * @see mantech.repository.UserRepository#getUserByRole(int)
   */
  @SuppressWarnings("unchecked")
  @Override
  public List<User> getUserByRole(int id) {
    return session().createQuery("select u from User u inner join u.role r where r.id = :id")
        .setInteger("id", id).list();
  }

  /*s
   * (non-Javadoc)
   * @see mantech.repository.UserRepository#getUserByRole(java.lang.String)
   */
  @SuppressWarnings("unchecked")
  @Override
  public List<User> getUserByRole(String name) {
    return session().createQuery("select u from User u inner join u.role r where r.name = :name")
        .setString("name", name).list();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<User> getUsers(int... ids) {
    return session().createQuery("from User u where u.id in (:ids)")
        .setParameterList("ids", ArrayUtils.toObject(ids))
        .list();
  }

  /* (non-Javadoc)
   * @see mantech.repository.UserRepository#searchByUsername(java.lang.String)
   */
  @SuppressWarnings("unchecked")
  @Override
  public List<User> search(String username, String departName) {
    Criteria crit = session().createCriteria(persistClass, "u");
    if (username != null) {
      crit.add(Restrictions.ilike("username", "%" + username + "%"));
    }
    if (departName != null) {
      crit.createAlias("u.department", "d").add(Restrictions.ilike("d.name", "%" + departName + "%"));
    }
    return crit.list();
  }
  
  @Override
  public boolean isExistUser(int id) {
    return ((Long)session().createQuery("select count(u) from User u where u.id = :id")
        .setInteger("id", id).uniqueResult()).byteValue() > 0;
  }
  
  @Override
  public boolean isExistUser(String unameOrEmail) {
    String hql = "select count(u) from User u where u.{field} = :value";
    if (EmailValidator.getInstance().isValid(unameOrEmail)) {
      hql = hql.replace("{field}", "email");
    }
    else {
      hql = hql.replace("{field}", "username");
    }
    return ((Long)session().createQuery(hql).setString("value", unameOrEmail)
        .uniqueResult()).byteValue() > 0;
  }

}
