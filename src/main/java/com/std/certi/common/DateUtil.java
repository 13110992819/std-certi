package com.std.certi.common;

import java.util.Date;

public class DateUtil {
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
}
