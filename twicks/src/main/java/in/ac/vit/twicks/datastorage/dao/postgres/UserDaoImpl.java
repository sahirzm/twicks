package in.ac.vit.twicks.datastorage.dao.postgres;

import in.ac.vit.twicks.datastorage.dao.UserDao;
import in.ac.vit.twicks.entities.User;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class UserDaoImpl implements UserDao {

	@Inject
	private EntityManager entityManager;

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

	protected EntityManager getEntityManager() {
		return this.entityManager;
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

	@Override
	public User getByUsername(String username) {
		return this.getEntityManager()
				.createNamedQuery("user.findByUsername", User.class)
				.setParameter("username", username).getSingleResult();
	}

}
