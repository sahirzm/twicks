package in.ac.vit.twicks.datastorage.dao.postgres;

import in.ac.vit.twicks.datastorage.dao.AbstractDaoImpl;
import in.ac.vit.twicks.datastorage.dao.UserDao;
import in.ac.vit.twicks.entities.User;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

	@Inject
	private EntityManager entityManager;

	@Override
	public User changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public User getByUsername(String username) {
		return this.getEntityManager()
				.createNamedQuery("user.findByUsername", User.class)
				.setParameter("username", username).getSingleResult();
	}

	@Override
	public List<User> get(int first, int pageSize, String sortField,
			String sortOrder, Map<String, String> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount(Map<String, String> filters) {
		// TODO Auto-generated method stub
		return 0;
	}

}
