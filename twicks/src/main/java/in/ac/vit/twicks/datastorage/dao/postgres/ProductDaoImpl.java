/**
 * 
 */
package in.ac.vit.twicks.datastorage.dao.postgres;

import in.ac.vit.twicks.datastorage.dao.AbstractDaoImpl;
import in.ac.vit.twicks.datastorage.dao.ProductDao;
import in.ac.vit.twicks.entities.Product;

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
public class ProductDaoImpl extends AbstractDaoImpl<Product> implements ProductDao {

	@Inject
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllByCompanyId(Integer companyId) {
		Query query = this.getEntityManager().createNamedQuery(
				"productsByCompanyId", Product.class);
		query.setParameter("companyId", companyId);
		return query.getResultList();
	}

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public List<Product> get(int first, int pageSize, String sortField,
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
