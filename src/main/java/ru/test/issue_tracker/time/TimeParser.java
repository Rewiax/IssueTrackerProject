package ru.test.issue_tracker.time;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.context.annotation.Configuration;

/**
 * @author maxim
 * class for parsing time data
 */
@Configuration
public class TimeParser {

	private static final String DATE_FORMAT = "dd/MM/yyyy";
	
	private static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";

	
	public static String dateFromString(String time) {
		DateFormat formatDate = new SimpleDateFormat(DATE_FORMAT);
		DateFormat standartformatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String formatted = formatDate.format(standartformatDate.parse(time).getTime());
			return formatted;
		} catch (ParseException e) {
//			e.printStackTrace();
		}

		return time;
	}
	
	public static String dateTimeFromString(String time) {
		DateFormat formatDate = new SimpleDateFormat(DATE_TIME_FORMAT);
		DateFormat standartformatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String formatted = formatDate.format(standartformatDate.parse(time).getTime());
			return formatted;
		} catch (ParseException e) {
//			e.printStackTrace();
		}

		return time;
	}
	
	public static String dateFromTimestamp(Timestamp time) {
		DateFormat formatDate = new SimpleDateFormat(DATE_FORMAT);
		String formatted = formatDate.format(time.getTime());

		return formatted;
	}
	
	public static String dateTimeFromTimestamp(Timestamp time) {
		DateFormat formatDate = new SimpleDateFormat(DATE_TIME_FORMAT);
		String formatted = formatDate.format(time.getTime());

		return formatted;
	}
	
	public static Timestamp dateFromStr(String timeString) {
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		try {
			Timestamp d = new Timestamp(dateFormat.parse(timeString).getTime());
			return d;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Timestamp dateTimeFromStr(String timeString) {
		DateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
		try {
			Timestamp d = new Timestamp(dateFormat.parse(timeString).getTime());
			return d;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
