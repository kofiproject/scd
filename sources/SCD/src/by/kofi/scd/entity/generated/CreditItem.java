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
@Table(name = "CREDIT_ITEM")
@SequenceGenerator(name = "SQ_CREDIT_ITEM", sequenceName = "SQ_CREDIT_ITEM")
public class CreditItem {
    private long creditItemId;

    @javax.persistence.Column(name = "CREDIT_ITEM_ID")
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE, generator = "SQ_CREDIT_ITEM")
    public long getCreditItemId() {
        return creditItemId;
    }

    public void setCreditItemId(long creditItemId) {
        this.creditItemId = creditItemId;
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

    private long userId;

    @javax.persistence.Column(name = "USER_ID")
    @Id
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    private long accountId;

    @javax.persistence.Column(name = "ACCOUNT_ID")
    @Id
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

    private Timestamp closingDate;

    @javax.persistence.Column(name = "CLOSING_DATE")
    @Basic
    public Timestamp getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Timestamp closingDate) {
        this.closingDate = closingDate;
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

    private BigDecimal calculatedAmount;

    @javax.persistence.Column(name = "CALCULATED_AMOUNT")
    @Basic
    public BigDecimal getCalculatedAmount() {
        return calculatedAmount;
    }

    public void setCalculatedAmount(BigDecimal calculatedAmount) {
        this.calculatedAmount = calculatedAmount;
    }

    private BigDecimal paidAmount;

    @javax.persistence.Column(name = "PAID_AMOUNT")
    @Basic
    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    private BigDecimal penaltyAmount;

    @javax.persistence.Column(name = "PENALTY_AMOUNT")
    @Basic
    public BigDecimal getPenaltyAmount() {
        return penaltyAmount;
    }

    public void setPenaltyAmount(BigDecimal penaltyAmount) {
        this.penaltyAmount = penaltyAmount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreditItem that = (CreditItem) o;

        if (accountId != that.accountId) return false;
        if (creditId != that.creditId) return false;
        if (creditItemId != that.creditItemId) return false;
        if (state != that.state) return false;
        if (term != that.term) return false;
        if (userId != that.userId) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (calculatedAmount != null ? !calculatedAmount.equals(that.calculatedAmount) : that.calculatedAmount != null)
            return false;
        if (closingDate != null ? !closingDate.equals(that.closingDate) : that.closingDate != null) return false;
        if (issuanceDate != null ? !issuanceDate.equals(that.issuanceDate) : that.issuanceDate != null) return false;
        if (paidAmount != null ? !paidAmount.equals(that.paidAmount) : that.paidAmount != null) return false;
        if (penaltyAmount != null ? !penaltyAmount.equals(that.penaltyAmount) : that.penaltyAmount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (creditItemId ^ (creditItemId >>> 32));
        result = 31 * result + (int) (creditId ^ (creditId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (accountId ^ (accountId >>> 32));
        result = 31 * result + (issuanceDate != null ? issuanceDate.hashCode() : 0);
        result = 31 * result + (closingDate != null ? closingDate.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (int) (term ^ (term >>> 32));
        result = 31 * result + (calculatedAmount != null ? calculatedAmount.hashCode() : 0);
        result = 31 * result + (paidAmount != null ? paidAmount.hashCode() : 0);
        result = 31 * result + (penaltyAmount != null ? penaltyAmount.hashCode() : 0);
        result = 31 * result + (int) (state ^ (state >>> 32));
        return result;
    }
}
