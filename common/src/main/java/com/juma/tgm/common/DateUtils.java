package com.juma.tgm.common;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * 功能点:
 * 基本功能:获取当前时间
 * 一.日期的格式转换
 * 1.日期对象转字符串
 * 2.日期对象转时间戳
 * 3.时间戳转日期对象
 * 4.时间戳转字符串
 * 二.日期的加减
 * 1.当前日期加减N天
 * 2.当前日期加减N周
 * 3.当前日期加减N月
 * 4.当前日期加减N年
 * 三.日期坐标转换
 * 1.指定日期是本周的周几
 * 2.指定日期是本年的第几周
 * 四.日期单位的特殊点
 * 1.根据日期,获取当前的起止时间戳
 * 2.根据日期,获取本周的第一天的0点时间戳
 * 3.根据日期,获取本周的最后一天的23:59:59的时间戳
 * 4.根据日期,获取本月的第一天的0点时间戳
 * 5.根据日期,获取本月的最后一天的23:59:59的时间戳
 * 6.根据日期,获取本年的第一天的0点时间戳
 * 7.根据日期,获取本年的最后一天的23:59:59的时间戳
 * 五.日期大小的比较
 * 1.两个日期,比较返回大小
 * 2.两个日期,判断日期是否在2个日期之间
 * 3.计算两个日期之间的差额(按年算)
 * 4.计算两个日期之间的差额(按月算)
 * 5.计算两个日期之间的差额(按天算)
 * 6.计算两个日期之间的差额(按小时算)
 * 7.计算两个日期之间的差额(按分钟算)
 * 8.计算两个日期之间的差额(按秒算)
 * 9.计算两个日期之间的差额(按毫秒算)
 * 六.日期截断
 * 1.获取指定日期的天的毫秒数
 * 2.获取指定日期的小时的毫秒数
 * 3.获取指定日期的分的毫秒数
 * 4.获取指定日期的秒的毫秒数
 * 七.获取时间段
 * 1.获取起止日期之间的所有日期列表
 * 2.获取起止日期之间的所有周列表
 * 3.获取起止日期之间的所有月列表
 *
 * // TODO 刘正航 后续增加方法,请在这里做备注,谢谢合作.
 * @author : Bruce(刘正航) 下午11:35 2018/03/19
 */
public class DateUtils {

    /**
     * 日期格式枚举类
     */
    public enum Parttern {

        /**中杠分隔符**/
        FORMAT_YYMM_MID("yyyy-MM"),
        FORMAT_YYWW_MID("yyyy-ww"),
        FORMAT_YYMMDD_MID("yyyy-MM-dd"),
        FORMAT_YYMMDDH_MID("yyyy-MM-dd HH"),
        FORMAT_YYMMDDHM_MID("yyyy-MM-dd HH:mm"),
        FORMAT_YYMMDDHMS_MID("yyyy-MM-dd HH:mm:ss"),
        FORMAT_YYMMDDHMSS_MID("yyyy-MM-dd HH:mm:ss SSS"),
        /**无隔符**/
        FORMAT_YYMM_NON("yyyyMM"),
        FORMAT_YYWW_NON("yyyyww"),
        FORMAT_YYMMDD_NON("yyyyMMdd"),
        FORMAT_YYMMDDH_NON("yyyyMMddHH"),
        FORMAT_YYMMDDHM_NON("yyyyMMddHHmm"),
        FORMAT_YYMMDDHMS_NON("yyyyMMddHHmmss"),
        FORMAT_YYMMDDHMSS_NON("yyyyMMddHHmmssSSS"),

        /**斜杠分隔符**/
        FORMAT_YYMM_SLASH("yyyy/MM"),
        FORMAT_YYWW_SLASH("yyyy/ww"),
        FORMAT_YYMMDD_SLASH("yyyy/MM/dd"),
        FORMAT_YYMMDDH_SLASH("yyyy/MM/dd HH"),
        FORMAT_YYMMDDHM_SLASH("yyyy/MM/dd HH:mm"),
        FORMAT_YYMMDDHMS_SLASH("yyyy/MM/dd HH:mm:ss"),
        FORMAT_YYMMDDHMSS_SLASH("yyyy/MM/dd HH:mm:ss:SSS"),

