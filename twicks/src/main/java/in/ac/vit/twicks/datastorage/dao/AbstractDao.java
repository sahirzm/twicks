/**
 *
 */
package in.ac.vit.twicks.datastorage.dao;

import java.util.List;
import java.util.Map;

/**
 * @author sahir
 */
public interface AbstractDao<X> {

	public void delete(X x);

	public void deleteById(Integer id);
	
	public X getById(Integer id);

	public X save(X x);

	public X update(X x);

	public Long getCount();

	public List<X> get(int first, int pageSize, String sortField,
			String sortOrder, Map<String, String> filters);

	public int getCount(Map<String, String> filters);

}
