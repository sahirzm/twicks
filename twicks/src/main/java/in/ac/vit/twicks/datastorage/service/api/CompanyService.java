/**
 * 
 */
package in.ac.vit.twicks.datastorage.service.api;

import in.ac.vit.twicks.entities.Company;

import java.util.List;

/**
 * @author sahir
 *
 */
public interface CompanyService {
	Company create(Company company);
	Company update(Company company);
	Company getById(Integer id);
	void delete(Company company);
	void deleteById(Integer companyId);
	
	List<Company> getAll();
}
