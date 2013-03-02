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
	void delete(Company company);
	void deleteById(Integer companyId);
	List<Company> getActiveCompanies();
	List<Company> getAll();

	Company getById(Integer id);

	Company update(Company company);
}
