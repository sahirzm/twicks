package in.ac.vit.twicks.datastorage.service.api;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA. User: sahir Date: 31/3/13 Time: 4:39 PM To change
 * this template use File | Settings | File Templates.
 */
public interface AbstractService<X> {

	public X save(X x);

	public X update(X x);

	public void delete(X x);
	public void delete(int key);
	
	public int getCount();

	public List<X> get(int first, int pageSize, String sortField,
			String sortOrder, Map<String, String> filters);
	
	public int getCount(Map<String, String> filters);
}
