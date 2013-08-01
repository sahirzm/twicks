/**
 *
 */
package in.ac.vit.twicks.datastorage.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author sahir
 */
public abstract class AbstractDaoImpl<X> implements AbstractDao<X> {

	private Class<X> type;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AbstractDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	@Override
	public void delete(X x) {
		this.getEntityManager().remove(x);
	}

	@Override
	public X save(X x) {
		this.getEntityManager().persist(x);
		return x;
	}

	@Override
	public X update(X x) {
		this.getEntityManager().merge(x);
		return x;
	}

	public X getById(Integer id) {
		if (id <= 0) {
			return null;
		}
		return this.getEntityManager().find(type, id);
	}

	public void deleteById(Integer id) {
		if (id > 0)
			this.delete(this.getById(id));
	}

	@Override
	public Long getCount() {
		final StringBuffer queryString = new StringBuffer(
				"SELECT count(o) from ");
		queryString.append(type.getSimpleName()).append(" o ");

		final Query query = this.getEntityManager().createQuery(
				queryString.toString(), Long.class);

		return (Long) query.getSingleResult();

	}

	protected abstract EntityManager getEntityManager();
}
