/**
 * Written by Tien Nguyen <lilylnx@users.sf.net>
 * FREE FOR ALL BUT DOES NOT MEAN THERE IS NO PRICE.
 */
package mantech.dao;

import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.validator.EmailValidator;
import org.hibernate.SessionFactory;

import mantech.domain.User;
import mantech.repository.UserRepository;

import net.lilylnx.springnet.core.hibernate.HibernateGenericDAO;

/**
 * 
 * @author Tien Nguyen
 * @version $Id: UserDAO.java,v 1.0 2011/09/07 2:15:44 lilylnx Exp $
 */
public class UserDAO extends HibernateGenericDAO<User> implements UserRepository {

  public UserDAO(SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  /*
   * (non-Javadoc)
   * @see mantech.repository.UserRepository#getByUsername(java.lang.String)
   */
  @Override
  public User getByUsername(String username) {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * @see mantech.repository.UserRepository#validateUser(java.lang.String, java.lang.String)
   */
  @Override
  public User validateUser(String username, String password) {
    // TODO Auto-generated method stub
    return null;
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
