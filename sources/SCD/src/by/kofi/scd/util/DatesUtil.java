package by.kofi.scd.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author harchevnikov_m
 *         Date: 16/11/11
 *         Time: 23:11
 */
public class DatesUtil {

    private static final long MILLISECS_PER_DAY = 1000 * 60 * 60 * 24;

    public static long getDaysBetween(Calendar start, Calendar end) {
        long endL = end.getTimeInMillis() + end.getTimeZone().getOffset(end.getTimeInMillis());
        long startL = start.getTimeInMillis() + start.getTimeZone().getOffset(start.getTimeInMillis());
        return (endL - startL) / MILLISECS_PER_DAY;
    }

    public static int getDaysInYear(Calendar date) {
        if ((new GregorianCalendar()).isLeapYear(date.get(Calendar.YEAR))) {
            return 366;
        } else {
            return 365;
        }
    }
}
