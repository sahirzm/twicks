/**
 * 
 */
package in.ac.vit.twicks.datastorage.doa.postgres;

import in.ac.vit.twicks.datastorage.doa.CompanyDao;
import in.ac.vit.twicks.entities.Company;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

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

	@Override
	public Company update(Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

}
