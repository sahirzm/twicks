/**
 * 
 */
package in.ac.vit.twicks.datastorage.dao.postgres;

import in.ac.vit.twicks.datastorage.dao.AbstractDaoImpl;
import in.ac.vit.twicks.datastorage.dao.CompanyDao;
import in.ac.vit.twicks.entities.Company;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author sahir
 *
 */
@Stateless
public class CompanyDaoImpl extends AbstractDaoImpl<Company> implements CompanyDao {

	@Inject
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getBySubscriptionDate(Date date) {
		Query query = this.getEntityManager().createNamedQuery(
				"companiesBySubDate", Company.class);
		query.setParameter("date", date);
		return query.getResultList();
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public List<Company> get(int first, int pageSize, String sortField,
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
