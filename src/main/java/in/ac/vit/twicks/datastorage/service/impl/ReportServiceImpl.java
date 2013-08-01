package in.ac.vit.twicks.datastorage.service.impl;

import in.ac.vit.twicks.datastorage.dao.ReportDao;
import in.ac.vit.twicks.datastorage.service.api.ReportService;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ReportServiceImpl implements ReportService {

	@Inject
	private ReportDao reportDao;

	@Override
	public List<Map<String, Object>> getAllProductsScore(int companyId) {
		return this.reportDao.getAllProductsScore(companyId);
	}

	@Override
	public List<Map<String, Object>> getLast24HrsScore(int productId) {
		return this.reportDao.getLast24HrsScore(productId);
	}

}
