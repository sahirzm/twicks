/**
 * 
 */
package in.ac.vit.twicks.datastorage.dao.postgres;

import in.ac.vit.twicks.datastorage.dao.AbstractDaoImpl;
import in.ac.vit.twicks.datastorage.dao.ResultDao;
import in.ac.vit.twicks.entities.Result;
import in.ac.vit.twicks.search.fetchers.Sources;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;



/**
 * @author sahir
 *
 */
@Stateless
public class ResultDaoImpl extends AbstractDaoImpl<Result> implements ResultDao {

	@Inject
	private EntityManager entityManager;

	@Override
	public List<Result> getByProductId(Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Result> getByProductIdAndSource(Integer productId,
			Sources source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public List<Result> get(int first, int pageSize, String sortField,
			String sortOrder, Map<String, String> filters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount(Map<String, String> filters) {
		// TODO Auto-generated method stub
		return 0;
	}
}
