package by.kofi.scd.dto.bank;

import by.kofi.scd.business.grid.ResultRow;
import by.kofi.scd.dto.client.creditRequest.CreditRequestResultRow;
import by.kofi.scd.entity.Account;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.CreditRequest;
import by.kofi.scd.entity.Employee;

/**
 * @author harchevnikov_m
 *         Date: 25/10/11
 *         Time: 22:50
 */
public class ExpertCreditRequestResultRow extends CreditRequestResultRow implements ResultRow {
    /**
     * Constructor to init fields from CreditItem instance
     *
     * @param creditRequest creditRequest
     */
    public ExpertCreditRequestResultRow(CreditRequest creditRequest) {
        super(creditRequest);
    }
}
