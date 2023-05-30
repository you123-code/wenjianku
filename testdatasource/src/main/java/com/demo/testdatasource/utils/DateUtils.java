package com.demo.testdatasource.utils;
import org.apache.commons.lang3.StringUtils;
import javax.ws.rs.InternalServerErrorException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SuppressWarnings({"unused"})
public class DateUtils {

    private DateUtils(){}

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.CHINA);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss", Locale.CHINA);
    public static final DateTimeFormatter DATE_DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
    public static final ZoneOffset ZONE_OFFSET_CHINA = ZoneOffset.ofHours(8);

    /**
     * 获取当天 YYYYMMDDHHmmss 格式
     */
    public static String getStringDate() {
        return LocalDateTime.now().format(DATE_DEFAULT_FORMATTER);
    }

    /**
     * 获取当前时间时间戳的字符串格式
     */
    public static String getStringTimestamp(){
        return LocalDateTime.now().toInstant(ZONE_OFFSET_CHINA).toEpochMilli()+"";
    }

    /**
     * 计算一个日期距离1970-01-01的天数
     */

    public static int differDay(String date) {
        if (StringUtils.isEmpty(date)) {
            return -1;
        }
        try {
            LocalDate endDate = LocalDate.parse(date, DATE_FORMATTER);
            long daysDiff = endDate.toEpochDay();
            return daysDiff < 0 ? -1 : (int) daysDiff;
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * 计算一个日期距离1970-01-01的天数
     */
    public static int differDay(Long date) {
        try {
            long day = Instant.ofEpochMilli(date).atZone(ZONE_OFFSET_CHINA).toLocalDate().toEpochDay();
            return day < 0 ? -1 : (int) day;
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    public static boolean isValidDate(String str, String dateFmt) {
        if (str == null) {
            return false;
        }
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy-MM-dd区分大小写；
        //"yyyy-MM-dd"
        SimpleDateFormat format = new SimpleDateFormat(dateFmt);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }

    /**
     * 获取当天 yyyy-MM-dd 格式
     */
    public static String getDate() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    /**
     * 计算一个日期与今天相隔天数,只支持 yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss 格式字符串
     */
    public static int remainDay(String endDate) {
        if (StringUtils.isEmpty(endDate)) return -1;
        try {
            if (endDate.length() == 10) {
                endDate += " 23:59:59";
            }
            // 用LocalDateTime 通过Duration对比，不满24小时差值均为0，与本方法原意不符。
            LocalDate today = LocalDate.now();
            LocalDate end =  LocalDateTime.parse(endDate,DATE_TIME_FORMATTER).toLocalDate();
            long day = end.toEpochDay() - today.toEpochDay();
            return day < 0 ? -1 : (int) day;
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    /**
     * 将时间戳的毫秒转换成 yyyy-MM-dd HH:mm:ss 格式的字符串
     */
    public static String date(String timestampStr) {
        if (StringUtils.isEmpty(timestampStr)) {
            return "";
        }
        long timestamp = Long.parseLong(timestampStr);
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(timestamp / 1000L, 0, ZONE_OFFSET_CHINA);
        return dateTime.format(DATE_TIME_FORMATTER);

    }

    /**
     * 将Date转换成时间戳的毫秒
     */
    public static int getSecondTimestampTwo(Date date) {
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime() / 1000);
        return Integer.parseInt(timestamp);
    }

    /**
     * 获取系统当前年份
     */
    public static String getSysYear() {
        return LocalDate.now().getYear() + "";
    }

    /**
     * String的日期转换成时间戳毫秒
     */
    public static long date2Ms(String date) {
        if (StringUtils.isEmpty(date)) return -1;
        try {
            if (date.length() == 10) {
                return LocalDate.parse(date, DATE_FORMATTER).atStartOfDay(ZONE_OFFSET_CHINA).toInstant().toEpochMilli();
            } else if (date.length() == 19) {
                return LocalDateTime.parse(date, DATE_DEFAULT_FORMATTER).toInstant(ZONE_OFFSET_CHINA).toEpochMilli();
            } else {
                return LocalDateTime.parse(date, DATE_TIME_FORMATTER).toInstant(ZONE_OFFSET_CHINA).toEpochMilli();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 获取当天的开始时间
     */
    public static Date getDayBegin() {
        return Date.from(LocalDate.now().atStartOfDay(ZONE_OFFSET_CHINA).toInstant());
    }

    /**
     * 获取当天的结束时间
     */
    public static Date getDayEnd() {
        return Date.from(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).toInstant(ZONE_OFFSET_CHINA));
    }

    /**
     * 获取昨天的开始时间
     */
    public static Date getBeginDayOfYesterday() {
        return Date.from(LocalDate.now().atStartOfDay(ZONE_OFFSET_CHINA).minusDays(1).toInstant());
    }

    /**
     * 获取昨天的结束时间
     */
    public static Date getEndDayOfYesterday() {
        return Date.from(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).minusDays(1).toInstant(ZONE_OFFSET_CHINA));
    }

    /**
     * 获取明天的开始时间
     */
    public static Date getBeginDayOfTomorrow() {
        return Date.from(LocalDate.now().atStartOfDay(ZONE_OFFSET_CHINA).plusDays(1).toInstant());
    }

    /**
     * 获取明天的结束时间
     */
    public static Date getEndDayOfTomorrow() {
        return Date.from(LocalDateTime.of(LocalDate.now(), LocalTime.MAX).plusDays(1).toInstant(ZONE_OFFSET_CHINA));
    }

    /**
     * 获取本周的开始时间
     */
    public static Date getBeginDayOfWeek() { //优化前的方法返回Timestamp
        return Timestamp.from(LocalDate.now().with(DayOfWeek.MONDAY).atStartOfDay(ZONE_OFFSET_CHINA).toInstant());
    }

    /**
     * 获取本周的结束时间
     */
    public static Date getEndDayOfWeek() { //优化前的方法返回Timestamp
        return Timestamp.from(LocalDateTime.of(LocalDate.now().with(DayOfWeek.SUNDAY), LocalTime.MAX).toInstant(ZONE_OFFSET_CHINA));
    }

    /**
     * 获取本月的开始时间
     */
    public static Date getBeginDayOfMonth() {
        LocalDate firstDay = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        return Timestamp.from(LocalDateTime.of(firstDay, LocalTime.MIN).toInstant(ZONE_OFFSET_CHINA));
    }

    /**
     * 获取本月的结束时间
     */
    public static Date getEndDayOfMonth() {
        LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        return Timestamp.from(LocalDateTime.of(lastDay, LocalTime.MAX).toInstant(ZONE_OFFSET_CHINA));
    }


    /**
     * 获取上个月的开始时间
     */
    public static Date getBeginDayOfLastMonth() {
        LocalDate firstDay = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).minusMonths(1);
        return Timestamp.from(LocalDateTime.of(firstDay, LocalTime.MIN).toInstant(ZONE_OFFSET_CHINA));
    }

    /**
     * 获取上个月的结束时间
     */
    public static Date getEndDayOfLastMonth() {
        LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).minusMonths(1);
        return Timestamp.from(LocalDateTime.of(lastDay, LocalTime.MAX).toInstant(ZONE_OFFSET_CHINA));
    }

    /**
     *
     */
    public static Date getLastDayOfLastMonth() {
        LocalDate lastDay = LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        return Timestamp.from(LocalDateTime.of(lastDay, LocalTime.MAX).toInstant(ZONE_OFFSET_CHINA));
    }

    /**
     * 获取本年的开始时间
     */
    public static Date getBeginDayOfYear() {
        LocalDate firstDay = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
        return Timestamp.from(LocalDateTime.of(firstDay, LocalTime.MIN).toInstant(ZONE_OFFSET_CHINA));
    }

    /**
     * 获取本年的结束时间
     */
    public static Date getEndDayOfYear() {
        LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfYear());
        return Timestamp.from(LocalDateTime.of(lastDay, LocalTime.MAX).toInstant(ZONE_OFFSET_CHINA));
    }

    /**
     * 获取某个日期的开始时间
     */
    public static Timestamp getDayStartTime(Date d) {
        LocalDateTime dayStart = LocalDateTime.of(d.toInstant().atZone(ZONE_OFFSET_CHINA).toLocalDate(), LocalTime.MIN);
        return Timestamp.from(dayStart.toInstant(ZONE_OFFSET_CHINA));
    }

    /**
     * 获取某个日期的结束时间
     */
    public static Timestamp getDayEndTime(Date d) {
        LocalDateTime dayStart = LocalDateTime.of(d.toInstant().atZone(ZONE_OFFSET_CHINA).toLocalDate(), LocalTime.MAX);
        return Timestamp.from(dayStart.toInstant(ZONE_OFFSET_CHINA));
    }

    /**
     * 获取今年是哪一年
     */
    public static Integer getNowYear() {
        return LocalDate.now().getYear();
    }

    /**
     * 获取本月是哪一月
     */
    public static int getNowMonth() {
        return LocalDate.now().getMonthValue();
    }

    /**
     * 两个日期相减得到的天数
     */
    public static int getDiffDays(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }
        LocalDate start = beginDate.toInstant().atZone(ZONE_OFFSET_CHINA).toLocalDate();
        LocalDate end = endDate.toInstant().atZone(ZONE_OFFSET_CHINA).toLocalDate();
        return (int) (end.toEpochDay() - start.toEpochDay());
    }

    /**
     * 两个日期相减得到的毫秒数
     */
    public static long dateDiff(Date beginDate, Date endDate) {
        long date1ms = beginDate.getTime();
        long date2ms = endDate.getTime();
        return date2ms - date1ms;
    }


    /**
     * 获取两个日期中的最大日期
     */
    public static Date max(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        return dateDiff(beginDate, endDate) > 0 ? endDate : beginDate;
    }

    /**
     * 获取两个日期中的最小日期
     */
    public static Date min(Date beginDate, Date endDate) {
        if (beginDate == null) {
            return endDate;
        }
        if (endDate == null) {
            return beginDate;
        }
        return dateDiff(beginDate, endDate) > 0 ? beginDate : endDate;
    }

    /**
     * 返回日期中表示该季度中第一个月的时间。
     * 如：param为 2020-08-14 15:20:36，返回 2020-07-14 15:20:36，
     * param为 2020-06-14 15:20:36，返回 2020-04-14 15:20:36
     */
    public static Date getFirstSeasonDate(Date date) {
        int[] season = {1, 1, 1, 4, 4, 4, 7, 7, 7, 10, 10, 10};
        LocalDateTime d = date.toInstant().atZone(ZONE_OFFSET_CHINA).toLocalDateTime();
        return Date.from(d.withMonth(season[d.getMonthValue() - 1]).toInstant(ZONE_OFFSET_CHINA));
    }

    /**
     * 获取指定日期后几天的日期
     */
    public static Date getNextDay(Date date, int i) {
        LocalDateTime newDate = date.toInstant().atZone(ZONE_OFFSET_CHINA).toLocalDateTime().plusDays(i);
        return Date.from(newDate.toInstant(ZONE_OFFSET_CHINA));
    }

    /**
     * 获取指定日期前几天的日期
     */
    public static Date getFrontDay(Date date, int i) {
        LocalDateTime newDate = date.toInstant().atZone(ZONE_OFFSET_CHINA).toLocalDateTime().minusDays(i);
        return Date.from(newDate.toInstant(ZONE_OFFSET_CHINA));
    }

    /**
     * 获取某年某月到某年某月按天的切片日期集合（间隔天数的日期集合）
     */
    public static List<List<Date>> getTimeList(int beginYear, int beginMonth, int endYear, int endMonth, int k) {
        List<List<Date>> list = new ArrayList<>();
        if (beginYear == endYear) {
            for (int j = beginMonth; j <= endMonth; j++) {
                list.add(getTimeList(beginYear, j, k));

            }
        } else {
            for (int j = beginMonth; j < 12; j++) {
                list.add(getTimeList(beginYear, j, k));
            }

            for (int i = beginYear + 1; i < endYear; i++) {
                for (int j = 0; j < 12; j++) {
                    list.add(getTimeList(i, j, k));
                }
            }
            for (int j = 0; j <= endMonth; j++) {
                list.add(getTimeList(endYear, j, k));
            }
        }
        return list;
    }

    /**
     * 获取某年某月按天切片日期集合（某个月间隔多少天的日期集合）
     */
    public static List<Date> getTimeList(int beginYear, int beginMonth, int k) {
        List<Date> list = new ArrayList<>();
        LocalDate date = LocalDate.of(beginYear, beginMonth + 1, 1);
        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        int max = lastDay.getDayOfMonth();
        for (long i = 1; i < max; i = i + k) {
            LocalDate newDate = date.plusDays(i - 1L);
            list.add(Date.from(newDate.atStartOfDay(ZONE_OFFSET_CHINA).toInstant()));
        }
        list.add(Date.from(lastDay.atStartOfDay(ZONE_OFFSET_CHINA).toInstant()));
        return list;
    }

    /**
     * 获取某年某月的第一天日期
     */
    public static Date getStartMonthDate(int year, int month) {
        LocalDateTime date = LocalDateTime.now().withYear(year).withMonth(month).with(TemporalAdjusters.firstDayOfMonth());
        return Date.from(date.toInstant(ZONE_OFFSET_CHINA));
    }

    /**
     * 获取某年某月的最后一天日期
     */
    public static Date getEndMonthDate(int year, int month) {
        LocalDateTime date = LocalDateTime.now().withYear(year).withMonth(month).with(TemporalAdjusters.lastDayOfMonth());
        return Date.from(date.toInstant(ZONE_OFFSET_CHINA));
    }

    /**
     * 获取i月前月份
     */
    public static int getMonth(int i) {
        LocalDateTime dateTime = LocalDateTime.now().minusMonths(i);
        return dateTime.getMonthValue();
    }

    /**
     * 获取i月前年份
     */
    public static int getYear(int i) {
        LocalDateTime dateTime = LocalDateTime.now().minusMonths(i);
        return dateTime.getYear();
    }

    /**
     * 按照指定格式来格式化日期
     */
    public static String dateFormat(Date date, String strFormat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(strFormat, Locale.CHINA);
        return LocalDateTime.ofInstant(date.toInstant(), ZONE_OFFSET_CHINA).format(formatter);
    }
}
