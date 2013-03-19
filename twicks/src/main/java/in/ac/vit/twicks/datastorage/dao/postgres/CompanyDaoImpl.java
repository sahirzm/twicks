/**
 * 
 */
package in.ac.vit.twicks.datastorage.dao.postgres;

import in.ac.vit.twicks.datastorage.dao.CompanyDao;
import in.ac.vit.twicks.entities.Company;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author sahir
 *
 */
@Stateless
public class CompanyDaoImpl implements CompanyDao {

	@Inject
	private EntityManager entityManager;

	@Override
	public Company create(Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Company company) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer companyId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Company> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getBySubscriptionDate(Date date) {
		Query query = this.getEntityManager().createNamedQuery(
				"companiesBySubDate", Company.class);
		query.setParameter("date", date);
		return query.getResultList();
	}

	@Override
	public Company update(Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

}
