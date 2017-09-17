package com.shahriar.hasan.monthlyexpenserecorder.Utility;

import java.util.Calendar;

/**
 * Created by USER on 8/6/2017.
 */

public class DateUtility {

    public int getYear(long time){
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(time);
        return cl.get(Calendar.YEAR);
    }
    public int getMonth(long time){
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(time);
        return cl.get(Calendar.MONTH);
    }
    public int getDay(long time){
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(time);
        return cl.get(Calendar.DAY_OF_MONTH);
    }
    public int getHour(long time){
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(time);
        return cl.get(Calendar.HOUR);
    }
    public int getMinute(long time){
        Calendar cl = Calendar.getInstance();
        cl.setTimeInMillis(time);
        return cl.get(Calendar.MINUTE);

    }

    public long createDate(int year, int month, int day, int hour, int min){
        Calendar cl = Calendar.getInstance();
        cl.set(year,month,day,hour,min);
        return cl.getTimeInMillis();
    }
}
