package by.kofi.scd.dataservice.payment;

import by.kofi.scd.dataservice.AbstractDataService;
import by.kofi.scd.entity.CreditRequest;
import by.kofi.scd.entity.CreditRequestStateEnum;
import by.kofi.scd.entity.Payment;
import by.kofi.scd.exceptions.SCDTechnicalException;

import java.util.List;

/**
 * @author harchevnikov_m
 *         Date: 15/10/11
 *         Time: 21:06
 */
public interface PaymentDataService extends AbstractDataService {

    /**
     * Retrieve payments list by account number
     *
     * @param accountNumber account
     * @return creditRequests
     * @throws by.kofi.scd.exceptions.SCDTechnicalException
     *          hql error
     */
    public List<Payment> getPaymentsByAccount(Long accountNumber) throws SCDTechnicalException;

}