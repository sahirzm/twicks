/**
 * 
 */
package in.ac.vit.twicks.datastorage.doa.postgres;

import in.ac.vit.twicks.datastorage.doa.ProductDao;
import in.ac.vit.twicks.entities.Product;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

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

	@Override
	public List<Product> getAllByCompanyId(Integer companyId) {
		// TODO Auto-generated method stub
		return null;
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
