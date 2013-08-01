package in.ac.vit.twicks.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class ParamParser {

	private HttpServletRequest request;

	public ParamParser(HttpServletRequest request) {
		this.request = request;
	}

	public String getString(String name, String def) {
		String value = request.getParameter(name);
		if (StringUtils.isEmpty(value)) {
			return def;
		} else {
			return value.trim();
		}
	}

	public String getString(String name) {
		return this.getString(name, null);
	}

	public int getInt(String name, int def) {
		String value = request.getParameter(name);
		if (StringUtils.isEmpty(value)) {
			return def;
		} else {
			try {
				int val = Integer.parseInt(value);
				return val;
			} catch (NumberFormatException e) {
				return def;
			}
		}
	}

	public int getInt(String name) {
		return this.getInt(name, 0);
	}

	public double getDouble(String name, double def) {
		String value = request.getParameter(name);
		if (StringUtils.isEmpty(value)) {
			return def;
		} else {
			try {
				double val = Double.parseDouble(value);
				return val;
			} catch (NumberFormatException e) {
				return def;
			}
		}
	}

	public double getDouble(String name) {
		return this.getDouble(name, 0);
	}

	public String[] getStrings(String name, String[] def) {
		String[] values = request.getParameterValues(name);
		if (values.length == 0) {
			return def;
		} else {
			return values;
		}
	}

	public String[] getStrings(String name) {
		return this.getStrings(name, null);
	}

	public Date getDate(String name, Date def) {
		String value = request.getParameter(name);
		if (StringUtils.isEmpty(value)) {
			return def;
		} else {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(value);
				return date;
			} catch (ParseException e) {
				return def;
			}
		}
	}

	public Date getDate(String name) {
		return this.getDate(name, null);
	}

	public boolean getBoolean(String name) {
		String value = request.getParameter(name);
		if (StringUtils.isBlank(value)) {
			return false;
		} else {
			if (value.equalsIgnoreCase("on") || value.equalsIgnoreCase("yes")
					|| value.equalsIgnoreCase("y")
					|| value.equalsIgnoreCase("1")) {
				return true;
			}
			return Boolean.valueOf(value);
		}
	}
	
}
