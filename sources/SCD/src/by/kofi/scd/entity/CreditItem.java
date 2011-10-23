package by.kofi.scd.entity;

import javax.persistence.*;
import java.io.Serializable;
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
public class CreditItem extends AbstractEntity {
    private Long creditItemId;

    @javax.persistence.Column(name = "CREDIT_ITEM_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CREDIT_ITEM")
    public Long getCreditItemId() {
        return creditItemId;
    }

    public void setCreditItemId(Long creditItemId) {
        this.creditItemId = creditItemId;
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

    private Client client;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "CLIENT_ID")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    private Long term;

    @javax.persistence.Column(name = "TERM")
    @Basic
    public Long getTerm() {
        return term;
    }

    public void setTerm(Long term) {
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

    private CreditItemStateEnum state;

    @javax.persistence.Column(name = "STATE")
    @Enumerated(EnumType.ORDINAL)
    public CreditItemStateEnum getState() {
        return state;
    }

    public void setState(CreditItemStateEnum state) {
        this.state = state;
    }

    @Override
    public boolean entityEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CreditItem that = (CreditItem) o;

        if (account != that.account) return false;
        if (creditItemId != that.creditItemId) return false;
        if (term != that.term) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (calculatedAmount != null ? !calculatedAmount.equals(that.calculatedAmount) : that.calculatedAmount != null)
            return false;
        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        if (closingDate != null ? !closingDate.equals(that.closingDate) : that.closingDate != null) return false;
        if (credit != null ? !credit.equals(that.credit) : that.credit != null) return false;
        if (issuanceDate != null ? !issuanceDate.equals(that.issuanceDate) : that.issuanceDate != null) return false;
        if (paidAmount != null ? !paidAmount.equals(that.paidAmount) : that.paidAmount != null) return false;
        if (penaltyAmount != null ? !penaltyAmount.equals(that.penaltyAmount) : that.penaltyAmount != null)
            return false;
        if (state != that.state) return false;

        return true;
    }

    @Override
    public int entityHashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (creditItemId ^ (creditItemId >>> 32));
        result = 31 * result + (credit != null ? credit.hashCode() : 0);
        result = 31 * result + (client != null ? client.hashCode() : 0);
        result = 31 * result + (issuanceDate != null ? issuanceDate.hashCode() : 0);
        result = 31 * result + (closingDate != null ? closingDate.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (int) (term ^ (term >>> 32));
        result = 31 * result + (calculatedAmount != null ? calculatedAmount.hashCode() : 0);
        result = 31 * result + (paidAmount != null ? paidAmount.hashCode() : 0);
        result = 31 * result + (penaltyAmount != null ? penaltyAmount.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    @Transient
    public Serializable getEntityId() {
        return getCreditItemId();
    }
}
