/**
 * 
 */
package in.ac.vit.twicks.datastorage.service.impl;

/**
 * 
 * @author sahir maredia
 */

import in.ac.vit.twicks.datastorage.doa.CompanyDao;
import in.ac.vit.twicks.datastorage.service.api.CompanyService;
import in.ac.vit.twicks.entities.Company;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CompanyServiceImpl implements CompanyService {

	@Inject
	private CompanyDao companyDao;

	@Override
	public Company create(Company company) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(Company company) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer companyId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Company> getActiveCompanies() {
		return this.getCompanyDao().getBySubscriptionDate(new Date());
	}

	@Override
	public List<Company> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company update(Company company) {
		// TODO Auto-generated method stub
		return null;
	}

	protected CompanyDao getCompanyDao() {
		return this.companyDao;
	}

}
