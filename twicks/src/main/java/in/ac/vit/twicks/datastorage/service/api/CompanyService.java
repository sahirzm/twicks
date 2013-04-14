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
public interface CompanyService extends AbstractService<Company> {
	
	List<Company> getActiveCompanies();

}
