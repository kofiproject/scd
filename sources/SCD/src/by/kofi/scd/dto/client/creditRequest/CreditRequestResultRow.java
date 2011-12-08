package by.kofi.scd.dto.client.creditRequest;

import by.kofi.scd.business.grid.ResultRow;
import by.kofi.scd.entity.*;

/**
 * @author harchevnikov_m
 *         Date: 25/10/11
 *         Time: 22:50
 */
public class CreditRequestResultRow extends CreditRequest implements ResultRow {
    private String creditName;
    private long accountNumber;
    private String expert;
    private String clientName;
    private Long creditId;
    private Long clientId;

    /**
     * Constructor to init fields from CreditItem instance
     *
     * @param creditRequest creditRequest
     */
    public CreditRequestResultRow(CreditRequest creditRequest) {
        setCreditRequestId(creditRequest.getCreditRequestId());
        setIssuanceDate(creditRequest.getIssuanceDate());
        setAmount(creditRequest.getAmount());
        setTerm(creditRequest.getTerm());
        this.creditName = creditRequest.getCredit().getName();
        Account account = creditRequest.getAccount();
        this.accountNumber = account != null ? account.getAccountNumber() : -1L;

        setProcessingDate(creditRequest.getProcessingDate());
        setDescription(creditRequest.getDescription());

        Employee employee = creditRequest.getEmployee();
        if (employee != null) {
            this.expert = employee.toString();
        }

        Client requestClient = creditRequest.getClient();
        this.clientName = requestClient.getSurname() + " " + requestClient.getName();

        this.creditId = creditRequest.getCredit().getCreditId();
        this.clientId = requestClient.getClientId();
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

    public String getExpert() {
        return expert;
    }

    public void setExpert(String expert) {
        this.expert = expert;
    }


    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public long getCreditId() {
        return this.creditId;
    }

    @Override
    public Long getClientId() {
        return this.clientId;
    }


}
