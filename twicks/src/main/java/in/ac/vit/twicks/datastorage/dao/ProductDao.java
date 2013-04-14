/**
 * 
 */
package in.ac.vit.twicks.datastorage.dao;

import java.util.List;

import in.ac.vit.twicks.entities.Product;

/**
 * @author sahir
 * 
 */
public interface ProductDao extends AbstractDao<Product> {

	List<Product> getAllByCompanyId(Integer companyId);

}
