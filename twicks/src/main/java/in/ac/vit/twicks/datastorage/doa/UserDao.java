/**
 * 
 */
package in.ac.vit.twicks.datastorage.doa;

import java.util.List;

import in.ac.vit.twicks.entities.User;

/**
 * @author sahir
 *
 */
public interface UserDao {

	User create(User user);
	User update(User user);
	User getById(Integer id);
	void delete(User user);
	void deleteById(Integer id);
	
	boolean isAuthenticate(String username, String password);
	User changePassword(String oldPassword, String newPassword);
		
	List<User> getAll();
}
