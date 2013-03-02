/**
 * 
 */
package in.ac.vit.twicks.datastorage.doa;

import in.ac.vit.twicks.entities.Company;

import java.util.Date;
import java.util.List;

/**
 * @author sahir
 *
 */
public interface CompanyDao {
	Company create(Company company);
	void delete(Company company);
	void deleteById(Integer companyId);
	List<Company> getAll();

	Company getById(Integer id);

	List<Company> getBySubscriptionDate(Date date);

	Company update(Company company);
}
