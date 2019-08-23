package com.juma.tgm.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 时间公共类
 * @author weilibin
 * @date 2016年9月22日 下午6:32:36
 * @version V1.0
 */

public class DateUtil extends DateUtils {

    private static Logger log = LoggerFactory.getLogger(DateUtil.class);

    public final static String YYYYMMDDHHMMSS_SIMPLE = "yyyyMMddHHmmss";
    public final static String YYYYMMDD_SIMPLE = "yyyyMMdd";
    public final static String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public final static String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    public final static String YYYYMMDD = "yyyy-MM-dd";
    public final static String YYYYMM = "yyyy-MM";
    public final static String MMDD = "MM-dd";
    public final static String HHMM = "HH:mm";
    public final static String MMDDHHMM = "MM-dd HH:mm";
    public final static String MMDDHHMM_CN = "MM月dd日 HH:mm";
    public final static String MMDD_CN = "MM月dd日";

    // 时间单位
    public enum TimeUnitEnum {
        YEAR, MONTH, DAY, HOUR, MINUTE, SECOND;
    }

    // 小于、等于、大于
    public enum ContrastSignEnum {
        LESS, EQUAL, GREATER;
    }

    public static String format() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYYMMDDHHMMSS);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return dateFormat.format(new Date());
    }

    public static String format(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYYMMDDHHMMSS);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return dateFormat.format(d);
    }

    /**
     * 获取指定格式的当前时间
     * 
     * @param format
     *            时间格式，若为空，默认为yyyy-MM-dd HH:mm:ss
     * @return String
     */
    public static String format(String format) {
        if (StringUtils.isBlank(format)) {
            format = YYYYMMDDHHMMSS;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return dateFormat.format(new Date());
    }

    /**
     * 获取指定时间指定格式的时间
     * 
     * @param date
     *            指定时间，若为空，默认当前时间
     * @param format
     *            时间格式，若为空，默认为yyyy-MM-dd HH:mm:ss
     * @return String
     */
    public static String format(Date date, String format) {
        if (StringUtils.isBlank(format)) {
            format = YYYYMMDDHHMMSS;
        }
        if (null == date) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return dateFormat.format(date);
    }

    /**
     * 获取去年月初(yyyy-mm-dd)
     * 
     * @param month
     *            月份(正数)
     */
    public static String lastYearMonthStart(int month) {
        Date date = new Date();
        date = DateUtils.addYears(date, -1);
        date = DateUtils.addMonths(date, month);
        date = DateUtils.setDays(date, 1);
        return DateFormatUtils.format(date, DateFormatUtils.ISO_DATE_FORMAT.getPattern());
    }

    /**
     * 获取月初(yyyy-mm-dd)
     * 
     * @param month
     *            月份(正数)
     */
    public static String monthStart(int month) {
        Date date = DateUtils.addMonths(new Date(), month);
        date = DateUtils.setDays(date, 1);
        return DateFormatUtils.format(date, DateFormatUtils.ISO_DATE_FORMAT.getPattern());
    }

    /**
     * 获取指定时间的月初(yyyy-mm-dd)
     * 
     * @param date
     *            指定时间，为空则取当前系统时间
     */
    public static String monthStart(Date date) {
        if (null == date) {
            date = new Date();
        }
        date = DateUtils.setDays(date, 1);
        return DateFormatUtils.format(date, YYYYMMDD);
    }

    /**
     * 获取指定时间的月初
     *
     * @param date
     *            指定时间，为空则取当前系统时间
     */
    public static String monthStart(Date date, String format) {
        if (null == date) {
            date = new Date();
        }
        date = DateUtils.setDays(date, 1);
        return DateFormatUtils.format(date, StringUtils.isBlank(format) ? YYYYMMDD : format);
    }

    /**
     * 获取月初
     * 
     * @param month
     *            月份(正数)
     * @param format
     *            时间格式
     */
    public static String monthStart(int month, String format) {
        Date date = DateUtils.addMonths(new Date(), month);
        date = DateUtils.setDays(date, 1);
        return DateFormatUtils.format(date, format);
    }

    /**
     * 获取天开始(yyyy-mm-dd HH:mm:ss)
     * 
     * @param day
     *            天(正数)
     */
    public static String dayStart(int day) {
        Date date = DateUtils.setDays(new Date(), day < 0 ? 0 : day);
        date = DateUtils.setHours(date, 0);
        date = DateUtils.setMinutes(date, 0);
        date = DateUtils.setSeconds(date, 0);
        return DateFormatUtils.format(date, YYYYMMDDHHMMSS);
    }

    /**
     * 获取指定日期的天的开始(yyyy-mm-dd HH:mm:ss)
     * 
     * @param date
     *            指定日期
     */
    public static String dayStartReturnStr(Date date) {
        if (null == date) {
            date = new Date();
        }
        date = DateUtils.setHours(date, 0);
        date = DateUtils.setMinutes(date, 0);
        date = DateUtils.setSeconds(date, 0);
        return DateFormatUtils.format(date, YYYYMMDDHHMMSS);
    }

    /**
     * 获取指定日期的天的开始(yyyy-mm-dd HH:mm:ss)
     * 
     * @param date
     *            指定日期
     */
    public static Date dayStartReturnDate(Date date) {
        if (null == date) {
            date = new Date();
        }
        date = DateUtils.setHours(date, 0);
        date = DateUtils.setMinutes(date, 0);
        date = DateUtils.setSeconds(date, 0);
        return date;
    }

    /**
     * 获取天结束(yyyy-mm-dd HH:mm:ss)
     * 
     * @param day
     *            天(正数)
     */
    public static String dayEnd(int day) {
        Date date = DateUtils.setDays(new Date(), day < 0 ? 0 : day);
        date = DateUtils.setHours(date, 23);
        date = DateUtils.setMinutes(date, 59);
        date = DateUtils.setSeconds(date, 59);
        return DateFormatUtils.format(date, YYYYMMDDHHMMSS);
    }

    /**
     * 获取指定日期(天)的结束(yyyy-mm-dd HH:mm:ss)
     * 
     * @param date
     *            指定日期(天)
     */
    public static String dayEnd(Date date) {
        if (null != date) {
            date = DateUtils.setHours(date, 23);
            date = DateUtils.setMinutes(date, 59);
            date = DateUtils.setSeconds(date, 59);
            return DateFormatUtils.format(date, YYYYMMDDHHMMSS);
        }
        return null;
    }
    
    /**
     * 获取指定日期(天)的结束(yyyy-mm-dd HH:mm:ss)
     * 
     * @param date
     *            指定日期(天)
     */
    public static Date dayEndReturnDate(Date date) {
        if (null == date) {
            date = new Date();
        }
        date = DateUtils.setHours(date, 23);
        date = DateUtils.setMinutes(date, 59);
        date = DateUtils.setSeconds(date, 59);
        return date;
    }

    /**
     * 获取指定日期(天)的结束(yyyy-mm-dd HH:mm:ss)
     * 
     * @param date
     *            指定日期(天)
     */
    public static Date dayEndForDate(Date date) {
        if (null != date) {
            date = DateUtils.setHours(date, 23);
            date = DateUtils.setMinutes(date, 59);
            date = DateUtils.setSeconds(date, 59);
        }
        return date;
    }

    /**
     * 获取几天前或后开始(yyyy-mm-dd HH:mm:ss)
     * 
     * @param day
     *            天(正数)
     */
    public static String dayAddStart(int day) {
        Date date = DateUtils.addDays(new Date(), day);
        date = DateUtils.setHours(date, 0);
        date = DateUtils.setMinutes(date, 0);
        date = DateUtils.setSeconds(date, 0);
        return DateFormatUtils.format(date, YYYYMMDDHHMMSS);
    }

    /**
     * 获取几天前或后结束(yyyy-mm-dd HH:mm:ss)
     * 
     * @param day
     *            天(正数)
     */
    public static String dayAddEnd(int day) {
        Date date = DateUtils.addDays(new Date(), day);
        date = DateUtils.setHours(date, 23);
        date = DateUtils.setMinutes(date, 59);
        date = DateUtils.setSeconds(date, 59);
        return DateFormatUtils.format(date, YYYYMMDDHHMMSS);
    }

    /**
     * 获取月份的列表
     * 
     * @param num
     *            月份的数量，取值[1,12]
     */
    public static List<Integer> monthList(int num) {
        Calendar cal = Calendar.getInstance();
        List<Integer> monthList = new ArrayList<Integer>();
        if (num < 1) {
            num = 1;
        } else if (num > 12) {
            num = 12;
        }
        Date date = new Date();
        for (int i = 1; i <= num; i++) {
            cal.setTime(DateUtils.addMonths(date, i - num));
            monthList.add(cal.get(Calendar.MONTH) + 1);
        }
        return monthList;
    }

    /**
     * 获取当前年份
     */
    public static int curYear() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     */
    public static int curMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前天
     */
    public static int curDay() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 时间转换，默认格式yyyy-MM-dd HH:mm:ss
     * 要求时间date必须与format一致
     */
    public static Date parse(String date, String format) {
        if (StringUtils.isNotBlank(date)) {
            if (StringUtils.isBlank(format)) {
                format = YYYYMMDDHHMMSS;
            }
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
                return dateFormat.parse(date);
            } catch (ParseException e) {
                log.warn("时间转换异常", e);
            }
        }
        return null;
    }

    /**
     * 计算两个时间点之间的时长(四舍五入保留两位小数)
     * 
     * @param first
     *            开始时间
     * @param second
     *            结束时间
     * @param timeUnitEnum
     *            计算后单位(默认小时)
     */
    public static BigDecimal calDate(Date first, Date second, TimeUnitEnum timeUnitEnum) {
        if (null != first && null != second) {
            BigDecimal cal = new BigDecimal(second.getTime() - first.getTime());
            if (null == timeUnitEnum) {
                timeUnitEnum = TimeUnitEnum.HOUR;
            }
            BigDecimal divisor = new BigDecimal(60 * 60 * 1000);
            if (TimeUnitEnum.YEAR.equals(timeUnitEnum)) {
                divisor = new BigDecimal(365l * 30l * 24l * 60l * 60l * 1000l);
            } else if (TimeUnitEnum.MONTH.equals(timeUnitEnum)) {
                divisor = new BigDecimal(30l * 24l * 60l * 60l * 1000l);
            } else if (TimeUnitEnum.DAY.equals(timeUnitEnum)) {
                divisor = new BigDecimal(24l * 60l * 60l * 1000l);
            } else if (TimeUnitEnum.MINUTE.equals(timeUnitEnum)) {
                divisor = new BigDecimal(60 * 1000);
            } else if (TimeUnitEnum.SECOND.equals(timeUnitEnum)) {
                divisor = new BigDecimal(1000);
            }
            return cal.divide(divisor, 2, RoundingMode.HALF_UP);
        }
        return null;
    }

    /**
     * 构造时间
     * 
     * @param day
     *            当前时间的前或后的某天(例如：0,代表今天;-1,代表昨天;1,代表明天)
     * @param hour
     *            指定的小时(范围:[0,23]),若不在此范围，则默认当前小时
     * @param minutes
     *            指定的分钟(范围:[0,59]),若不在此范围，则默认当前分钟
     * @param seconds
     *            指定的秒(范围:[0,59]),若不在此范围，则默认当前秒
     * @return Date
     */
    public static Date buildDate(int day, int hour, int minutes, int seconds) {
        Date date = new Date();
        date = DateUtils.addDays(date, day);
        if (hour >= 0 && hour <= 23) {
            date = DateUtils.setHours(date, hour);
        }
        if (minutes >= 0 && minutes <= 59) {
            date = DateUtils.setMinutes(date, minutes);
        }
        if (seconds >= 0 && seconds <= 59) {
            date = DateUtils.setSeconds(date, seconds);
        }
        return date;
    }

    /**
     * 判断(指定的时间与当前时间之差)与指定时长的大小，当指的的时间为空时，返回null
     * 
     * @param date
     *            指定的时间
     * @param seconds
     *            指定时长(单位：秒)
     * @return Integer 小于:-1; 等于:0; 大于:1
     */
    public static Integer checkOvertime(Date date, int seconds) {
        if (null != date) {
            BigDecimal dateLen = calDate(new Date(), date, TimeUnitEnum.SECOND);
            if (null != dateLen) {
                return dateLen.compareTo(new BigDecimal(seconds));
            }
        }
        return null;
    }

    /**
     * 手机端列表时间显示
     * 
     * @param date
     * @return String
     */
    public static String buildDateFormat(Date date) {
        if (null != date) {
            Calendar sourceDay = Calendar.getInstance();
            sourceDay.setTime(date);
            sourceDay.set(Calendar.HOUR_OF_DAY, 0);
            sourceDay.set(Calendar.MINUTE, 0);
            sourceDay.set(Calendar.SECOND, 0);
            sourceDay.set(Calendar.MILLISECOND, 0);
            
            Calendar today = Calendar.getInstance();
            today.setTime(new Date());
            today.set(Calendar.HOUR_OF_DAY, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            today.set(Calendar.MILLISECOND, 0);
            
            Calendar tomorrow = Calendar.getInstance();
            tomorrow.setTime(today.getTime());
            tomorrow.add(Calendar.DAY_OF_MONTH, 1);
            tomorrow.set(Calendar.HOUR_OF_DAY, 0);
            tomorrow.set(Calendar.MINUTE, 0);
            tomorrow.set(Calendar.SECOND, 0);
            tomorrow.set(Calendar.MILLISECOND, 0);
            
            Calendar afterTomorrow = Calendar.getInstance();
            afterTomorrow.setTime(today.getTime());
            afterTomorrow.add(Calendar.DAY_OF_MONTH, 2);
            afterTomorrow.set(Calendar.HOUR_OF_DAY, 0);
            afterTomorrow.set(Calendar.MINUTE, 0);
            afterTomorrow.set(Calendar.SECOND, 0);
            afterTomorrow.set(Calendar.MILLISECOND, 0);
            
            if (sourceDay.getTime().getTime() == today.getTime().getTime()) {
                return "今天 " + DateFormatUtils.format(date, HHMM);
            } else if (sourceDay.getTime().getTime() == tomorrow.getTime().getTime()) {
                return "明天 " + DateFormatUtils.format(date, HHMM);
            } else if (sourceDay.getTime().getTime() == afterTomorrow.getTime().getTime()) {
                return "后天 " + DateFormatUtils.format(date, HHMM);
            }
            return DateFormatUtils.format(date, MMDDHHMM_CN);
        }
        return null;
    }

    /**
     * 根据指定的时间格式比较时间
     * 
     * @param first
     *            第一个时间，若为空，结果返回null
     * @param second
     *            第二个时间，若为空，结果返回null
     * @param format
     *            时间格式
     * @return first < second : -1; first = second : 0; first > second : 1;
     */
    public static Integer compare(Date first, Date second, String format) {
        if (null != first && null != second) {
            if (StringUtils.isNotBlank(format)) {
                first = parse(format(first), format);
                second = parse(format(second), format);
            }
            return first.compareTo(second);
        }
        return null;
    }

    /**
     * 
     * 取得当前日期所在周的第一天
     * 
     * @author Libin.Wei
     * @Date 2017年7月11日 上午11:40:29
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        if (null == date) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        return calendar.getTime();
    }

    /**
     * 
     * 取得当前日期所在周的最后一天
     * 
     * @author Libin.Wei
     * @Date 2017年7月11日 上午11:40:39
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        if (null == date) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek() + 6);
        return calendar.getTime();
    }
    
    /**
     * 
     * 得到某年某周的第一天
     * @author Libin.Wei
     * @Date 2017年7月11日 上午11:48:22
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);

        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getFirstDayOfWeek(cal.getTime());
    }
    
    /**
     * 
     * 得到某年某周的最后一天
     * @author Libin.Wei
     * @Date 2017年7月11日 上午11:45:35
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DATE, 1);
        Calendar cal = (Calendar) calendar.clone();
        cal.add(Calendar.DATE, week * 7);

        return getLastDayOfWeek(cal.getTime());
    }

    /**
     * 取得当前日期所在周的前一周第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfLastWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getFirstDayOfWeek(calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.WEEK_OF_YEAR) - 1);
    }
    
    /**
     * 取得当前日期所在周的前一周最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfLastWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getLastDayOfWeek(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.WEEK_OF_YEAR) - 1);
    }
    
    /**
     * 
     * 返回指定日期的月的第一天
     * @author Libin.Wei
     * @Date 2017年7月11日 上午11:52:47
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                     calendar.get(Calendar.MONTH), 1);
        return calendar.getTime();
    }

    /**
     * 
     * 返回指定日期的月的最后一天
     * @author Libin.Wei
     * @Date 2017年7月11日 上午11:54:03
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR),
                     calendar.get(Calendar.MONTH), 1);
        calendar.roll(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 
     * 返回指定年月的月的指定天
     * @author Libin.Wei
     * @Date 2017年7月11日 上午11:55:30
     * @param year
     * @param month
     * @return
     */
    public static Date getDayOfMonth(Integer year, Integer month, Integer day) {
        Calendar calendar = Calendar.getInstance();
        if (year == null) {
            year = calendar.get(Calendar.YEAR);
        }
        if (month == null) {
            month = calendar.get(Calendar.MONTH);
        }
        month = month - 1;
        if (day == null) {
            day = calendar.get(Calendar.DAY_OF_MONTH);
        }
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    public static float betweenDays (Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();  
        fromCalendar.setTime(startDate);  
        fromCalendar.set(Calendar.SECOND, 0);  
        fromCalendar.set(Calendar.MILLISECOND, 0);  
  
        Calendar toCalendar = Calendar.getInstance();  
        toCalendar.setTime(endDate);  
        toCalendar.set(Calendar.SECOND, 0);  
        toCalendar.set(Calendar.MILLISECOND, 0);  
  
        return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);  
    }
    
    public static void main(String[] args) {
        System.out.println(DateUtil.format(new Date(), "yyyy年MM月dd HH:mm"));
        
        System.out.println(buildDateFormat(new Date()));
        
        System.out.println(buildDateFormat(DateUtil.parse("2018-06-11 22:22:22", "yyyy-MM-dd HH:mm:ss")));
        
        System.out.println(buildDateFormat(DateUtil.parse("2018-06-12 22:22:22", "yyyy-MM-dd HH:mm:ss")));
    }
}
