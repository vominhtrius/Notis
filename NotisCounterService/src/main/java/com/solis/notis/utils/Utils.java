package com.solis.common.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author Võ Minh Trí
 */
public class Utils {

    public static List<Integer> converUnixToDate(long unix) {
        Calendar currentDate = new GregorianCalendar();
        currentDate.setTimeInMillis(unix * 1000L);

        ArrayList<Integer> res = new ArrayList<>(3);

        res.add(currentDate.get(Calendar.DAY_OF_MONTH));
        res.add(currentDate.get(Calendar.MONTH) + 1);
        res.add(currentDate.get(Calendar.YEAR));

        return res;
    }

    public static long getUnixTime(int dd, int mm, int yy) {
        Calendar currentDate = new GregorianCalendar(yy, mm - 1, dd);

        return currentDate.getTimeInMillis() / 1000L;
    }

    public static long getCurrentUnixDate() {
        Calendar calendar = Calendar.getInstance();
        Calendar currentDate = new GregorianCalendar(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        return currentDate.getTimeInMillis() / 1000L;
    }

    public static long getCurrentUnix() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis() / 1000L;
    }
}
