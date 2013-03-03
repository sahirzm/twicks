/**
 * 
 */
package in.ac.vit.twicks.datastorage.service.impl;

import in.ac.vit.twicks.datastorage.doa.UserDao;
import in.ac.vit.twicks.datastorage.service.api.UserService;
import in.ac.vit.twicks.entities.User;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

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
	public boolean isAuthenticate(String username, String password) {
		// TODO Auto-generated method stub
		return false;
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