        /**中文**/
        FORMAT_YYMM_ZH("yyyy年MM月"),
        FORMAT_YYWW_ZH("yyyy年ww周"),
        FORMAT_YYMMDD_ZH("yyyy年MM月dd日"),
        FORMAT_YYMMDDH_ZH("yyyy年MM月dd日 HH点"),
        FORMAT_YYMMDDHM_ZH("yyyy年MM月dd日 HH点mm分"),
        FORMAT_YYMMDDHMS_ZH("yyyy年MM月dd日 HH点mm分ss秒"),
        FORMAT_YYMMDDHMSS_ZH("yyyy年MM月dd日 HH点mm分ss秒SSS毫秒"),

        ;

        private String fmt;

        Parttern(String fmt) {
            this.fmt = fmt;
        }

        public String getFmt() {
            return fmt;
        }
    }

    /**新建时间对象**/
    public static DateTime create(){
        return DateTime.now();
    }

    /**新建时间对象**/
    public static DateTime create(Date date){
        return new DateTime(date);
    }

    /**注意:timestamp为13位,否则新建出来的日期是1970年1月18号**/
    public static DateTime create(long timestamp){
        return new DateTime(timestamp);
    }

    /**
     * 基本功能:获取当前时间
     * 一.日期的格式转换
     * 1.日期对象转字符串 {@link #format(DateTime)}
     * 2.日期对象转时间戳 {@link #create().getMillis() }
     * 3.时间戳转日期对象 {@link #create(long timestamp) }
     * 4.时间戳转字符串 {@link #create(long timestamp)} --> {@link #format(DateTime)}
     */

    /**默认日期转换格式:yyyy-MM-dd HH:mm:ss**/
    public static String format(DateTime dateTime){
        return format(dateTime, Parttern.FORMAT_YYMMDDHMS_MID);
    }

    /**默认日期转换格式:yyyy-MM-dd HH:mm:ss**/
    public static String format(Date date){
        return format(date, Parttern.FORMAT_YYMMDDHMS_MID);
    }

    public static String format(DateTime dateTime, Parttern fmt){
        return dateTime.toString(getFormatter(fmt));
    }

    public static String format(Date date, Parttern fmt){
        DateTime dateTime = create(date);
        return dateTime.toString(getFormatter(fmt));
    }

    public static String format(DateTime dateTime, String format){
        return dateTime.toString(getFormatter(format));
    }

    public static String format(Date date, String format){
        DateTime dateTime = create(date);
        return dateTime.toString(getFormatter(format));
    }

    public static DateTime parse(String date){
        return parse(date, Parttern.FORMAT_YYMMDDHMS_MID);
    }

    /**转换日期**/
    public static DateTime parse(String date, Parttern fmt){
        return getFormatter(fmt).parseDateTime(date);
    }

    /**转换日期**/
    public static DateTime parse(String date, String format){
        return getFormatter(format).parseDateTime(date);
    }

    /**
     * 二.秒的加减
     * 0.当前日期加减N秒
     */
    public static DateTime addSeconds(DateTime dateTime,int seconds){
        return dateTime.plusSeconds(seconds);
    }

    /**
     * 二.分钟的加减
     * 0.当前日期加减N分钟
     */
    public static DateTime addMinutes(DateTime dateTime,int minutes){
        return dateTime.plusMinutes(minutes);
    }

    /**
     * 二.小时的加减
     * 0.当前日期加减N小时
     */
    public static DateTime addHours(DateTime dateTime,int hours){
        return dateTime.plusHours(hours);
    }

    /**
     * 二.日期的加减
     * 1.当前日期加减N天
     */
    public static DateTime addDays(DateTime dateTime,int days){
        return dateTime.plusDays(days);
    }

    /**
     * 2.当前日期加减N周
     */
    public static DateTime addWeeks(DateTime dateTime,int weeks){
        return dateTime.plusWeeks(weeks);
    }

    /**
     * 3.当前日期加减N月
     */
    public static DateTime addMonths(DateTime dateTime,int months){
        return dateTime.plusMonths(months);
    }

    /**
     * 4.当前日期加减N年
     */
    public static DateTime addYears(DateTime dateTime,int years){
        return dateTime.plusYears(years);
    }


