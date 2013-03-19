/**
 * 
 */
package in.ac.vit.twicks.datastorage.dao.postgres;

import in.ac.vit.twicks.datastorage.dao.ProductDao;
import in.ac.vit.twicks.entities.Product;

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
public class ProductDaoImpl implements ProductDao {

	@Inject
	private EntityManager entityManager;

	@Override
	public Product create(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Product product) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer productId) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllByCompanyId(Integer companyId) {
		Query query = this.getEntityManager().createNamedQuery(
				"productsByCompanyId", Product.class);
		query.setParameter("companyId", companyId);
		return query.getResultList();
	}

	@Override
	public Product getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product update(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

}
