package com.lib.jlib.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by txbydev4 on 17/3/7.
 */

public class DateUtil {
    /**
     * 日期格式化  日期转为字符串
     * @param date
     * @param format
     * @return
     */
    public static String formatDateByFormat(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 日期格式化 字符串转为日期
     * @param date
     * @param format
     * @return
     */
    public static Date parseDateByFormat(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d = null;
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }
}
