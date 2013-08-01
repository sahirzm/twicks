/**
 * 
 */
package in.ac.vit.twicks.datastorage.dao;

import in.ac.vit.twicks.entities.Company;

import java.util.Date;
import java.util.List;

/**
 * @author sahir
 * 
 */
public interface CompanyDao extends AbstractDao<Company> {

	public List<Company> getBySubscriptionDate(Date date);

}