    /**
     * 三.日期坐标转换
     * 1.指定日期是本周的周几
     * 2.指定日期是本年的第几周
     */

    public static int getDayOfWeek(DateTime dateTime){
        return dateTime.getDayOfWeek();
    }

    public static int getWeekOfYear(DateTime dateTime){
        return dateTime.getWeekOfWeekyear();
    }


    /**
     * 四.日期单位的特殊点
     * 1.根据日期,获取本年的第一天的0点时间 {@link #getYearOfBegin(DateTime)}
     * 2.根据日期,获取本年的最后一天的23:59:59的时间 {@link #getYearOfEnd(DateTime)}
     * 3.根据日期,获取本月的第一天的0点时间 {@link #getMonthOfBegin(DateTime)}
     * 4.根据日期,获取本月的最后一天的23:59:59的时间 {@link #getMonthOfEnd(DateTime)}
     * 5.根据日期,获取本周的第一天的0点时间 {@link #getWeekOfBegin(DateTime)}
     * 6.根据日期,获取本周的最后一天的23:59:59的时间戳 {@link #getWeekOfEnd(DateTime)}
     * 7.根据日期,获取当前的起止时间 {@link #getDayOfBegin(DateTime)} {@link #getDayOfEnd(DateTime)}
     */

    public static DateTime getYearOfBegin(DateTime dateTime){
        return dateTime.dayOfYear().withMinimumValue().dayOfYear().roundFloorCopy();
    }

    public static DateTime getYearOfEnd(DateTime dateTime){
        return dateTime.dayOfYear().withMaximumValue().dayOfYear().roundCeilingCopy().minusMillis(1);
    }

    public static DateTime getMonthOfBegin(DateTime dateTime){
        return dateTime.dayOfMonth().withMinimumValue().dayOfMonth().roundFloorCopy();
    }

    public static DateTime getMonthOfEnd(DateTime dateTime){
        return dateTime.dayOfMonth().withMaximumValue().dayOfMonth().roundCeilingCopy().minusMillis(1);
    }

    public static DateTime getWeekOfBegin(DateTime dateTime){
        return dateTime.dayOfWeek().withMinimumValue().dayOfWeek().roundFloorCopy();
    }

    public static DateTime getWeekOfEnd(DateTime dateTime){
        return dateTime.dayOfWeek().withMaximumValue().dayOfWeek().roundCeilingCopy().minusMillis(1);
    }

    public static DateTime getDayOfBegin(DateTime dateTime){
        return dateTime.millisOfDay().withMinimumValue().millisOfDay().roundFloorCopy();
    }

    public static DateTime getDayOfEnd(DateTime dateTime){
        return dateTime.millisOfDay().withMaximumValue().millisOfDay().roundCeilingCopy();
    }

    public static DateTime getHourOfBegin(DateTime dateTime){
        return dateTime.hourOfDay().roundFloorCopy();
    }

    public static DateTime getHourOfEnd(DateTime dateTime){
        return dateTime.hourOfDay().roundCeilingCopy().minusMillis(1);
    }

    public static DateTime getMinuteOfBegin(DateTime dateTime){
        return dateTime.minuteOfDay().roundFloorCopy();
    }

    public static DateTime getMinuteOfEnd(DateTime dateTime){
        return dateTime.minuteOfDay().roundCeilingCopy().minusMillis(1);
    }

    public static DateTime getSecondOfBegin(DateTime dateTime){
        return dateTime.secondOfDay().roundFloorCopy();
    }

    public static DateTime getSecondOfEnd(DateTime dateTime){
        return dateTime.secondOfDay().roundCeilingCopy().minusMillis(1);
    }

