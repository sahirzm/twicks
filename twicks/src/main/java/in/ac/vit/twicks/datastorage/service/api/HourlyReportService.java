package in.ac.vit.twicks.datastorage.service.api;

import java.util.Date;

public interface HourlyReportService {

	void generateReport(Date date);

	void generateReport();
}
