package by.kofi.scd.dto.client;

import by.kofi.scd.business.grid.ResultRow;
import by.kofi.scd.entity.CreditItem;

/**
 * @author harchevnikov_m
 *         Date: 25/10/11
 *         Time: 22:50
 */
public class CreditItemResultRow extends CreditItem implements ResultRow {
    private Long rowId;
    private Long creditId;
    private Long clientId;
    private String creditName;
    private long accountNumber;

    /**
     * Constructor to init fields from CreditItem instance
     *
     * @param creditItem creditItem
     */
    public CreditItemResultRow(CreditItem creditItem) {
        setIssuanceDate(creditItem.getIssuanceDate());
        this.creditName = creditItem.getCredit().getName();
        this.accountNumber = creditItem.getAccount().getAccountNumber();
        setAmount(creditItem.getAmount());
        setTerm(creditItem.getTerm());
        setCalculatedAmount(creditItem.getCalculatedAmount());
        setPaidAmount(creditItem.getPaidAmount());
        this.creditId = creditItem.getCredit().getCreditId();
        this.clientId = creditItem.getClient().getClientId();
    }


    @Override
    public Long getRowId() {
        return accountNumber;
    }


    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public Long getCreditId() {
        return this.creditId;
    }

    @Override
    public Long getClientId() {
        return  this.clientId;
    }

}
