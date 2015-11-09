package namoo.security.v3.z_library;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * 날짜 관련 함수 클래스
 * 
 * @author 김동열
 * @since 2008. 09. 25
 */
public class DateUtil {

	/**
	 * 기본 날짜 포맷 (yyyy-MM-dd'T'HH:mm:ss) RFC333
	 * (http://www.ietf.org/rfc/rfc3339.txt)
	 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
	/** 1일을 millisecond로 환산한 값 */
	public static final long ONE_DAY_MS = 24 * 60 * 60 * 1000;

	/** 날짜 포맷 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

	/** 기본 날짜 포맷 길이 */
	private static final int DEFAUT_DATE_FORMAT_LEN = DEFAULT_DATE_FORMAT.length();
	/** 날짜 포맷 길이 */
	private static final int DATE_FORMAT_LEN = DATE_FORMAT.length();

	/**
	 * 현재 날짜를 문자열로 반환한다.
	 * 
	 * @return
	 */
	public static String getCurrentDateAsString() {
		return dateToString(new java.util.Date(), null);
	}

	/**
	 * 현재 날짜를 문자열로 반환한다.
	 * 
	 * @return
	 */
	public static String getCurrentDateAsString(String format) {
		return dateToString(new java.util.Date(), format);
	}

	/**
	 * 기본 포맷으로 날짜를 문자열로 변환한다.
	 * 
	 * @param date
	 *            날짜
	 * @return 문자열 날짜
	 */
	public static String dateToString(java.util.Date date) {
		return dateToString(date, null);
	}

	/**
	 * 날짜를 문자열로 변환한다.
	 * 
	 * @param date
	 *            날짜
	 * @param format
	 *            포맷
	 * @return 문자열 날짜
	 */
	public static String dateToString(java.util.Date date, String format) {
		if (date == null)
			return null;

		SimpleDateFormat sdf = makeFormat(format);

		return sdf.format(date);
	}
	
	/**
	 * 문자열을 데이터로 변환한다.
	 * 
	 * @param dateString
	 *            날짜 문자열
	 * @return 변환된 날짜
	 */
	public static Date stringToDate(String dateString) {
		return stringToDate(dateString, null);
	}
	

	/**
	 * 문자열을 데이터로 변환한다.
	 * 
	 * @param dateString
	 *            날짜 문자열
	 * @param format
	 *            변환할 포맷
	 * @return 변환된 날짜
	 */
	public static Date stringToDate(String dateString, String format) {
		if (dateString == null)
			return null;

		SimpleDateFormat sdf = makeFormat(dateString, format);

		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
     * 날짜를 입력된 포맷으로 변환한다.
     * 
     * @param date
     *            날짜
     * @param format
     *            포맷
     * @return 변환된 날짜
     */
    public static Date convert(java.util.Date date, String format) {
        return stringToDate(dateToString(date, format), format);
    }

	private static SimpleDateFormat makeFormat(String format) {
		String usingFormat;
		if (format != null) {
			usingFormat = format;
		} else {
			usingFormat = DEFAULT_DATE_FORMAT;
		}

		return new SimpleDateFormat(usingFormat);
	}

	private static SimpleDateFormat makeFormat(String dateString, String format) {
		String usingFormat;
		if (format != null) {
			usingFormat = format;
		} else {
			int dateStringLen = dateString.length();
			if (dateStringLen == DEFAUT_DATE_FORMAT_LEN) {
				usingFormat = DEFAULT_DATE_FORMAT;
			} else if (dateStringLen == DATE_FORMAT_LEN) {
				usingFormat = DATE_FORMAT;
			} else {
				usingFormat = DEFAULT_DATE_FORMAT;
			}
		}

		SimpleDateFormat sdf = new SimpleDateFormat(usingFormat);

		return sdf;
	}

	/**
	 * 입력받은 날짜에서 +/- N개월의 날짜를 구한다.
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date changeDateWithMonthLevel(Date date, int month) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + month);
		return cal.getTime();
	}

	/**
	 * 입력받은 날짜에서 +/- N일의 날짜를 구한다.
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date changeDateWithDayLevel(Date date, int day) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + day);
		return cal.getTime();
	}

	/**
	 * 두 날짜의 차이(일수)를 구한다. <br>
	 * lastDate - firstDate의 일수
	 * 
	 * @param firstDate
	 * @param lastDate
	 * @return
	 */
	public static int diffDate(Date firstDate, Date lastDate) {
		String firstDateString = dateToString(firstDate, DATE_FORMAT);
		String lastDateString = dateToString(lastDate, DATE_FORMAT);

		Date defaultFirstDate = stringToDate(firstDateString, DATE_FORMAT);
		Date defaultLastDate = stringToDate(lastDateString, DATE_FORMAT);

		long diffTime = defaultLastDate.getTime() - defaultFirstDate.getTime();
		int days = (int) Math.ceil((double) diffTime / ONE_DAY_MS);

		return days;
	}

    
    /**
     * 두 날짜를 포함한 개월 수를 반환한다. <br>2009-01-01, 2009-08-30로 값이 주어지면 8이<br> 2009-01-30, 2010-01-01로 값이 주어지면 13이 반환된다.
     * 값을 거꾸로 넣어도 개월간의 차이수만 반환한다. (기간을 -로 반환하지 않는다)
     * @param startDate
     *            시작일
     * @param endDate
     *            종료일
     * @return 두 날짜를 포함한 기간을 개월수로 반환한다.
     */
    public static int diffMonth(Date startDate, Date endDate) {
    	
    	Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		if(DateUtil.isNextDate(startDate, endDate)){
			startCal.setTime(startDate);
			endCal.setTime(endDate);
		}else{
			endCal.setTime(startDate);
			startCal.setTime(endDate);
		}
		int year = endCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR);
		int month = endCal.get(Calendar.MONTH) - (startCal.get(Calendar.MONTH));
		month += year != 0 ? year * 12 : 0;
		return month;
    }
    
	
	
