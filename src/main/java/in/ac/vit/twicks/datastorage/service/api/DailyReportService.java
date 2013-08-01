package in.ac.vit.twicks.datastorage.service.api;

import java.util.Date;

public interface DailyReportService {

	void generateReport();

	void generateReport(Date date);
}
