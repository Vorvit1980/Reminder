package com.example.vorvit1980.reminder;

import java.text.SimpleDateFormat;

/**
 * Created by vorvit1980 on 01.02.2017.
 */

public class Utils {

    public static String getDate(long date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        return dateFormat.format(date);
    }

    public static String getTime(long time){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH.mm");
        return dateFormat.format(time);
    }

    public static String getFullDate(long date){
        SimpleDateFormat fullDateFormat = new SimpleDateFormat("dd.MM.yy  HH.mm");
        return fullDateFormat.format(date);
    }
}
