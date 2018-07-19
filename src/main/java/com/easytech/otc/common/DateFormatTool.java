package com.easytech.otc.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期格式化
 */
public class DateFormatTool {

    private static final String data_format_day           = "yyyy-MM-dd";

    private static final String data_format_minute        = "yyyy-MM-dd HH:mm";

    private static final String data_format_time_minute   = "HH:mm";

    private static final String data_format_month         = "MM-dd";

    private static final String data_format_chinese_month = "MM月dd日";

    private static final String data_format_time          = "yyyy-MM-dd HH:mm:ss";

    private static final String data_format_hour          = "HH:mm:ss";

    private static final String data_format_second        = "yyyyMMddHHmmss";

    private static final String data_format_milli_second  = "yyyyMMddHHmmssSSS";

    private static final String data_format_day_chinese   = "yyyy年MM月dd日";

    private static final String data_format_month_chinese = "MM月dd日  HH时mm分";

    static final int[]          DAYS_OF_MONTH             = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    public static String formatDate2ChineseMonthDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(data_format_chinese_month);
        return format.format(date);
    }

    public static String formatDate2MonthDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(data_format_month);
        return format.format(date);
    }

    public static String formatDate2Day(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(data_format_day);
        return format.format(date);
    }

    public static String formatDate2Minute(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(data_format_minute);
        return format.format(date);
    }

    public static String formatDate2Time(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(data_format_time);
        return format.format(date);
    }

    public static String formatDate2TimeMinute(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(data_format_time_minute);
        return format.format(date);
    }

    public static String formatDate2Hour(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(data_format_hour);
        return format.format(date);
    }

    public static String formatDate2Second(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(data_format_second);
        return format.format(date);
    }

    public static String formatDate2MilliSecond(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(data_format_milli_second);
        return format.format(date);
    }

    public static String formatDate2ChineseDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(data_format_day_chinese);
        return format.format(date);
    }

    public static String formatDate2ChineseMonth(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(data_format_month_chinese);
        return format.format(date);
    }

    public static String getYear4Date(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }

    public static String getMonth4Date(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("M");
        return format.format(date);
    }

    public static String getDay4Date(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("d");
        return format.format(date);
    }

    /**
     * 两个日期相差x月x天
     *
     * @param one
     * @param two
     * @return
     */
    public static String diffMonthDay(Date bigDate, Date smallDate) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(bigDate);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(smallDate);
        int byear = c1.get(Calendar.YEAR);
        int syear = c2.get(Calendar.YEAR);
        int bmonth = c1.get(Calendar.MONTH);
        int smonth = c2.get(Calendar.MONTH);
        int bday = c1.get(Calendar.DAY_OF_MONTH);
        int sday = c2.get(Calendar.DAY_OF_MONTH);
        int months = (byear - syear) * 12 + (bmonth - smonth);
        int days = bday - sday;
        // 如果天不够
        if (days < 0) {
            int bDayOfMonth = getDaysOfMonth(bigDate);
            int sDayOfMonth = getDaysOfMonth(smallDate);
            // 如果日期不够，则查看是否是月末,月末，则天数不用计算了
            if (bday == bDayOfMonth && sday == sDayOfMonth) {
                days = 0;
            } else {
                if (bDayOfMonth <= 30) {
                    days = getDaysOfMonth(smallDate) + days;
                } else {
                    // 正常情况日期加减
                    days = getDaysOfMonth(bigDate) + days;
                }
                months--;
            }
        }
        String res = "";
        if (months > 0)
            res += months + "个月";
        if (days > 0)
            res += days + "天";

        return res;
    }

    /**
     * 某月有多少天。可根据2月是28天还是29天判断平年闰年
     *
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int res = DAYS_OF_MONTH[c.get(Calendar.MONTH)];
        // 2月
        if (res == 28) {
            int year = c.get(Calendar.YEAR);
            // 是否闰年
            if (year % 4 == 0) {
                if (year % 100 == 0) {
                    if (year % 400 == 0)
                        res += 1;
                } else {
                    res += 1;
                }
            }
        }
        return res;
    }
}
