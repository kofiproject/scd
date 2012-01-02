package by.kofi.scd.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

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

    private Account debitAccount;

    @OneToOne
    @JoinColumn(name = "DEBIT_ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID")
    public Account getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(Account debitAccount) {
        this.debitAccount = debitAccount;
    }

    private Account creditAccount;

    @OneToOne
    @JoinColumn(name = "CREDIT_ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID")
    public Account getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(Account creditAccount) {
        this.creditAccount = creditAccount;
    }

    private Account paymentsAccount;

    @OneToOne
    @JoinColumn(name = "PAYMENT_ACCOUNT_ID", referencedColumnName = "ACCOUNT_ID")
    public Account getPaymentsAccount() {
        return paymentsAccount;
    }

    public void setPaymentsAccount(Account paymentsAccount) {
        this.paymentsAccount = paymentsAccount;
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

    private Date closingDate;

    @javax.persistence.Column(name = "CLOSING_DATE")
    @Basic
    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    private BigDecimal sum;

    @javax.persistence.Column(name = "SUM")
    @Basic
    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
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

    private CreditItemStateEnum state;

    @javax.persistence.Column(name = "STATE")
    @Enumerated(EnumType.ORDINAL)
    public CreditItemStateEnum getState() {
        return state;
    }

    public void setState(CreditItemStateEnum state) {
        this.state = state;
    }

    private Date lastUpdated;

    @Column(name = "LAST_UPDATED")
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public boolean entityEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CreditItem that = (CreditItem) o;

        if (creditItemId != that.creditItemId) return false;
        if (term != that.term) return false;
        if (sum != null ? !sum.equals(that.sum) : that.sum != null) return false;
        if (client != null ? !client.equals(that.client) : that.client != null) return false;
        if (closingDate != null ? !closingDate.equals(that.closingDate) : that.closingDate != null) return false;
        if (credit != null ? !credit.equals(that.credit) : that.credit != null) return false;
        if (issuanceDate != null ? !issuanceDate.equals(that.issuanceDate) : that.issuanceDate != null) return false;
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
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        result = 31 * result + (int) (term ^ (term >>> 32));
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    @Transient
    public Serializable getEntityId() {
        return getCreditItemId();
    }

    private Set<PercentHistory> percentHistories;

    @OneToMany(mappedBy = "creditItem", cascade = CascadeType.REMOVE)
    public Set<PercentHistory> getPercentHistories() {
        return percentHistories;
    }

    public void setPercentHistories(Set<PercentHistory> percentHistories) {
        this.percentHistories = percentHistories;
    }

}
