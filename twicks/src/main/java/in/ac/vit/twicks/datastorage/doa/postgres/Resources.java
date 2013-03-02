/**
 * This class is used to inject resources using CDI
 * @author sahir maredia
 * 
 */

package in.ac.vit.twicks.datastorage.doa.postgres;

import in.ac.vit.twicks.datastorage.doa.CompanyDao;
import in.ac.vit.twicks.datastorage.doa.ProductDao;
import in.ac.vit.twicks.datastorage.doa.ResultDao;
import in.ac.vit.twicks.datastorage.doa.UserDao;
import in.ac.vit.twicks.entities.Production;

import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resources {

	@Produces
	@PersistenceContext
	private EntityManager entityManager;

	@Produces
	@Production
	public CompanyDao getCompanyDao(@New CompanyDaoImpl companyDao) {
		return companyDao;
	}

	@Produces
	@Production
	public ProductDao getProductDao(@New ProductDaoImpl productDao) {
		return productDao;
	}

	@Produces
	@Production
	public ResultDao getResultDao(@New ResultDaoImpl resultDao) {
		return resultDao;
	}

	@Produces
	@Production
	public UserDao getUserDao(@New UserDaoImpl userDao) {
		return userDao;
	}
}
