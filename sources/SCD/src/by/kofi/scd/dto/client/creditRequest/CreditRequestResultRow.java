package by.kofi.scd.dto.client.creditRequest;

import by.kofi.scd.business.grid.ResultRow;
import by.kofi.scd.entity.Client;
import by.kofi.scd.entity.CreditRequest;
import by.kofi.scd.entity.Employee;

/**
 * @author harchevnikov_m
 *         Date: 25/10/11
 *         Time: 22:50
 */
public class CreditRequestResultRow extends CreditRequest implements ResultRow {
    private String creditName;
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
        setSum(creditRequest.getSum());
        setTerm(creditRequest.getTerm());
        this.creditName = creditRequest.getCredit().getName();

        setProcessingDate(creditRequest.getProcessingDate());
        setDescription(creditRequest.getDescription());
        setIssuanceDate(creditRequest.getIssuanceDate());

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
        return getCreditRequestId();
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String creditName) {
        this.creditName = creditName;
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
