/** 
 * This is implementation of status dao for Apache Hadoop
 * 
 */
package in.ac.vit.twicks.datastorage.service;

import in.ac.vit.twicks.datastorage.apis.StatusService;
import in.ac.vit.twicks.search.statuses.Status;

/**
 * @author sahir
 *
 */
public class StatusServiceImpl implements StatusService {

	@Override
	public Integer storeStatus(Status status) {
		System.out.println(status);
		return null;
	}

}