	/**
	 * 같은 날인지 비교(연도, 월, 일 비교) <br/>
	 * 
	 * @param date1
	 *            비교날짜1
	 * @param date2
	 *            비교날짜2
	 * @return 같은 날인지 비교결과
	 * @see org.apache.commons.lang.time.DateUtils.isSameDay(Date date1, Date
	 *      date2)
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return isSameDay(cal1, cal2);
	}

	/**
	 * 같은 날인지 비교(연도, 월, 일 비교) <br/>
	 * 
	 * @param cal1
	 *            비교날짜1
	 * @param cal2
	 *            비교날짜2
	 * @return 같은 날인지 비교
	 * @see @see org.apache.commons.lang.time.DateUtils.isSameDay(Calendar cal1,
	 *      Calendar cal2)
	 */
	public static boolean isSameDay(Calendar cal1, Calendar cal2) {
		if (cal1 == null || cal2 == null) {
			throw new IllegalArgumentException("The date must not be null");
		}
		return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1
				.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	}

	/**
	 * 이전 날짜인지 비교 <br/>
	 * base를 기준으로 target이 이전 날짜인지 확인
	 * 
	 * @param baseDate
	 *            기준일(base)
	 * @param prevDate
	 *            이전일(확인할 대상 날짜:target)
	 * @return 이전 날짜인지 여부
	 */
	public static boolean isPrevDate(Date baseDate, Date prevDate) {
		if (isSameDay(baseDate, prevDate))
			return false;
		if (baseDate.compareTo(prevDate) < 0)
			return false;
		return true;
	}

	/**
	 * 이후 날짜인지 비교 <br/>
	 * base를 기준으로 target이 이후 날짜인지 확인
	 * 
	 * @param baseDate
	 *            기준일(base)
	 * @param nextDate
	 *            이후일(확인할 대상 날짜:target)
	 * @return 이후 날짜인지 여부
	 */
	public static boolean isNextDate(Date baseDate, Date nextDate) {
		if (isSameDay(baseDate, nextDate))
			return false;
		if (baseDate.compareTo(nextDate) > 0)
			return false;
		return true;
	}

	/**
	 * 입력된 시간 초를 최대치(59)로 변경하여 반환한다.
	 * 
	 * @param date
	 *            시간
	 * @return 59초로 변경된 시간
	 */
	public static Date fillLimitSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	
	/**
     * 두 날짜 사이의 기간을 날짜수로 반환한다. 2009-01-01, 2009-01-05로 값이 주어지면 4가 반환된다.
     * 
     * @param startDate
     *            시작일
     * @param endDate
     *            종료일
     * @return 두 날짜 사이의 기간을 일수로 반환한다.
     */
    public static Long getDuration(Date startDate, Date endDate) {
    	Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        truncTime(startCal);
        
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        truncTime(endCal);
        return (endCal.getTimeInMillis() - startCal.getTimeInMillis()) / 1000 / 60 / 60 / 24;
    }
    
    private static void truncTime(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
	}
    
