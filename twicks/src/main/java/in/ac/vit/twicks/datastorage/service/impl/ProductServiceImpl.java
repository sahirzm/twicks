/**
 * 
 */
package in.ac.vit.twicks.datastorage.service.impl;

import in.ac.vit.twicks.datastorage.doa.ProductDao;
import in.ac.vit.twicks.datastorage.service.api.ProductService;
import in.ac.vit.twicks.entities.Product;
import in.ac.vit.twicks.entities.Production;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author sahir
 *
 */
@Stateless
public class ProductServiceImpl implements ProductService {

	@Inject
	@Production
	private ProductDao productDao;

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

	protected ProductDao getProductDao() {
		return this.productDao;
	}

}
