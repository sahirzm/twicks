/**
 * 
 */
package in.ac.vit.twicks.datastorage.service.api;

import in.ac.vit.twicks.entities.Product;

import java.util.List;

/**
 * @author sahir
 *
 */
public interface ProductService {

	Product create(Product product);
	void delete(Product product);
	void deleteById(Integer productId);
	List<Product> getAll();
	List<Product> getAllByCompanyId(Integer companyId);

	List<Product> getAllToFetch();

	Product getById(Integer id);

	Product update(Product product);

}