    /**
	 * 
	 * 
	 * @param startDate
	 * @param endDate
	 * @param holidays
	 * @return
	 */
	public static int getDurationExceptHoliday(Date startDate, Date endDate, Set<Date> holidays) {
		if (endDate.before(startDate)) return 0;
		
		int result = getDurationExceptHoliday(startDate, endDate);
		
		// 기간에 포함되는 토/일 아닌 휴일 count
		int holidayCount = 0;
		if (holidays != null) {
			for (Date holiday : holidays) {
				if (isWeekend(holiday)) continue;
				if (containInPeriod(startDate, endDate, holiday)) {
					holidayCount++;
				}
			}			
		}
		
		return result - holidayCount;
	}

	private static boolean isWeekend(Date holiday) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(holiday);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
	}
	
	public static boolean containInPeriod(Date startDate, Date endDate, Date aDay) {
        // FIXME 중복으로 만드네
		Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        
        startCal.set(Calendar.HOUR, 0);
        startCal.set(Calendar.MINUTE, 0);
        startCal.set(Calendar.SECOND, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        
        endCal.set(Calendar.HOUR_OF_DAY, 23);
        endCal.set(Calendar.MINUTE, 59);
        endCal.set(Calendar.SECOND, 59);
        endCal.set(Calendar.MILLISECOND, 999);
        
        long h = aDay.getTime();
        
		return startCal.getTimeInMillis() <= h && h <= endCal.getTimeInMillis();
	}

	public static int getDurationExceptHoliday(Date startDate, Date endDate) {
		if (endDate.before(startDate)) return 0;
		
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        truncTime(startCal);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        truncTime(endCal);
        
        int total = (int) ((endCal.getTimeInMillis() - startCal.getTimeInMillis()) / 1000 / 60 / 60 / 24 + 1);
        
        int mod = total % 7;
        int week = total / 7;
        
        int day = 0;
        int startDateOfWeek = startCal.get(Calendar.DAY_OF_WEEK);
        switch (startDateOfWeek) {
		case Calendar.SUNDAY:
			day = Math.max(mod - 1, 0);
			break;
		case Calendar.MONDAY:
			day = (mod >= 5) ? 5 : mod;
			break;
		case Calendar.TUESDAY:
			day = (mod >= 4) ? 4 : mod;
			break;
		case Calendar.WEDNESDAY:
			day = (mod >= 3) ? 3 + Math.max(mod - 5, 0) : mod;
			break;
		case Calendar.THURSDAY:
			day = (mod >= 2) ? 2 + Math.max(mod - 4, 0) : mod;
			break;
		case Calendar.FRIDAY:
			day = (mod >= 1) ? 1 + Math.max(mod - 3, 0) : mod;
			break;
		case Calendar.SATURDAY:
			day = Math.max(mod - 2, 0);
			break;
		}
        
        return week * 5 + day;
	}
	
	/**
     * 주어진 년월일을 마지막 날값을 반환한다. 2009-02-22가 주어지면 2009-02-28이 반환된다.
     * 
     * @param date
     *            날짜
     * @return 해당 년월의 마지막 날짜 값
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        return cal.getTime();
    }
    
    /**
     * 주어진 년월일을 1일값을 반환한다. 2009-02-22가 주어지면 2009-02-01이 반환된다.
     * 
     * @param date
     *            날짜
     * @return 해당 년월의 1일 값
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
        return cal.getTime();
    }
    
    /**
     * 주어진 주간의 토요일 반환하나. 2011-12-XX 가 수요일일 경우 토요일에 해당하는 날짜를 반환
     * 단 주의 시작을 토요일로 설정 
     * @param date
     * 			날짜
     * @return 해당 주간의 마지막 값
     */
    public static Date getLastDayOfWeek(Date date){
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        truncTime(cal);
        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
        	cal.add(Calendar.DATE, 1); 
        }
    	return cal.getTime();
    }
    
    /**
     * 주어진 주간의 일요일 반환하나. 2011-12-XX 가 수요일일 경우 일요일에 해당하는 날짜를 반환
     * 단 주의 시작을 일요일로 설정 
     * @param date
     * 			날짜
     * @return 해당 주간의 마지막 값
     */
    public static Date getFirstDayOfWeek(Date date) {
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        truncTime(cal);
        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
        	cal.add(Calendar.DATE, -1); 
        }
    	return cal.getTime();
    }
    
    /**
     * 주어진 날짜에 시간,분,초 를 모두 0으로 셋팅한다.
     * @param date 날짜
     * @return
     */
    public static Date trunkTime(Date date){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
    }

}
