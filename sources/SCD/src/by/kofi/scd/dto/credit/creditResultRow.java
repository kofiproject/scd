package by.kofi.scd.dto.credit;

import by.kofi.scd.business.grid.ResultRow;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.Credit;

import java.math.BigDecimal;

/**
 * @author harchevnikov_m
 *         Date: 25/10/11
 *         Time: 22:50
 */
public class CreditResultRow extends Credit implements ResultRow {

    public CreditResultRow(Credit credit) {
        setCreditId(credit.getCreditId());
        setName(credit.getName());
        setPercent(credit.getPercent());
        setPenaltyPercent(credit.getPenaltyPercent());
        setMaxTerm(credit.getMaxTerm());
    }

    @Override
    public Long getRowId() {
        return getCreditId();
    }

    @Override
    public Long getClientId() {
        return -1L;
    }
}
