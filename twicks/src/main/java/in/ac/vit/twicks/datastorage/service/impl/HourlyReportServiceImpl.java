package in.ac.vit.twicks.datastorage.service.impl;

import in.ac.vit.twicks.datastorage.dao.HourlyReportDao;
import in.ac.vit.twicks.datastorage.service.api.HourlyReportService;

import java.util.Calendar;
import java.util.Date;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.log4j.Logger;

@Stateless
public class HourlyReportServiceImpl implements HourlyReportService {

	@Inject
	private HourlyReportDao hourlyReportDao;

	private transient Logger log = Logger.getLogger(this.getClass());

	@Override
	@Schedule(hour = "*", minute = "30")
	public void generateReport() {
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.HOUR_OF_DAY, -1);
		this.generateReport(calender.getTime());
	}

	@Override
	public void generateReport(Date date) {
		Boolean result = this.hourlyReportDao.generateReport(date);
		if (result) {
			this.log.info("Successfully executed hourly script for ==> " + date);
		} else {
			this.log.error("Error executing hourly script for ==> " + date);
		}
	}
}