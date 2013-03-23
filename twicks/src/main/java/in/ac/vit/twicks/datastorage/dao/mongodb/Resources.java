package in.ac.vit.twicks.datastorage.dao.mongodb;

import javax.enterprise.inject.Produces;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

public class Resources {

	@Produces
	public DB getMongoDB() {
		DB db = null;
		try {
			MongoClient client = new MongoClient("localhost", 27017);
			client.setWriteConcern(WriteConcern.JOURNALED);
			db = client.getDB("twicks");
		} catch (Exception e) {

		}
		return db;
	}
}
