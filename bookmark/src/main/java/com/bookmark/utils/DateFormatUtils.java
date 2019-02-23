package com.bookmark.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 12425 on 2018/8/8.
 */
public class DateFormatUtils {

    private final static String FORMAT = "yyyyMMddhhmmss";
    private final static String FORMAT2 = "yyyy年MM月dd日 hh:mm:ss";

    public static String getCurrentDateString(){
        DateFormat dateFormat = new SimpleDateFormat(FORMAT) ;
        return dateFormat.format(new Date());
    }

    public static Long getCurrentDateLong(){
        return Long.valueOf(getCurrentDateString());
    }

    public static String getStringByLongDate(Long date)  {
        try {
            DateFormat dateFormat = new SimpleDateFormat(FORMAT);
            Date date1 = dateFormat.parse(String.valueOf(date));
            dateFormat = new SimpleDateFormat(FORMAT2);
            return dateFormat.format(date1);
            // return date1.
        }catch (Exception e){

        }
        return String.valueOf(date);
    }

}