    /**
     * 五.日期大小的比较
     * 1.两个日期,比较返回大小{@link #compare(DateTime, DateTime)}
     * 2.两个日期,判断日期是否在2个日期之间{@link #between(DateTime, DateTime, DateTime)}
     * 3.计算两个日期之间的差额(按年算){@link #distanceYears(DateTime, DateTime)}
     * 4.计算两个日期之间的差额(按月算){@link #distanceMonths(DateTime, DateTime)}
     * 5.计算两个日期之间的差额(按天算){@link #distanceDays(DateTime, DateTime)}
     * 6.计算两个日期之间的差额(按小时算){@link #distanceHours(DateTime, DateTime)}
     * 7.计算两个日期之间的差额(按分钟算){@link #distanceMinutes(DateTime, DateTime)}
     * 8.计算两个日期之间的差额(按秒算){@link #distanceSeconds(DateTime, DateTime)}
     * 9.计算两个日期之间的差额(按毫秒算){@link #distanceMillis(DateTime, DateTime)}
     *
     * left > right = 1
     * left = right = 0
     * left < right = -1
     */
    public static int compare(DateTime left, DateTime right){
        if( left.isBefore(right) ){
            return 1;
        }
        if( left.isEqual(right) ){
            return 0;
        }
        return -1;
    }

    public static boolean between(DateTime src, DateTime left, DateTime right){
        if( src.isAfter(left) && src.isBefore(right) ){
            return true;
        }
        return false;
    }

    /**计算日期之间的差值:按年算**/
    public static int distanceYears(DateTime left, DateTime right){
        Period p = new Period(left, right, PeriodType.years());
        return p.getYears();
    }

    /**计算日期之间的差值:按月算**/
    public static int distanceMonths(DateTime left, DateTime right){
        Period p = new Period(left, right, PeriodType.months());
        return p.getMonths();
    }

    /**计算日期之间的差值:按天算**/
    public static int distanceDays(DateTime left, DateTime right){
        Period p = new Period(left, right, PeriodType.days());
        return p.getDays();
    }

    /**计算日期之间的差值:按小时算**/
    public static int distanceHours(DateTime left, DateTime right){
        Period p = new Period(left, right, PeriodType.hours());
        return p.getHours();
    }

    /**计算日期之间的差值:按分钟算**/
    public static int distanceMinutes(DateTime left, DateTime right){
        Period p = new Period(left, right, PeriodType.minutes());
        return p.getMinutes();
    }

    /**计算日期之间的差值:按秒算**/
    public static int distanceSeconds(DateTime left, DateTime right){
        Period p = new Period(left, right, PeriodType.seconds());
        return p.getSeconds();
    }

    /**计算日期之间的差值:按毫秒算**/
    public static int distanceMillis(DateTime left, DateTime right){
        Period p = new Period(left, right, PeriodType.millis());
        return p.getMillis();
    }

    /**
     * 六.日期截断
     * 1.获取指定日期的天的毫秒数
     *   2018-04-08 16:33:01 285 -> 2018-04-08 00:00:00 000
     * 2.获取指定日期的小时的毫秒数
     *   2018-04-08 16:33:01 285 -> 2018-04-08 16:00:00 000
     * 3.获取指定日期的分的毫秒数
     *   2018-04-08 16:33:01 285 -> 2018-04-08 16:33:00 000
     * 4.获取指定日期的秒的毫秒数
     *   2018-04-08 16:33:01 285 -> 2018-04-08 16:33:01 000
     */

    /**
     * 七.获取时间段
     * 1.获取起止日期之间的所有日期列表
     * 2.获取起止日期之间的所有周列表
     * 3.获取起止日期之间的所有月列表
     * TODO 刘正航 待完善...
     */

    /*
     ****************************************私有方法区*******************************************
                   _               _                           _    _                 _
                  (_)             | |                         | |  | |               | |
      _ __   _ __  _ __   __ __ _ | |_  ___   _ __ ___    ___ | |_ | |__    ___    __| |
     | '_ \ | '__|| |\ \ / // _` || __|/ _ \ | '_ ` _ \  / _ \| __|| '_ \  / _ \  / _` |
     | |_) || |   | | \ V /| (_| || |_|  __/ | | | | | ||  __/| |_ | | | || (_) || (_| |
     | .__/ |_|   |_|  \_/  \__,_| \__|\___| |_| |_| |_| \___| \__||_| |_| \___/  \__,_|
     | |
     |_|
     ****************************************私有方法区*******************************************
     */

    /**
     * 获取格式解析器
     */
    private static DateTimeFormatter getFormatter(Parttern fmt) {
        return DateTimeFormat.forPattern(fmt.getFmt());
    }

    /**
     * 获取格式解析器
     */
    private static DateTimeFormatter getFormatter(String format) {
        return DateTimeFormat.forPattern(format);
    }
}
