/**
 * 
 */
package in.ac.vit.twicks.datastorage.service.impl;

import in.ac.vit.twicks.datastorage.service.api.CompanyService;
import in.ac.vit.twicks.datastorage.service.api.ProductService;
import in.ac.vit.twicks.datastorage.service.api.ResultService;
import in.ac.vit.twicks.datastorage.service.api.StatusService;
import in.ac.vit.twicks.datastorage.service.api.UserService;
import in.ac.vit.twicks.entities.Production;

import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;

/**
 * @author sahir
 * 
 */
public class Resources {

	@Produces
	@Production
	public CompanyService getCompanyService(
			@New CompanyServiceImpl companyService) {
		return companyService;
	}

	@Produces
	@Production
	public ProductService getProductService(
			@New ProductServiceImpl productService) {
		return productService;
	}

	@Produces
	@Production
	public ResultService getResultService(@New ResultServiceImpl resultService) {
		return resultService;
	}

	@Production
	@Produces
	public StatusService getStatusService(@New StatusServiceImpl statusService) {
		return statusService;
	}

	@Produces
	@Production
	public UserService getUserService(@New UserServiceImpl userService) {
		return userService;
	}
}