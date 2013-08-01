/**
 * This is implementation of status dao for Apache Hadoop
 * 
 */
package in.ac.vit.twicks.datastorage.service.impl;

import in.ac.vit.twicks.datastorage.dao.StatusDao;
import in.ac.vit.twicks.datastorage.service.api.StatusService;
import in.ac.vit.twicks.search.statuses.Status;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author sahir
 *
 */
@Stateless
public class StatusServiceImpl implements StatusService {

	@Inject
	private StatusDao statusDao;

	@Override
	public Status storeStatus(Status status) {
		return this.getStatusDao().save(status);
	}

	protected StatusDao getStatusDao() {
		return this.statusDao;
	}
}
