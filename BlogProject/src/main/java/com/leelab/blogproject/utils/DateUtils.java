package com.leelab.blogproject.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

	public static final int TIMESTAMP = 1;
	public static final int DATE_SQL = 2;
	public static final int DATE = 3;
	
	public static Timestamp now() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static Timestamp diff(Timestamp original, int diffType, int diff) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(original);
		cal.add(Calendar.DAY_OF_WEEK, diff);
		return new Timestamp(cal.getTimeInMillis());
	}
	
	public static String format(Timestamp timestamp, String format) {
		SimpleDateFormat form = new SimpleDateFormat(format);
		return form.format(timestamp);
	}
	
}
