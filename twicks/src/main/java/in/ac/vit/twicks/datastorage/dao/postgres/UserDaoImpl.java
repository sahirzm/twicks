package in.ac.vit.twicks.datastorage.dao.postgres;

import in.ac.vit.twicks.datastorage.dao.AbstractDaoImpl;
import in.ac.vit.twicks.datastorage.dao.UserDao;
import in.ac.vit.twicks.entities.Company;
import in.ac.vit.twicks.entities.Company_;
import in.ac.vit.twicks.entities.User;
import in.ac.vit.twicks.entities.User_;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;

@Stateless
public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {

	@Inject
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public User getByUsername(String username) {
		return this.getEntityManager()
				.createNamedQuery("user.findByUsername", User.class)
				.setParameter("username", username).getSingleResult();
	}

	@Override
	public List<User> get(int first, int pageSize, String sortField,
			String sortOrder, Map<String, String> filters) {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> user = criteria.from(User.class);

		this.buildFilters(filters, criteria, builder, user);

		if (!StringUtils.isBlank(sortField)) {
			Path<?> path = null;
			switch (sortField) {
			case "id":
				path = user.get(User_.id);
				break;
			case "firstname":
				path = user.get(User_.firstname);
				break;
			case "lastname":
				path = user.get(User_.lastname);
				break;
			case "email":
				path = user.get(User_.email);
				break;
			}

			if (sortOrder.equalsIgnoreCase("asc")) {
				criteria.orderBy(builder.asc(path));
			} else {
				criteria.orderBy(builder.desc(path));
			}

		}
		TypedQuery<User> query = this.getEntityManager().createQuery(criteria);
		return query.setFirstResult(first).setMaxResults(pageSize)
				.getResultList();
	}

	@Override
	public int getCount(Map<String, String> filters) {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<User> user = criteria.from(User.class);
		criteria.select(builder.count(user));

		this.buildFilters(filters, criteria, builder, user);
		TypedQuery<Long> query = this.getEntityManager().createQuery(criteria);
		return query.getSingleResult().intValue();

	}

	private void buildFilters(Map<String, String> filters,
			CriteriaQuery<?> criteria, CriteriaBuilder builder, Root<User> user) {

		if (filters == null)
			return;

		Set<String> keys = filters.keySet();
		ArrayList<Predicate> predicates = new ArrayList<>();
		for (String key : keys) {
			String value = filters.get(key);

			switch (key) {
			case "id":
				predicates.add(builder.equal(user.get(User_.id), value));
				break;
			case "firstname":
				predicates.add(builder.like(user.get(User_.firstname), value
						+ "%"));
				break;
			case "lastname":
				predicates.add(builder.like(user.get(User_.lastname), value
						+ "%"));
				break;
			case "email":
				predicates
						.add(builder.like(user.get(User_.email), value + "%"));
				break;
			case "company":
				Join<User, Company> company = user.join(User_.company);
				predicates.add(builder.like(company.get(Company_.name), "%"
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
