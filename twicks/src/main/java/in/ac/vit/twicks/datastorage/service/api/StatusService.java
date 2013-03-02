/** 
 * This is the interface to perform operations on data fetched from 
 * different data sources
 * 
 */
package in.ac.vit.twicks.datastorage.service.api;

import in.ac.vit.twicks.search.statuses.Status;

/**
 * @author sahir
 *
 */
public interface StatusService {
	
	public Integer storeStatus(Status status);
	
	/**
	 * Add additional methods here
	 */
}
