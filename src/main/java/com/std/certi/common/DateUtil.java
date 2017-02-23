package com.std.certi.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final String FRONT_DATE_FORMAT_STRING = "yyyy-MM-dd";

    public static final String DATA_TIME_PATTERN_4 = "yyyyMMDDhhmmss";

    /**
     * 统计两个时间差，返回的是秒数
     * @param beginDate
     * @param endDate
     * @return 
     * @create: 2015年11月16日 上午11:20:51 myb858
     * @history:
     */
    public static Long daysBetween(Date beginDate, Date endDate) {
        Long seconds = null;
        try {
            long time1 = beginDate.getTime();
            long time2 = endDate.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            seconds = diff / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seconds;
    }

    /**
     * 
     * @param date
     * @param addOneDay 是否加1天
     * @return 
     * @create: 2015-5-7 上午11:25:23 miyb
     * @history:
     */
    public static Date getFrontDate(String date, boolean addOneDay) {
        Date returnDate = null;
        try {
            returnDate = new SimpleDateFormat(FRONT_DATE_FORMAT_STRING)
                .parse(date);
            if (addOneDay) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(returnDate);
                calendar.add(calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
                returnDate = calendar.getTime(); // 这个时间就是日期往后推一天的结果
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return returnDate;
    }

    /**
     * 按格式获取当前时间
     * @param pattern
     * @return 
     * @create: 2015-5-7 上午11:22:04 miyb
     * @history:
     */
    public static String getToday(String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(new Date());
    }

}
