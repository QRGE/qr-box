package com.qr.blog.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 时间工具类
 * @author QR
 */
@SuppressWarnings("unused")
public final class DateUtils {

    private DateUtils() {
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取指定天数之前的日期
     * @param index 天数
     * @return 日期
     */
    public static String getDay(int index) {
        // 获取日历类
        Calendar calendar = Calendar.getInstance();
        // 获取指定天数前的日期
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - index);
        Date today = calendar.getTime();
        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(today);
    }

    /**
     * 获取当前时间戳
     * @return 当前时间戳
     */
    public static long nowUnix() {
        return System.currentTimeMillis();
    }

    /**
     * 默认日期格式
     * @return 默认日期格式的字符串
     */
    public static String newDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 没有 '-' 和 ':' 连接的日期格式
     * @return 没有 '-' 和 ':' 连接的日期格式的字符串
     */
    public static String newDateTimeNoChar() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    /**
     * 获取当前日期的年月日
     * @return 年月日
     */
    public static String newDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    /**
     * 获取当前日期的年月
     * @return "年月"
     */
    public static String newMonth() {
        return new SimpleDateFormat("yyyy-MM").format(new Date());
    }

    /**
     * 计算两个日期之间相差的天数 整数
     */
    public static String diffTime(String startDate, String endDate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long between = sdf.parse(endDate).getTime() - sdf.parse(startDate).getTime();
        // 转换为秒
        between /= 1000;
        long day = between / (24 * 3600);
        long hour = between % (24 * 3600) / 3600;
        long minute = between % 3600 / 60;
        long second = between % 60;

        // 天转成小时
        hour += day * 24;

        String result = "";
        if (hour != 0) {
            result += hour + "小时";
        }
        if (minute != 0) {
            result += minute + "分";
        }
        if (second != 0) {
            result += second + "秒";
        }

        return result;
    }

    /**
     * 计算日期时间相差天数 浮点数
     */
    public static double diffDoubleTime(String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long a = 0;
        try {
            a = sdf.parse(endDate).getTime() - sdf.parse(startDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return a / 1000.0 / 60 / 60 / 24;
    }

    /**
     * 计算日期相差天数 浮点数
     */
    public static double diffDoubleDate(String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long a = 0;
        try {
            a = sdf.parse(endDate).getTime() - sdf.parse(startDate).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return a / 1000.0 / 60 / 60 / 24;
    }

    /**
     * 指定日期字符串加上 天数
     */
    public static String addDate(String date, int day) {
        long time = 0;
        try {
            time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long second = day * 24 * 60 * 60 * 1000L;
        time += second;
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time));
    }

    /**
     * 判断是否比当前时间大
     */
    public static boolean bigThanNow(String date) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(date);
            Date dt2 = df.parse(newDateTime());
            return dt1.getTime() > dt2.getTime();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    /**
     * 比较日期大小
     */
    public static Integer compareDate(String left, String right) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return df.parse(left).compareTo(df.parse(right));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取到第二天凌晨的毫秒
     */
    public static long getMilliSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        // 改成这样就好了
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() - System.currentTimeMillis();
    }

    /**
     * 获取当前时间（不带符号）
     */
    public static String getNowDateTimeNoChar() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    /**
     * 获取指定月份的整月日期列表
     */
    public static List<String> getDayByMonth(String date) {
        List<String> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        try {
            calendar.setTime(new SimpleDateFormat("yyyy-MM").parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.getActualMaximum(Calendar.DATE);
        String nowDay = newDate();
        for (int i = 1; i <= day; i++) {
            String aDate = null;
            if (month < 10 && i < 10) {
                aDate = year + "-0" + month + "-0" + i;
            }
            if (month < 10 && i >= 10) {
                aDate = year + "-0" + month + "-" + i;
            }
            if (month >= 10 && i < 10) {
                aDate = year + "-" + month + "-0" + i;
            }
            if (month >= 10 && i >= 10) {
                aDate = year + "-" + month + "-" + i;
            }
            list.add(aDate);
            if (nowDay.equals(aDate)) {
                break;
            }
        }
        return list;
    }

    /**
     * 根据所给的时间区间，获取指定月份的所有天数
     * 如给定月份2020-07，startDate：2020-06-15 endDate：2020-07-26
     * 该方法会获取2020-07-01~2020-07-26的日期
     */
    public static List<String> getDaySectionByMonth(String month, String startDate, String endDate) {
        List<String> monthList = getDayByMonth(month);
        String monthStart = monthList.get(0);
        String monthEnd = monthList.get(monthList.size() - 1);
        if (diffDoubleDate(monthStart, startDate) < 0) {
            startDate = monthStart;
        }
        if (diffDoubleDate(monthEnd, endDate) > 0) {
            endDate = monthEnd;
        }
        return getDayBySection(startDate, endDate);
    }

    /**
     * 返回指定的时间段内的所有日期
     */
    public static List<String> getDayBySection(String startDate, String endDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date newStartDate = null;
        Date newEndDate = null;
        try {
            newStartDate = simpleDateFormat.parse(startDate);
            newEndDate = simpleDateFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<String> dateList = new ArrayList<>();
        long spi = newEndDate.getTime() - newStartDate.getTime();
        long step = spi / (24 * 60 * 60 * 1000);
        List<Date> newDateList = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        newDateList.add(newStartDate);
        dateList.add(startDate);
        for (int i = 1; i <= step; i++) {
            dateList.add(formatter.format(new Date(newDateList.get(i - 1).getTime() + (24 * 60 * 60 * 1000))));
            newDateList.add(new Date(newDateList.get(i - 1).getTime() + (24 * 60 * 60 * 1000)));
        }
        return dateList;
    }

}
