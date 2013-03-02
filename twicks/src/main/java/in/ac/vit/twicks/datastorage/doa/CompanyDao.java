/**
 * 
 */
package in.ac.vit.twicks.datastorage.doa;

import in.ac.vit.twicks.entities.Company;

import java.util.List;

/**
 * @author sahir
 *
 */
public interface CompanyDao {
	Company create(Company company);
	Company update(Company company);
	Company getById(Integer id);
	void delete(Company company);
	void deleteById(Integer companyId);
	
	List<Company> getAll();
}
