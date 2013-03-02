/**
 * 
 */
package in.ac.vit.twicks.datastorage.doa.postgres;

import in.ac.vit.twicks.datastorage.doa.ResultDao;
import in.ac.vit.twicks.entities.Result;
import in.ac.vit.twicks.search.fetchers.Sources;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;



/**
 * @author sahir
 *
 */
@Stateless
public class ResultDaoImpl implements ResultDao {

	@Inject
	private EntityManager entityManager;

	@Override
	public Result create(Result result) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Result result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Result getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

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
	public Result update(Result result) {
		// TODO Auto-generated method stub
		return null;
	}

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}
}
