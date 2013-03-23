package in.ac.vit.twicks.datastorage.dao.mongodb;

import in.ac.vit.twicks.datastorage.dao.StatusDao;
import in.ac.vit.twicks.search.statuses.Status;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.mongodb.DB;
import com.mongodb.DBCollection;

@Stateless
public class StatusDaoImpl implements StatusDao {

	@Inject
	private DB db;

	@Override
	public List<Status> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status getStatus(String id) {
		return null;
	}

	@Override
	public Status storeStatus(Status status) {
		DBCollection collection = this.db.getCollection("status");
		collection.insert(status.getDBObject());
		return status;
	}

}
