/**
 * 
 */
package in.ac.vit.twicks.datastorage.service.impl;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * @author sahir
 * 
 */
public class Resources {

	@Produces
	public Logger produceLog(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass()
				.getName());
	}
}