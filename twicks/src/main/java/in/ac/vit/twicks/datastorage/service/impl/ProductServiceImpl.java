/**
 * 
 */
package in.ac.vit.twicks.datastorage.service.impl;

import in.ac.vit.twicks.controllers.CurrentUser;
import in.ac.vit.twicks.datastorage.dao.ProductDao;
import in.ac.vit.twicks.datastorage.service.api.CompanyService;
import in.ac.vit.twicks.datastorage.service.api.ProductService;
import in.ac.vit.twicks.entities.Company;
import in.ac.vit.twicks.entities.Product;
import in.ac.vit.twicks.entities.UserRoles;
import in.ac.vit.twicks.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 * @author sahir
 * 
 */
@Stateless
public class ProductServiceImpl implements ProductService {

	@Inject
	private ProductDao productDao;
	@Inject
	private Validator validator;
	@Inject
	private CompanyService companyService;
	@Inject
	private CurrentUser currentUser;

	@Override
	public Product save(Product product) {
		Set<ConstraintViolation<Product>> violations = this.validator
				.validate(product);
		if (!violations.isEmpty()) {
			throw new ValidationException(new HashSet<ConstraintViolation<?>>(
					violations));
		}
		product = this.productDao.save(product);
		return product;
	}

	@Override
	public void delete(Product product) {
		this.productDao.delete(product);
	}

	@Override
	public List<Product> getAllByCompanyId(Integer companyId) {
		if (!this.currentUser.isUserRole(UserRoles.ADMIN)) {
			companyId = this.currentUser.getUser().getCompany().getId();
		}
		return this.getProductDao().getAllByCompanyId(companyId);
	}

	@Override
	public List<Product> getAllToFetch() {
		List<Company> activeCompanies = this.getCompanyService()
				.getActiveCompanies();
		List<Product> products = new ArrayList<>();
		for (Company company : activeCompanies) {
			products.addAll(this.getAllByCompanyId(company.getId()));
		}
		return products;
	}

	@Override
	public Product getById(int id) {
		return this.productDao.getById(id);
	}

	@Override
	public Product update(Product product) {
		Set<ConstraintViolation<Product>> violations = this.validator
				.validate(product);
		if (!violations.isEmpty()) {
			throw new ValidationException(new HashSet<ConstraintViolation<?>>(
					violations));
		}
		product = this.productDao.update(product);
		return product;
	}

	protected CompanyService getCompanyService() {
		return this.companyService;
	}

	protected ProductDao getProductDao() {
		return this.productDao;
	}

	@Override
	public void delete(int key) {
		this.productDao.deleteById(key);
	}

	@Override
	public int getCount() {
		if (!this.currentUser.isUserRole(UserRoles.ADMIN)) {
			int companyId = this.currentUser.getUser().getCompany().getId();
			Map<String, String> filter = new HashMap<String, String>(1);
			filter.put("company", companyId+"");
			return this.getCount(filter);
		} else
			return this.productDao.getCount().intValue();
	}

	@Override
	public List<Product> get(int first, int pageSize, String sortField,
			String sortOrder, Map<String, String> filters) {
		if (!this.currentUser.isUserRole(UserRoles.ADMIN)) {
			int companyId = this.currentUser.getUser().getCompany().getId();
			filters.put("company", companyId+"");
		}
		return this.productDao.get(first, pageSize, sortField, sortOrder,
				filters);
	}

	@Override
	public int getCount(Map<String, String> filters) {
		if (!this.currentUser.isUserRole(UserRoles.ADMIN)) {
			int companyId = this.currentUser.getUser().getCompany().getId();
			filters.put("company", companyId+"");
		}
		return this.productDao.getCount(filters);
	}

}
