/**
 * 
 */
package in.ac.vit.twicks.datastorage.service.impl;

/**
 * 
 * @author sahir maredia
 */

import in.ac.vit.twicks.datastorage.dao.CompanyDao;
import in.ac.vit.twicks.datastorage.service.api.CompanyService;
import in.ac.vit.twicks.entities.Company;
import in.ac.vit.twicks.exceptions.ValidationException;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

@Stateless
@RolesAllowed({ "admin" })
public class CompanyServiceImpl implements CompanyService {
	@Resource
	private SessionContext context;
	@Inject
	private CompanyDao companyDao;
	@Inject
	private Validator validator;

	@Override
	public List<Company> getActiveCompanies() {
		return this.getCompanyDao().getBySubscriptionDate(new Date());
	}

	protected CompanyDao getCompanyDao() {
		return this.companyDao;
	}

	@Override
	public Company save(Company company) {
		Set<ConstraintViolation<Company>> violations = this.validator
				.validate(company);
		if (!violations.isEmpty()) {
			throw new ValidationException(new HashSet<ConstraintViolation<?>>(
					violations));
		}
		company = this.getCompanyDao().save(company);
		return company;
	}

	@Override
	public Company update(Company company) {
		Set<ConstraintViolation<Company>> violations = this.validator
				.validate(company);
		if (!violations.isEmpty()) {
			throw new ValidationException(new HashSet<ConstraintViolation<?>>(
					violations));
		}
		company = this.getCompanyDao().update(company);
		return company;
	}

	@Override
	public void delete(Company company) {
		this.getCompanyDao().delete(company);
	}

	@Override
	public void delete(int key) {
		this.getCompanyDao().deleteById(key);
	}

	@Override
	public int getCount() {
		Long value = this.getCompanyDao().getCount();
		return value.intValue();
	}

	@Override
	public List<Company> get(int first, int pageSize, String sortField,
			String sortOrder, Map<String, String> filters) {
		return this.getCompanyDao().get(first, pageSize, sortField, sortOrder,
				filters);
	}

	@Override
	public int getCount(Map<String, String> filters) {
		return this.getCompanyDao().getCount(filters);
	}

	@Override
	public Company getById(int id) {
		return this.getCompanyDao().getById(id);
	}

}
