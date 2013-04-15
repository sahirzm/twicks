/**
 * 
 */
package in.ac.vit.twicks.datastorage.service.impl;

import in.ac.vit.twicks.controllers.CurrentUser;
import in.ac.vit.twicks.datastorage.dao.UserDao;
import in.ac.vit.twicks.datastorage.service.api.UserService;
import in.ac.vit.twicks.entities.User;
import in.ac.vit.twicks.entities.UserRoles;
import in.ac.vit.twicks.exceptions.ValidationException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.jboss.security.auth.spi.Util;

/**
 * @author sahir
 * 
 */
@Stateless
public class UserServiceImpl implements UserService {

	@Inject
	private UserDao userDao;
	@Inject
	private Validator validator;
	@Inject
	private CurrentUser currentUser;

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

	protected UserDao getUserDao() {
		return this.userDao;
	}

	@Override
	public User save(User user) {
		Set<ConstraintViolation<User>> violations = this.validator
				.validate(user);
		ValidationException exp = new ValidationException(
				new HashSet<ConstraintViolation<?>>(violations));

		if (user.getUsername() != null) {
			User oldUser = this.getUserDao().getByUsername(user.getUsername());
			if (oldUser != null) {
				exp.addError("username", "username already taken");
			}
		}
		if (user.getRole() != null && user.getRole().equals(UserRoles.CUSTOMER)) {
			if (user.getCompany() == null) {
				exp.addError("company", "cannot be empty for customer");
			}
		}
		if (!exp.isEmpty()) {
			throw exp;
		}
		user = this.getUserDao().save(user);
		return user;
	}

	@Override
	public User update(User user) {
		Set<ConstraintViolation<User>> violations = this.validator
				.validate(user);
		ValidationException exp = new ValidationException(
				new HashSet<ConstraintViolation<?>>(violations));

		if (user.getRole() != null && user.getRole().equals(UserRoles.CUSTOMER)) {
			if (user.getCompany() == null) {
				exp.addError("company", "cannot be empty for customer");
			}
		}
		if (!exp.isEmpty()) {
			throw exp;
		}
		user = this.getUserDao().update(user);
		return user;
	}

	@Override
	public void delete(User user) {
		this.getUserDao().delete(user);
	}

	@Override
	public void delete(int key) {
		if (key > 0) {
			this.getUserDao().deleteById(key);
		}
	}

	@Override
	public int getCount() {
		return this.getUserDao().getCount().intValue();
	}

	@Override
	public List<User> get(int first, int pageSize, String sortField,
			String sortOrder, Map<String, String> filters) {
		return this.getUserDao().get(first, pageSize, sortField, sortOrder,
				filters);
	}

	@Override
	public int getCount(Map<String, String> filters) {
		return this.getUserDao().getCount(filters);
	}

	@Override
	public User getById(int id) {
		return this.getUserDao().getById(id);
	}

	@Override
	public User changePassword(int id, String oldPassword, String newPassword)
			throws Exception {
		User user = this.getById(id);
		if (user != null) {
			String oldHash = Util.createPasswordHash("MD5", "hex", null, null,
					oldPassword);
			if (oldPassword.equals(oldHash)) {
				user.setPassword(Util.createPasswordHash("MD5", "hex", null,
						null, newPassword));
				return this.update(user);
			} else {
				throw new Exception("Password mismatched");
			}
		} else {
			return null;
		}
	}

}