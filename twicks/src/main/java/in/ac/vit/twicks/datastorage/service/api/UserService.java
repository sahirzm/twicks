/**
 * 
 */
package in.ac.vit.twicks.datastorage.service.api;

import in.ac.vit.twicks.entities.User;

/**
 * @author sahir
 * 
 */
public interface UserService extends AbstractService<User> {

	User authenticate(String username, String password);

	User changePassword(int id, String oldPassword, String newPassword) throws Exception;

}
