import static junit.framework.Assert.assertEquals;

/**
 * @author harchevnikov_m
 *         Date: 06/11/11
 *         Time: 19:40
 */
public class Test {

    @org.junit.Test
    public void calculator() {
        double term = 1;
        double income = 10;
        double percent = 23;
        double sum = term * income * 12 * 100 / (2 * (100 * 12 + 23 * term));
        sum += sum * .23 / 12;
        assertEquals(1,1);
    }
}