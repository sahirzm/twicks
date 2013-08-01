package in.ac.vit.twicks.datastorage.dao.postgres;

import in.ac.vit.twicks.datastorage.dao.HourlyReportDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

@Stateless
public class HourlyReportDaoImpl implements HourlyReportDao {

	@Resource(name = "java:jboss/datasources/twicksDS")
	private DataSource dataSource;
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public Boolean generateReport(Date date) {
		ResultSet result = null;
		Connection conn = null;
		boolean flag = true;
		try {
			conn = this.dataSource.getConnection();

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			calendar.set(Calendar.SECOND, 0);
			String sdate = calendar.get(Calendar.YEAR) + "-"
					+ calendar.get(Calendar.MONTH) + "-"
					+ calendar.get(Calendar.DAY_OF_MONTH);
			String dd = sdate + " " + calendar.get(Calendar.HOUR_OF_DAY)
					+ ":00:00";
			String sql = "SELECT count(*) as count, date_trunc('hour',timestamp) as time, avg(score) as score, product_id, source  "
					+ "FROM status "
					+ "WHERE date_trunc('hour',timestamp) = '"
					+ dd
					+ "' "
					+ "GROUP BY date_trunc('hour',timestamp), product_id, source";
			this.log.debug("Generating hourly report for date ==> " + dd);
			conn.createStatement().executeUpdate(
					"update status set score = 1 where score = 0");
			this.log.debug(sql);
			Statement stmt = conn.createStatement();
			result = stmt.executeQuery(sql);
			Calendar cal = Calendar.getInstance();
			String createdOn = cal.get(Calendar.YEAR) + "-"
					+ cal.get(Calendar.MONTH) + "-"
					+ cal.get(Calendar.DAY_OF_MONTH) + " "
					+ cal.get(Calendar.HOUR_OF_DAY) + ":"
					+ cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
			while (result.next()) {
				sql = "INSERT INTO hourly_score (source, score, product_id, count, hour, data_date, created_on) values ('"
						+ result.getString("source")
						+ "', "
						+ result.getDouble("score")
						+ ", "
						+ result.getInt("product_id")
						+ ", "
						+ result.getInt("count")
						+ ", "
						+ calendar.get(Calendar.HOUR_OF_DAY)
						+ ", "
						+ "'"
						+ sdate + "', " + "'" + createdOn + "')";
				this.log.debug(sql);
				conn.createStatement().executeUpdate(sql);
			}
		} catch (SQLException e) {
			this.log.error(e.getMessage());
			flag = false;
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e1) {
				this.log.error(e1.getMessage());
			}
		}
		return flag;
	}

}