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
public interface ProductService extends AbstractService<Product>{

	List<Product> getAllByCompanyId(Integer companyId);

	List<Product> getAllToFetch();

}
