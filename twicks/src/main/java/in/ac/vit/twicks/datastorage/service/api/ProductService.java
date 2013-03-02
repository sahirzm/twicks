/**
 * 
 */
package in.ac.vit.twicks.datastorage.service.api;

import java.util.List;

import in.ac.vit.twicks.entities.Product;

/**
 * @author sahir
 *
 */
public interface ProductService {

	Product create(Product product);
	Product update(Product product);
	Product getById(Integer id);
	void delete(Product product);
	void deleteById(Integer productId);
	
	List<Product> getAllByCompanyId(Integer companyId);
		
}
