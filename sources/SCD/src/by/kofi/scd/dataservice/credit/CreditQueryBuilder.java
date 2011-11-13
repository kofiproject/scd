package by.kofi.scd.dataservice.credit;

import by.kofi.scd.exceptions.SCDTechnicalException;

/**
 * Queries builder util class
 *
 * @author harchevnikov_m
 *         Date: 29/10/11
 *         Time: 14:05
 */
public class CreditQueryBuilder {

    public static String getCreditByName() {
        return "from Credit cr where cr.name = :name ";
    }

}
