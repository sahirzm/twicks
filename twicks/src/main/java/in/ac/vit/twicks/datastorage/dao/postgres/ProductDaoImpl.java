/**
 * 
 */
package in.ac.vit.twicks.datastorage.dao.postgres;

import in.ac.vit.twicks.datastorage.dao.AbstractDaoImpl;
import in.ac.vit.twicks.datastorage.dao.ProductDao;
import in.ac.vit.twicks.entities.Company;
import in.ac.vit.twicks.entities.Company_;
import in.ac.vit.twicks.entities.Product;
import in.ac.vit.twicks.entities.Product_;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

/**
 * @author sahir
 * 
 */
@Stateless
public class ProductDaoImpl extends AbstractDaoImpl<Product> implements
		ProductDao {

	@Inject
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllByCompanyId(Integer companyId) {
		Query query = this.getEntityManager().createNamedQuery(
				"productsByCompanyId", Product.class);
		query.setParameter("companyId", companyId);
		return query.getResultList();
	}

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public List<Product> get(int first, int pageSize, String sortField,
			String sortOrder, Map<String, String> filters) {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> product = criteria.from(Product.class);

		this.buildFilters(filters, criteria, builder, product);

		if (!StringUtils.isBlank(sortField)) {
			Path<?> path = null;
			switch (sortField) {
			case "id":
				path = product.get(Product_.id);
				break;
			case "name":
				path = product.get(Product_.name);
				break;
			case "keywords":
				path = product.get(Product_.keywords);
				break;
			}

			if (sortOrder.equalsIgnoreCase("asc")) {
				criteria.orderBy(builder.asc(path));
			} else {
				criteria.orderBy(builder.desc(path));
			}

		}
		TypedQuery<Product> query = this.getEntityManager().createQuery(
				criteria);
		return query.setFirstResult(first).setMaxResults(pageSize)
				.getResultList();
	}

	@Override
	public int getCount(Map<String, String> filters) {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Product> product = criteria.from(Product.class);
		criteria.select(builder.count(product));

		this.buildFilters(filters, criteria, builder, product);
		TypedQuery<Long> query = this.getEntityManager().createQuery(criteria);
		return query.getSingleResult().intValue();
	}

	private void buildFilters(Map<String, String> filters,
			CriteriaQuery<?> criteria, CriteriaBuilder builder,
			Root<Product> product) {

		if (filters == null)
			return;

		Set<String> keys = filters.keySet();
		ArrayList<Predicate> predicates = new ArrayList<>();
		for (String key : keys) {
			String value = filters.get(key);
			switch (key) {
			case "id":
				predicates.add(builder.equal(product.get(Product_.id), value));
				break;
			case "name":
				predicates.add(builder.like(product.get(Product_.name), value
						+ "%"));
				break;
			case "keywords":
				predicates.add(builder.equal(product.get(Product_.keywords),
						"%" + value + "%"));
				break;
			case "company":
				Join<Product, Company> company = product.join(Product_.company);
				predicates.add(builder.equal(company.get(Company_.id), "%"
						+ value + "%"));
				break;
			}
		}
		Predicate[] predicateArr = new Predicate[predicates.size()];
		for (int i = 0; i < predicateArr.length; i++) {
			predicateArr[i] = predicates.get(i);
		}
		criteria.where(predicateArr);
	}

}
