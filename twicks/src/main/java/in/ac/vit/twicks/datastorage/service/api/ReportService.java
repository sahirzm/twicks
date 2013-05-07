package in.ac.vit.twicks.datastorage.service.api;

import java.util.List;
import java.util.Map;

public interface ReportService {

	List<Map<String, Object>> getAllProductsScore(int companyId);

	List<Map<String, Object>> getLast24HrsScore(int productId);
}
