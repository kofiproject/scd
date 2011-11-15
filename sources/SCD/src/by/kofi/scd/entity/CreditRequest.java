package by.kofi.scd.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author harchevnikov_m
 *         Date: 08/10/11
 *         Time: 23:08
 */
@Entity
@Table(name = "CREDIT_REQUEST")
@SequenceGenerator(name = "SQ_CREDIT_REQUEST", sequenceName = "SQ_CREDIT_REQUEST")
public class CreditRequest extends AbstractEntity {
    private long creditRequestId;

    @javax.persistence.Column(name = "CREDIT_REQUEST_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CREDIT_REQUEST")
    public long getCreditRequestId() {
        return creditRequestId;
    }

    public void setCreditRequestId(long creditRequestId) {
        this.creditRequestId = creditRequestId;
    }

    private Client client;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    private Credit credit;

    @ManyToOne
    @JoinColumn(name = "CREDIT_ID", referencedColumnName = "CREDIT_ID")
    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private Account account;

    @OneToOne
    @JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    private Date issuanceDate;

    @javax.persistence.Column(name = "ISSUANCE_DATE")
    @Basic
    public Date getIssuanceDate() {
        return issuanceDate;
    }

    public void setIssuanceDate(Date issuanceDate) {
        this.issuanceDate = issuanceDate;
    }

    private Date processingDate;

    @javax.persistence.Column(name = "PROCESSING_DATE")
    @Basic
    public Date getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(Date processingDate) {
        this.processingDate = processingDate;
    }

    private BigDecimal amount;

    @javax.persistence.Column(name = "AMOUNT")
    @Basic
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    private long term;

    @javax.persistence.Column(name = "TERM")
    @Basic
    public long getTerm() {
        return term;
    }

    public void setTerm(long term) {
        this.term = term;
    }

    private CreditRequestStateEnum state;

    @javax.persistence.Column(name = "STATE")
    @Enumerated(EnumType.ORDINAL)
    public CreditRequestStateEnum getState() {
        return state;
    }

    public void setState(CreditRequestStateEnum state) {
        this.state = state;
    }

    private String description;

    @javax.persistence.Column(name = "DESCRIPTION")
    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private BigDecimal monthlyCacheIncome;

    @javax.persistence.Column(name = "MONTHLY_CACHE_INCOME")
    @Basic
    public BigDecimal getMonthlyCacheIncome() {
        return monthlyCacheIncome;
    }

    public void setMonthlyCacheIncome(BigDecimal monthlyCacheIncome) {
        this.monthlyCacheIncome = monthlyCacheIncome;
    }

    private Employee lockedByEmployee;

    @ManyToOne
    @JoinColumn(name = "LOCKED_BY_EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")
    public Employee getLockedByEmployee() {
        return lockedByEmployee;
    }

    public void setLockedByEmployee(Employee lockedByEmployee) {
        this.lockedByEmployee = lockedByEmployee;
    }

    @Override
    public boolean entityEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditRequest that = (CreditRequest) o;

        if (creditRequestId != that.creditRequestId) return false;
        if (term != that.term) return false;
        if (account != null ? !account.equals(that.account) : that.account != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        if (credit != null ? !credit.equals(that.credit) : that.credit != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (employee != null ? !employee.equals(that.employee) : that.employee != null) return false;
        if (issuanceDate != null ? !issuanceDate.equals(that.issuanceDate) : that.issuanceDate != null) return false;
        if (processingDate != null ? !processingDate.equals(that.processingDate) : that.processingDate != null)
            return false;
        if (state != that.state) return false;

        return true;
    }

    @Override
    public int entityHashCode() {
        int result = (int) (creditRequestId ^ (creditRequestId >>> 32));
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (credit != null ? credit.hashCode() : 0);
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (issuanceDate != null ? issuanceDate.hashCode() : 0);
        result = 31 * result + (processingDate != null ? processingDate.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (int) (term ^ (term >>> 32));
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }


    @Override
    @Transient
    public Serializable getEntityId() {
        return getCreditRequestId();
    }
}
