package in.ac.vit.twicks.datastorage.dao.postgres;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import in.ac.vit.twicks.datastorage.dao.AbstractDaoImpl;
import in.ac.vit.twicks.datastorage.dao.StatusDao;
import in.ac.vit.twicks.search.statuses.Status;

@Stateless
public class StatusDaoImpl extends AbstractDaoImpl<Status> implements StatusDao {

	@Inject
	private EntityManager entityManager;

	@Override
	public List<Status> get(int first, int pageSize, String sortField,
			String sortOrder, Map<String, String> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public int getCount(Map<String, String> filters) {
		// TODO Auto-generated method stub
		return 0;
	}

}
