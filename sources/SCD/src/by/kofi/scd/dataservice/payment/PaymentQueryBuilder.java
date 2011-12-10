package by.kofi.scd.dataservice.payment;

import by.kofi.scd.exceptions.SCDTechnicalException;

/**
 * Queries builder util class
 *
 * @author harchevnikov_m
 *         Date: 29/10/11
 *         Time: 14:05
 */
public class PaymentQueryBuilder {

    public static String getPaymentsByAccount() throws SCDTechnicalException {
        return "from Payment p where p.account.accountNumber = :account order by p.paymentDate ";
    }

}
