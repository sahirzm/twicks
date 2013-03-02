/** 
 * This is implementation of status dao for Apache Hadoop
 * 
 */
package in.ac.vit.twicks.datastorage.service.impl;

import javax.ejb.Stateless;

import in.ac.vit.twicks.datastorage.service.api.StatusService;
import in.ac.vit.twicks.search.statuses.Status;

/**
 * @author sahir
 *
 */
@Stateless
public class StatusServiceImpl implements StatusService {

	@Override
	public Integer storeStatus(Status status) {
		System.out.println(status);
		return null;
	}

}
