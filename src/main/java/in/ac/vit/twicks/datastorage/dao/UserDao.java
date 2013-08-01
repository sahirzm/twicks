/**
 * 
 */
package in.ac.vit.twicks.datastorage.dao;

import in.ac.vit.twicks.entities.User;

/**
 * @author sahir
 *
 */
public interface UserDao extends AbstractDao<User> {

	User getByUsername(String username);
}
