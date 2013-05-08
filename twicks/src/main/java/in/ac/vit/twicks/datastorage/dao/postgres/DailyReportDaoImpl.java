package in.ac.vit.twicks.datastorage.dao.postgres;

import in.ac.vit.twicks.datastorage.dao.DailyReportDao;

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
public class DailyReportDaoImpl implements DailyReportDao {

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
			String sql = "SELECT count(*) as count, avg(score) as score, product_id, source FROM hourly_score "
					+ "WHERE data_date = '"
					+ sdate
					+ "' "
					+ "GROUP BY data_date, product_id, source";
			this.log.info("Generating daily report for date ==> " + sdate);
			this.log.info(sql);
			Statement stmt = conn.createStatement();
			result = stmt.executeQuery(sql);

			while (result.next()) {
				sql = "INSERT INTO daily_score (source, score, product_id, count, data_date) values ('"
						+ result.getString("source")
						+ "', "
						+ result.getDouble("score")
						+ ", "
						+ result.getInt("product_id")
						+ ", "
						+ result.getInt("count") + ", " + "'" + sdate + "')";
				this.log.info(sql);
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