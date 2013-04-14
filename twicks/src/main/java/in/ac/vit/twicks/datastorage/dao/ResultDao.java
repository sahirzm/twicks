/**
 * 
 */
package in.ac.vit.twicks.datastorage.dao;

import java.util.List;

import in.ac.vit.twicks.entities.Result;
import in.ac.vit.twicks.search.fetchers.Sources;

/**
 * @author sahir
 * 
 */
public interface ResultDao extends AbstractDao<Result> {

	List<Result> getByProductId(Integer productId);

	List<Result> getByProductIdAndSource(Integer productId, Sources source);
}
