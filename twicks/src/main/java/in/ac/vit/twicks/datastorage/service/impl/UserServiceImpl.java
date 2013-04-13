/**
 * 
 */
package in.ac.vit.twicks.datastorage.service.impl;

import in.ac.vit.twicks.datastorage.dao.UserDao;
import in.ac.vit.twicks.datastorage.service.api.UserService;
import in.ac.vit.twicks.entities.User;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.jboss.security.auth.spi.Util;

/**
 * @author sahir
 * 
 */
@Stateless
public class UserServiceImpl implements UserService {

	@Inject
	private UserDao userDao;

	@Override
	public User changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User create(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User authenticate(String username, String password) {
		User user = this.getUserDao().getByUsername(username);
		if (user != null) {
			if (!user.getPassword()
					.equals(Util.createPasswordHash("MD5", "hex", null, null,
							password))) {
				user = null;
			}
		}
		return user;
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	protected UserDao getUserDao() {
		return this.userDao;
	}

}
