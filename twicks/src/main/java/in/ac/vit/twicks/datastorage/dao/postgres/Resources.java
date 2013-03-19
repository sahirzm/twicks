/**
 * This class is used to inject resources using CDI
 * @author sahir maredia
 * 
 */

package in.ac.vit.twicks.datastorage.dao.postgres;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resources {

	@Produces
	@PersistenceContext
	private EntityManager entityManager;

}
