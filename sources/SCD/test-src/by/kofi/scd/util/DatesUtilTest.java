package by.kofi.scd.util;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static junit.framework.Assert.assertEquals;

/**
 * @author harchevnikov_m
 *         Date: 16/11/11
 *         Time: 23:33
 */
public class DatesUtilTest {
    @Test
    public void testGetDaysInYear() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int daysInYear = DatesUtil.getDaysInYear(calendar);
        assertEquals(365, daysInYear);
    }
}
