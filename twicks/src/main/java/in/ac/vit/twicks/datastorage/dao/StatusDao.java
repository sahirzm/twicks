package in.ac.vit.twicks.datastorage.dao;

import in.ac.vit.twicks.search.statuses.Status;

import java.util.List;

public interface StatusDao {

	List<Status> getAll();

	Status getStatus(String id);

	Status storeStatus(Status status);
}
