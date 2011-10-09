package by.kofi.scd.entity.generated;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author harchevnikov_m
 *         Date: 08/10/11
 *         Time: 23:08
 */
@Entity
@Table(name = "CREDIT_REQUEST", schema = "SCD")
@SequenceGenerator(name = "SQ_CREDIT_REQUEST", sequenceName = "SQ_CREDIT_REQUEST")
public class CreditRequest {
    private long creditRequestId;

    @javax.persistence.Column(name = "CREDIT_REQUEST_ID")
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE, generator = "SQ_CREDIT_REQUEST")
    public long getCreditRequestId() {
        return creditRequestId;
    }

    public void setCreditRequestId(long creditRequestId) {
        this.creditRequestId = creditRequestId;
    }

    private long userId;

    @javax.persistence.Column(name = "USER_ID")
    @Id
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    private long creditId;

    @javax.persistence.Column(name = "CREDIT_ID")
    @Id
    public long getCreditId() {
        return creditId;
    }

    public void setCreditId(long creditId) {
        this.creditId = creditId;
    }

    private long employeeId;

    @javax.persistence.Column(name = "EMPLOYEE_ID")
    @Basic
    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    private long departmentId;

    @javax.persistence.Column(name = "DEPARTMENT_ID")
    @Basic
    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    private long roleId;

    @javax.persistence.Column(name = "ROLE_ID")
    @Basic
    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    private long accountId;

    @javax.persistence.Column(name = "ACCOUNT_ID")
    @Basic
    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    private Timestamp issuanceDate;

    @javax.persistence.Column(name = "ISSUANCE_DATE")
    @Basic
    public Timestamp getIssuanceDate() {
        return issuanceDate;
    }

    public void setIssuanceDate(Timestamp issuanceDate) {
        this.issuanceDate = issuanceDate;
    }

    private Timestamp processingDate;

    @javax.persistence.Column(name = "PROCESSING_DATE")
    @Basic
    public Timestamp getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(Timestamp processingDate) {
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

    private long state;

    @javax.persistence.Column(name = "STATE")
    @Basic
    public long getState() {
        return state;
    }

    public void setState(long state) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditRequest that = (CreditRequest) o;

        if (accountId != that.accountId) return false;
        if (creditId != that.creditId) return false;
        if (creditRequestId != that.creditRequestId) return false;
        if (departmentId != that.departmentId) return false;
        if (employeeId != that.employeeId) return false;
        if (roleId != that.roleId) return false;
        if (state != that.state) return false;
        if (term != that.term) return false;
        if (userId != that.userId) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (issuanceDate != null ? !issuanceDate.equals(that.issuanceDate) : that.issuanceDate != null) return false;
        if (processingDate != null ? !processingDate.equals(that.processingDate) : that.processingDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (creditRequestId ^ (creditRequestId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (creditId ^ (creditId >>> 32));
        result = 31 * result + (int) (employeeId ^ (employeeId >>> 32));
        result = 31 * result + (int) (departmentId ^ (departmentId >>> 32));
        result = 31 * result + (int) (roleId ^ (roleId >>> 32));
        result = 31 * result + (int) (accountId ^ (accountId >>> 32));
        result = 31 * result + (issuanceDate != null ? issuanceDate.hashCode() : 0);
        result = 31 * result + (processingDate != null ? processingDate.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (int) (term ^ (term >>> 32));
        result = 31 * result + (int) (state ^ (state >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
