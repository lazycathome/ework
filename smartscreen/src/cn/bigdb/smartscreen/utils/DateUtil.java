package cn.bigdb.smartscreen.utils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Functionality: 有关日期工具类
 */
public class DateUtil extends TimeUtil {

	public DateUtil() {
	}

	// ----------------------日期计算---------------------------------------------------------------------------------

	/**
	 * 是否开始日期在结束日期之前(不包括相等)
	 * 
	 * @param p_startDate
	 * @param p_endDate
	 * @return boolean 在结束日期前:ture;否则：false
	 */
	public static boolean isStartDateBeforeEndDate(Date p_startDate,
			Date p_endDate) throws ParseException {
		long l_startTime = getMillisOfDate(p_startDate);
		long l_endTime = getMillisOfDate(p_endDate);
		return (l_startTime - l_endTime > (long) 0) ? true : false;
	}

	/**
	 * 获取2个字符日期的天数差
	 * 
	 * @param p_startDate
	 * @param p_endDate
	 * @return 天数差
	 */
	public static long getDaysOfTowDiffDate(String p_startDate, String p_endDate)
			throws ParseException {

		Date l_startDate = toUtilDateFromStrDateByFormat(p_startDate,
				"yyyy-MM-dd");
		Date l_endDate = toUtilDateFromStrDateByFormat(p_endDate, "yyyy-MM-dd");
		long l_startTime = getMillisOfDate(l_startDate);
		long l_endTime = getMillisOfDate(l_endDate);
		long betweenDays = (long) ((l_endTime - l_startTime) / (1000 * 60 * 60 * 24));
		return betweenDays;
	}

	/**
	 * 获取2个长整型日期的天数差
	 * 
	 * @param p_startDate
	 * @param p_endDate
	 * @return 天数差
	 */
	public static long getDaysOfTowDiffDate(long p_startDate, long p_endDate){
		long betweenDays = (long) ((p_endDate - p_startDate) / (1000 * 60 * 60 * 24));
		return betweenDays;
	}
	
	/**
	 * 获取2个字符日期的周数差
	 * @param p_startDate
	 * @param p_endDate
	 * @return 周数差
	 */
	public static long getWeeksOfTowDiffDate(String p_startDate,
			String p_endDate) throws ParseException {
		return getDaysOfTowDiffDate(p_startDate, p_endDate) / 7;
	}

	/**
	 * 获取2个字符日期的月数差
	 * 
	 * @param p_startDate
	 * @param p_endDate
	 * @return 月数差
	 */
	public static long getMonthsOfTowDiffDate(String p_startDate,
			String p_endDate) throws ParseException {
		return getDaysOfTowDiffDate(p_startDate, p_endDate) / 30;
	}

	/**
	 * 获取2个字符日期的年数差
	 * 
	 * @param p_startDate
	 * @param p_endDate
	 * @return 年数差
	 */
	public static long getYearsOfTowDiffDate(String p_startDate,
			String p_endDate) throws ParseException {
		return getDaysOfTowDiffDate(p_startDate, p_endDate) / 365;
	}

	/**
	 * 在给定的日期基础上添加年，月，日、时，分，秒例如要再2006－10－21（uitl日期）添加3个月，并且格式化为yyyy-MM-dd格式，<br>
	 * 这里调用的方式为 addDate(2006－10－21,3,Calendar.MONTH,"yyyy-MM-dd")
	 * 
	 * @param p_startDate 给定的日期
	 * 
	 * @param p_count 时间的数量
	 * 
	 * @param p_field 添加的域
	 * 
	 * @param p_format 时间转化格式，例如：yyyy-MM-dd hh:mm:ss 或者yyyy-mm-dd等
	 * 
	 * @return 添加后格式化的时间
	 */
	public static String addDate(Date p_startDate, int p_count, int p_field,
			String p_format) throws ParseException {

		// 年，月，日、时，分，秒
		int l_year = getYearOfDate(p_startDate);
		int l_month = getMonthOfDate(p_startDate) - 1;
		int l_day = getDayOfDate(p_startDate);
		int l_hour = getHourOfDate(p_startDate);
		int l_minute = getMinuteOfDate(p_startDate);
		int l_second = getSecondOfDate(p_startDate);
		Calendar l_calendar = new GregorianCalendar(l_year, l_month, l_day,
				l_hour, l_minute, l_second);
		l_calendar.add(p_field, p_count);
		return toStrDateFromUtilDateByFormat(l_calendar.getTime(), p_format);
	}
}