package by.kofi.scd.dataservice.credit.item;

/**
 * Queries builder util class
 *
 * @author harchevnikov_m
 *         Date: 29/10/11
 *         Time: 14:05
 */
public class CreditItemQueryBuilder {

    public static String getCreditItemsByState() {
        return " from CreditItem  cri where cri.state = :state ";
    }
}
