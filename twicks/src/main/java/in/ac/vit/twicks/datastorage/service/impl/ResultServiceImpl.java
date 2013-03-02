/**
 * 
 */
package in.ac.vit.twicks.datastorage.service.impl;

import in.ac.vit.twicks.datastorage.doa.ResultDao;
import in.ac.vit.twicks.datastorage.service.api.ResultService;
import in.ac.vit.twicks.entities.Production;
import in.ac.vit.twicks.entities.Result;
import in.ac.vit.twicks.search.fetchers.Sources;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author sahir
 *
 */
@Stateless
public class ResultServiceImpl implements ResultService{

	@Inject
	@Production
	private ResultDao resultDao;

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

	protected ResultDao getResultDao() {
		return this.resultDao;
	}

}
