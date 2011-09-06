/**
 * Written by Tien Nguyen <lilylnx@users.sf.net>
 * FREE FOR ALL BUT DOES NOT MEAN THERE IS NO PRICE.
 */
package mantech.repository;

import net.lilylnx.springnet.repository.Repository;

import mantech.domain.User;

/**
 * 
 * @author Tien Nguyen
 * @version $Id: UserRepository.java,v 1.0 2011/06/28 16:34:49 lilylnx Exp $
 */
public interface UserRepository extends Repository<User> {
  
  User getByUsername(String username);
  User validateUser(String username, String password);

}