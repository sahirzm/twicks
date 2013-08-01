package in.ac.vit.twicks.datastorage.service.impl;

import in.ac.vit.twicks.datastorage.dao.DailyReportDao;
import in.ac.vit.twicks.datastorage.service.api.DailyReportService;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

@Stateless
public class DailyReportServiceImpl implements DailyReportService {

	@Inject
	private DailyReportDao dailyReportDao;
	private transient Logger log = Logger.getLogger(this.getClass());

	@Override
	@Schedule(dayOfWeek = "*", hour = "0", minute = "30")
	public void generateReport() {
		this.generateReport(new Date());
	}

	@Override
	public void generateReport(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		if (this.dailyReportDao.generateReport(cal.getTime())) {
			this.log.info("Successfully generated daily report for ==> "
					+ cal.getTime());
		} else {
			this.log.info("Failed to generate daily report for ==> "
					+ cal.getTime());
		}
	}

}
