package by.kofi.scd.dto.client;

import by.kofi.scd.business.grid.ResultRow;
import by.kofi.scd.entity.Client;
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
    private long calculatedAmount;
    private long paidAmount;
    private String clientName;

    /**
     * Constructor to init fields from CreditItem instance
     *
     * @param creditItem creditItem
     */
    public CreditItemResultRow(CreditItem creditItem) {
        setIssuanceDate(creditItem.getIssuanceDate());
        this.creditName = creditItem.getCredit().getName();
        this.accountNumber = creditItem.getCreditAccount().getAccountNumber();
        setSum(creditItem.getSum());
        setTerm(creditItem.getTerm());
        setCalculatedAmount(creditItem.getCreditAccount().getSum().longValue());
        setPaidAmount(creditItem.getPaymentsAccount().getSum().longValue());
        this.creditId = creditItem.getCredit().getCreditId();
        Client client = creditItem.getClient();
        this.clientId = client.getClientId();

        this.clientName = client.getSurname() + " " + client.getName();
        setState(creditItem.getState());
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
    public long getCreditId() {
        return this.creditId;
    }

    @Override
    public Long getClientId() {
        return  this.clientId;
    }

    public long getCalculatedAmount() {
        return calculatedAmount;
    }

    public void setCalculatedAmount(long calculatedAmount) {
        this.calculatedAmount = calculatedAmount;
    }

    public long getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(long paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
