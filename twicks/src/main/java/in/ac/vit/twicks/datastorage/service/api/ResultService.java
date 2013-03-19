/**
 * 
 */
package in.ac.vit.twicks.datastorage.service.api;

import java.util.List;

import in.ac.vit.twicks.entities.Result;
import in.ac.vit.twicks.search.fetchers.Sources;

/**
 * @author sahir
 *
 */
public interface ResultService {

	Result create(Result result);
	Result update(Result result);
	Result getById(Integer id);
	void delete(Result result);
	void deleteById(Integer id);
	
	List<Result> getByProductId(Integer productId);
	List<Result> getByProductIdAndSource(Integer productId, Sources source);
}