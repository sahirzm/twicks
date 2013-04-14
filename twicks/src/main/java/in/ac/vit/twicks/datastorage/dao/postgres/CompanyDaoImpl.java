/**
 * 
 */
package in.ac.vit.twicks.datastorage.dao.postgres;

import in.ac.vit.twicks.datastorage.dao.AbstractDaoImpl;
import in.ac.vit.twicks.datastorage.dao.CompanyDao;
import in.ac.vit.twicks.entities.Company;
import in.ac.vit.twicks.entities.Company_;
import in.ac.vit.twicks.utils.Utils;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

/**
 * @author sahir
 *
 */
@Stateless
public class CompanyDaoImpl extends AbstractDaoImpl<Company> implements CompanyDao {

	@Inject
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getBySubscriptionDate(Date date) {
		Query query = this.getEntityManager().createNamedQuery(
				"companiesBySubDate", Company.class);
		query.setParameter("date", date);
		return query.getResultList();
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public List<Company> get(int first, int pageSize, String sortField,
			String sortOrder, Map<String, String> filters) {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Company> criteria = builder.createQuery(Company.class);
		Root<Company> store = criteria.from(Company.class);

		this.buildFilters(filters, criteria, builder, store);

		if (!StringUtils.isBlank(sortField)) {
			Path<?> path = null;
			switch (sortField) {
			case "id":
				path = store.get(Company_.id);
				break;
			case "name":
				path = store.get(Company_.name);
				break;
			case "subscriptionDate":
				path = store.get(Company_.subscriptionDate);
				break;
			}

			if (sortOrder.equalsIgnoreCase("asc")) {
				criteria.orderBy(builder.asc(path));
			} else {
				criteria.orderBy(builder.desc(path));
			}

		}
		TypedQuery<Company> query = this.getEntityManager().createQuery(criteria);
		return query.setFirstResult(first).setMaxResults(pageSize)
				.getResultList();
	}

	private void buildFilters(Map<String, String> filters,
			CriteriaQuery<?> criteria, CriteriaBuilder builder,
			Root<Company> company) {

		if (filters == null)
			return;

		Set<String> keys = filters.keySet();
		ArrayList<Predicate> predicates = new ArrayList<>();
		for (String key : keys) {
			String value = filters.get(key);
			switch (key) {
			case "id":
				predicates.add(builder.equal(company.get(Company_.id), value));
				break;
			case "name":
				predicates
						.add(builder.like(company.get(Company_.name), value + "%"));
				break;
			case "subscriptionDate":
				Date date = Utils.strToDate(value);
				if (date != null)
					predicates.add(builder.equal(
							company.get(Company_.subscriptionDate), date));
				break;
			}
		}
		Predicate[] predicateArr = new Predicate[predicates.size()];
		for (int i = 0; i < predicateArr.length; i++) {
			predicateArr[i] = predicates.get(i);
		}
		criteria.where(predicateArr);
	}

	@Override
	public int getCount(Map<String, String> filters) {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Company> company = criteria.from(Company.class);
		criteria.select(builder.count(company));

		this.buildFilters(filters, criteria, builder, company);
		TypedQuery<Long> query = this.getEntityManager().createQuery(criteria);
		return query.getSingleResult().intValue();
	}

}
