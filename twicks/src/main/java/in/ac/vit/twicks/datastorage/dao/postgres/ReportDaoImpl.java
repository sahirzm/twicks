package in.ac.vit.twicks.datastorage.dao.postgres;

import in.ac.vit.twicks.datastorage.dao.ReportDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

@Stateless
public class ReportDaoImpl implements ReportDao {

	@Resource(name = "java:jboss/datasources/twicksDS")
	private DataSource dataSource;
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public List<Map<String, Object>> getAllProductsScore(int companyId) {
		Connection conn = null;
		ArrayList<Map<String, Object>> result = new ArrayList<>();
		try {
			String sql = "SELECT p.name, p.id, avg(d.score) as score "
					+ "FROM product p, daily_score d "
					+ "WHERE p.id = d.product_id AND p.company_id = ? "
					+ "GROUP BY p.id" + " ORDER BY score desc";
			conn = this.dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, companyId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Map<String, Object> map = new HashMap<>(3);
				map.put("productId", rs.getInt("id"));
				map.put("productName", rs.getString("name"));
				map.put("score",
						Math.round(rs.getDouble("score") * 100.0) / 100.0);
				result.add(map);
			}
		} catch (SQLException e) {
			this.log.error("SQL Exception ==> " + e.getMessage());
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
		this.log.info("got products = " + result.size());
		return result;
	}

	@Override
	public List<Map<String, Object>> getLast24HrsScore(int productId) {
		Connection conn = null;
		List<Map<String, Object>> data = new ArrayList<>(24);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.HOUR_OF_DAY, -25);
		try {
			conn = this.dataSource.getConnection();
			for (int i = 0; i < 24; i++) {
				Map<String, Object> val = new HashMap<>();
				cal.add(Calendar.HOUR_OF_DAY, 1);
				val.put("hour", cal.get(Calendar.HOUR_OF_DAY));
				String date = cal.get(Calendar.YEAR) + "-"
						+ (cal.get(Calendar.MONTH) + 1) + "-"
						+ cal.get(Calendar.DAY_OF_MONTH);
				this.log.info("Fetching for ==> " + date + " = "
						+ cal.get(Calendar.HOUR_OF_DAY));
				String sql = "SELECT score, count FROM hourly_score "
						+ "WHERE hour = " + cal.get(Calendar.HOUR_OF_DAY)
						+ " AND data_date = '" + date + "' AND product_id="
						+ productId;
				String tsql = sql + " AND source='TWITTER'";
				ResultSet rs = conn.createStatement().executeQuery(tsql);
				String score = "";
				String count = "";
				if (rs.next()) {
					score += (Math.round((rs.getDouble("score") * 100.0)) / 100.0)
							+ ", ";
					count += rs.getInt("count") + ", ";
				} else {
					score += "0.00, ";
					count += "0, ";
				}

				String fsql = sql + " AND source='FACEBOOK'";
				ResultSet frs = conn.createStatement().executeQuery(fsql);

				if (frs.next()) {
					score += (Math.round((frs.getDouble("score") * 100.0)) / 100.0)
							+ ", ";
					count += frs.getInt("count");
				} else {
					score += "0.00";
					count += "0";
				}
				val.put("score", score);
				val.put("count", count);
				data.add(val);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return data;
	}
}