package by.kofi.scd.dto.client.creditRequest;

import by.kofi.scd.business.grid.ResultRow;
import by.kofi.scd.entity.Account;
import by.kofi.scd.entity.CreditItem;
import by.kofi.scd.entity.CreditRequest;
import by.kofi.scd.entity.Employee;

/**
 * @author harchevnikov_m
 *         Date: 25/10/11
 *         Time: 22:50
 */
public class CreditRequestResultRow extends CreditRequest implements ResultRow {
    private Long rowId;

    private String creditName;
    private long accountNumber;
    private String expert;

    /**
     * Constructor to init fields from CreditItem instance
     *
     * @param creditRequest creditRequest
     */
    public CreditRequestResultRow(CreditRequest creditRequest) {
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
            this.expert = employee.getSurname();
        }
    }

    @Override
    public Long getRowId() {
        return accountNumber;
    }

    @Override
    public void setRowId(Long rowId) {
        this.rowId = rowId;
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
}
