package exp.jsapar.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * FIXME Clear this util class.
 * 
 * @author JsaPar Developer
 */
public class DateUtil {
	// for more information about the ISO 8601 standard:
	// http://www.iso.org/iso/support/faqs/faqs_widely_used_standards/widely_used_standards_other/date_and_time_format.htm
	private static final String DATE_TIME_FORMAT_ISO8601 = "yyyy-MM-dd HH:mm:ss";

	private static GregorianCalendar calendar = new GregorianCalendar();

	/**
	 * Check style rule: utility classes should not have a public constructor.
	 */
	private DateUtil() {
		// Intentionally left blank.
	}

	/**
	 * Returns the current date as a Date object.
	 * 
	 * @return the current date.
	 */
	public static Date now() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	/**
	 * Returns the current date as string, formatted as "yyyy-MM-dd HH:mm:ss".
	 * 
	 * @return the current date as string.
	 */
	public static String nowAsString() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT_ISO8601);
		return sdf.format(calendar.getTime());
	}

	/**
	 * Returns the current date as string, formatted in the specified date
	 * format.
	 * 
	 * @param dateFormat
	 *            in which format the date must be formatted.
	 * @return the current date as string, formatted in the specified date
	 *         format.
	 */
	public static String nowAsString(String dateFormat) {
		// TODO Calendar cal = Calendar.getInstance(); cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(calendar.getTime());
	}

	/**
	 * Returns the specified date as string, formatted as "yyyy-MM-dd HH:mm:ss".
	 * 
	 * @param date
	 *            the specified date that needs to be formatted.
	 * 
	 * @return the specified date as string.
	 */
	public static String formattedDateAsString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT_ISO8601);
		return sdf.format(date.getTime());
	}

	public static Date getStartDate(Date date) {
		calendar.setTime(date);
		calendar.set(GregorianCalendar.HOUR_OF_DAY,
				calendar.getActualMinimum(GregorianCalendar.HOUR_OF_DAY));
		calendar.set(GregorianCalendar.MINUTE,
				calendar.getActualMinimum(GregorianCalendar.MINUTE));
		calendar.set(GregorianCalendar.SECOND,
				calendar.getActualMinimum(GregorianCalendar.SECOND));
		calendar.set(GregorianCalendar.MILLISECOND,
				calendar.getActualMinimum(GregorianCalendar.MILLISECOND));
		return (Date) calendar.getTime().clone();
	}

	public static Date getEndDate(Date date) {
		calendar.setTime(date);
		calendar.set(GregorianCalendar.HOUR_OF_DAY,
				calendar.getActualMaximum(GregorianCalendar.HOUR_OF_DAY));
		calendar.set(GregorianCalendar.MINUTE,
				calendar.getActualMaximum(GregorianCalendar.MINUTE));
		calendar.set(GregorianCalendar.SECOND,
				calendar.getActualMaximum(GregorianCalendar.SECOND));
		calendar.set(GregorianCalendar.MILLISECOND,
				calendar.getActualMaximum(GregorianCalendar.MILLISECOND));
		return (Date) calendar.getTime().clone();
	}

	public static Date getNextDay(Date date) {
		calendar.setTime(date);
		calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
		return (Date) calendar.getTime().clone();
	}

	public static Date getNextNDay(Date date, int n) {
		calendar.setTime(date);
		calendar.add(GregorianCalendar.DAY_OF_MONTH, n);
		return (Date) calendar.getTime().clone();
	}

	public static Date getBeginOfMonth(Date date, int months) {
		calendar.setTime(date);
		if (months != 0)
			calendar.add(GregorianCalendar.MONTH, months);
		calendar.set(GregorianCalendar.DAY_OF_MONTH,
				calendar.getActualMinimum(GregorianCalendar.DAY_OF_MONTH));
		return (Date) calendar.getTime().clone();
	}

	public static Date getEndOfMonth(Date date, int months) {
		calendar.setTime(date);
		if (months != 0)
			calendar.add(GregorianCalendar.MONTH, months);
		calendar.set(GregorianCalendar.DAY_OF_MONTH,
				calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
		return (Date) calendar.getTime().clone();
	}

	public static Date getBeginOfYear(Date date) {
		calendar.setTime(date);
		calendar.set(GregorianCalendar.MONTH,
				calendar.getActualMinimum(GregorianCalendar.MONTH));
		calendar.set(GregorianCalendar.DAY_OF_MONTH,
				calendar.getActualMinimum(GregorianCalendar.DAY_OF_MONTH));
		calendar.set(GregorianCalendar.HOUR_OF_DAY,
				calendar.getActualMinimum(GregorianCalendar.HOUR_OF_DAY));
		calendar.set(GregorianCalendar.MINUTE,
				calendar.getActualMinimum(GregorianCalendar.MINUTE));
		calendar.set(GregorianCalendar.SECOND,
				calendar.getActualMinimum(GregorianCalendar.SECOND));
		calendar.set(GregorianCalendar.MILLISECOND,
				calendar.getActualMinimum(GregorianCalendar.MILLISECOND));
		return (Date) calendar.getTime().clone();
	}

	public static Date getEndOfYear(Date date) {
		calendar.setTime(date);
		calendar.set(GregorianCalendar.MONTH,
				calendar.getActualMaximum(GregorianCalendar.MONTH));
		calendar.set(GregorianCalendar.DAY_OF_MONTH,
				calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
		calendar.set(GregorianCalendar.HOUR_OF_DAY,
				calendar.getActualMaximum(GregorianCalendar.HOUR_OF_DAY));
		calendar.set(GregorianCalendar.MINUTE,
				calendar.getActualMaximum(GregorianCalendar.MINUTE));
		calendar.set(GregorianCalendar.SECOND,
				calendar.getActualMaximum(GregorianCalendar.SECOND));
		calendar.set(GregorianCalendar.MILLISECOND,
				calendar.getActualMaximum(GregorianCalendar.MILLISECOND));
		return (Date) calendar.getTime().clone();
	}

	public static int getYearOfDate(Date date) {
		return calendar.get(Calendar.YEAR);
	}

	public static Date getEndOfYear(int year) {
		calendar.set(GregorianCalendar.YEAR, year);
		calendar.set(GregorianCalendar.MONTH,
				calendar.getActualMaximum(GregorianCalendar.MONTH));
		calendar.set(GregorianCalendar.DAY_OF_MONTH,
				calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
		calendar.set(GregorianCalendar.HOUR_OF_DAY,
				calendar.getActualMaximum(GregorianCalendar.HOUR_OF_DAY));
		calendar.set(GregorianCalendar.MINUTE,
				calendar.getActualMaximum(GregorianCalendar.MINUTE));
		calendar.set(GregorianCalendar.SECOND,
				calendar.getActualMaximum(GregorianCalendar.SECOND));
		calendar.set(GregorianCalendar.MILLISECOND,
				calendar.getActualMaximum(GregorianCalendar.MILLISECOND));
		return (Date) calendar.getTime().clone();
	}

	public static int monthsBetween(Date startDate, Date endDate) {
		calendar.setTime(startDate);
		int startMonth = calendar.get(GregorianCalendar.MONTH);
		int startYear = calendar.get(GregorianCalendar.YEAR);
		calendar.setTime(endDate);
		int endMonth = calendar.get(GregorianCalendar.MONTH);
		int endYear = calendar.get(GregorianCalendar.YEAR);
		return (endYear - startYear) * 12 + (endMonth - startMonth);
	}

	public static int daysBetween(Date startDate, Date endDate) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(startDate);
		c2.setTime(endDate);
		return daysBetween(c1, c2);
	}

	public static int daysBetween(Calendar early, Calendar late) {
		return (int) (toJulian(late) - toJulian(early));
	}

	public static final float toJulian(Calendar c) {
		int Y = c.get(Calendar.YEAR);
		int M = c.get(Calendar.MONTH);
		int D = c.get(Calendar.DATE);
		int A = Y / 100;
		int B = A / 4;
		int C = 2 - A + B;
		float E = (int) (365.25f * (Y + 4716));
		float F = (int) (30.6001f * (M + 1));
		float JD = (C + D + E + F) - 1524.5f;
		return JD;
	}
}
