package in.ac.vit.twicks.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static String dateToStr(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public static Date strToDate(String value) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(value);
			return date;
		} catch (ParseException e) {
			return null;
		}
	}
}
